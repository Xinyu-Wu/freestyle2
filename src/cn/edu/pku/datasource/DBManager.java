/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 *
 * @author Liu Yang
 */
public class DBManager {

    private String host;
    private String port;
    private String database;
    private String superUser;
    private String superUserPwd;
    private PostgreSQLManager manager;
//    private String currentUser;
//    private String currentUserPwd;

    public DBManager(String host, String port, String database, String superUser, String superUserPwd) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.superUser = superUser;
        this.superUserPwd = superUserPwd;
        Connection dm = null;
        Statement stmt = null;
        manager = new PostgreSQLManager();
        manager.postgisConfig.setHost(host);
        manager.postgisConfig.setPort(port);
        manager.postgisConfig.setDatabaseName(database);
        manager.postgisConfig.setPassword(superUserPwd);
        manager.postgisConfig.setUser(superUser);
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String create_userinfo = "CREATE TABLE IF NOT EXISTS UserInfo"
                    + "(UserID VARCHAR(20) PRIMARY KEY NOT NULL,"
                    + "Password VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(create_userinfo);
            String create_projects = "CREATE TABLE IF NOT EXISTS Projects"
                    + "(FP_Name VARCHAR(20) PRIMARY KEY NOT NULL,"
                    + "OwnerID VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(create_projects);
            String create_prouser = "CREATE TABLE IF NOT EXISTS ProjectUser"
                    + "(FP_Name VARCHAR(20) NOT NULL,"
                    + "UserID VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(create_prouser);
            stmt.close();
            dm.close();
            System.out.println("Connected to server successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * User sign in.CurrentUser will be changed to the given userid.
     *
     * @param userid
     * @param password
     * @return true(passed) false(failed)
     */
    public boolean signIn(String userid, String password) {
        boolean flag = false;
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "SELECT * FROM userinfo"
                    + " WHERE userid='" + userid + "' AND password='" + password + "';";
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                flag = true;
            }
            stmt.close();
            dm.close();
            System.out.println("User " + userid + " logined successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    /**
     * Add a record when a new user signs up.
     *
     * @param userid
     * @param password
     */
    public boolean signUp(String userid, String password) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql_exist = "SELECT * FROM userinfo WHERE userid= '" + userid + "'";
            ResultSet rs = stmt.executeQuery(sql_exist);
            if (rs.next()) {
                System.out.println(userid + " already exists. Add " + userid + " failed.");
                stmt.close();
                dm.close();
                success = false;
                return success;
            }
            String create_user = "CREATE USER " + userid + " WITH "
                    + "	LOGIN"
                    + "	NOSUPERUSER"
                    + "	CREATEDB"
                    + "	NOCREATEROLE"
                    + "	INHERIT"
                    + "	NOREPLICATION"
                    + "	CONNECTION LIMIT -1"
                    + "	PASSWORD '" + password + "';";
            stmt.executeUpdate(create_user);
            String sql = "INSERT INTO userinfo(userid,password)"
                    + "VALUES ('" + userid + "','" + password + "' );";
            stmt.executeUpdate(sql);

            stmt.close();
            dm.close();
            System.out.println("Signed up successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * Invite a superUser to a project.
     *
     * @param fp_name
     * @param userid
     */
    public boolean addUsertoProject(String fp_name, String userid) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql_exist = "SELECT * FROM projectuser WHERE fp_name= '" + fp_name + "' AND userid= '" + userid + "'";
            ResultSet rs = stmt.executeQuery(sql_exist);
            if (rs.next()) {
                System.out.println("This record already exists. Add this record failed.");
                stmt.close();
                dm.close();
                success = false;
                return success;
            }

            String sql = "INSERT INTO projectuser(fp_name,userid)"
                    + "VALUES ('" + fp_name + "','" + userid + "');";
            stmt.executeUpdate(sql);

            String sql_ownerID = "SELECT ownerid FROM projects WHERE fp_name= '" + fp_name + "'";
            ResultSet rso = stmt.executeQuery(sql_ownerID);
            String ownerid = null;
            if (rso.next()) {
                ownerid = rso.getString("ownerid");
            }
            if (!ownerid.equals(userid)) {
                String grant = "GRANT " + ownerid + " TO " + userid + " ;";
                stmt.executeUpdate(grant);
            }

            stmt.close();
            dm.close();
            System.out.println("Add " + userid + " to " + fp_name + " successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * Find users who have access to the given project.
     *
     * @param fp_name
     * @return List userlist
     */
    public List queryUserbyProject(String fp_name) {
        List<String> userList = new ArrayList<>();
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "SELECT userid FROM projectuser"
                    + " WHERE fp_name='" + fp_name + "';";
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userList.add(rs.getString("userid"));
            }
            stmt.close();
            dm.close();
            System.out.println("Query executed successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    /**
     * Find projects which belong to the given superUser.
     *
     * @param userid
     * @return List projectlist
     */
    public List queryProjectbyUser(String userid) {
        List<String> projectList = new ArrayList<>();
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "SELECT fp_name FROM projectuser"
                    + " WHERE userid='" + userid + "';";
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                projectList.add(rs.getString("fp_name"));
            }
            stmt.close();
            dm.close();
            System.out.println("Query executed successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            projectList = null;
        }
        return projectList;
    }

    /**
     * Delete a layer from a project.
     *
     * @param fp_name
     * @param layer_name
     */
    public boolean deleteLayer(String fp_name, String layer_name) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, fp_name, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "DELETE FROM layers WHERE layer_name= '" + layer_name + "'";
            stmt.executeUpdate(sql);
            String drop_layer = "DROP TABLE " + layer_name;
            stmt.executeUpdate(drop_layer);
            stmt.close();
            dm.close();
            System.out.println("Delete layer successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * Check the lock status of the given layer
     *
     * @param fp_name
     * @param layer_name
     * @return null or the userid
     */
    public String checkLayerLock(String fp_name, String layer_name) {
        String lockstatus = null;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, fp_name, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "SELECT lockstatus FROM layers WHERE layer_name= '" + layer_name + "'";
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lockstatus = rs.getString("lockstatus");
            }
            stmt.close();
            dm.close();
            System.out.println("Check lockstatus successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lockstatus;
    }

    /**
     * Set the lock status of the given layer.
     *
     * @param fp_name
     * @param layer_name
     * @param lockstatus
     */
    public boolean setLayerLock(String fp_name, String layer_name, String lockstatus) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, fp_name, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "UPDATE layers SET lockstatus= '" + lockstatus + "' WHERE layer_name= '" + layer_name + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Set lockstatus successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * Insert a record to TABLE projects when a project is added or created.
     *
     * @param fp_name
     * @param ownerid
     */
    private boolean insertProjectRecord(String fp_name, String ownerid) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql_exist = "SELECT * FROM projects Where fp_name='" + fp_name + "'";
            ResultSet rs = stmt.executeQuery(sql_exist);
            if (rs.next()) {
                System.out.println("Error: " + fp_name + " has already been inserted.There is no need to insert again.");
                stmt.close();
                dm.close();
                success = false;
                return success;
            }

            String sql = "INSERT INTO projects(fp_name,ownerid)"
                    + " VALUES ('" + fp_name + "','" + ownerid + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Insert " + fp_name + " successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * Create a new project
     *
     * @param fp_name
     * @param ownerID
     * @return
     */
    public boolean newProject(String fp_name, String ownerID) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql_exist = "SELECT datname FROM pg_database WHERE datname= '" + fp_name + "'";
            ResultSet rs = stmt.executeQuery(sql_exist);
            if (rs.next()) {
                System.out.println("Database " + fp_name + " already existed.");
                stmt.close();
                dm.close();
                success = false;
                return success;
            }

            String sql = "CREATE DATABASE " + fp_name + " WITH OWNER = " + ownerID + " ;";
            stmt.executeUpdate(sql);
            String grant = "GRANT ALL PRIVILEGES ON DATABASE " + fp_name + " TO " + ownerID;
            stmt.executeUpdate(grant);
            stmt.close();
            dm.close();
            
            dm = manager.connetToPostgre(host, port, fp_name, superUser, superUserPwd);
            stmt = dm.createStatement();
            String sql_postgis = "CREATE EXTENSION postgis" ;
            stmt.executeUpdate(sql_postgis);
            System.out.println("Create database " + fp_name + " successfully");

            insertProjectRecord(fp_name, ownerID);
            addUsertoProject(fp_name, ownerID);

            String create_layers = "CREATE TABLE IF NOT EXISTS layers"
                    + "(layer_name VARCHAR(20) PRIMARY KEY NOT NULL,"
                    + "lockstatus VARCHAR(100) )";
            stmt.executeUpdate(create_layers);
           
            stmt.close();
            dm.close();
            System.out.println("New project " + fp_name + " created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } finally {
        }
        return success;
    }

    /**
     * Open a project
     *
     * @param fp_name
     * @param userID
     * @return All layers' name that this project contains.
     */
    public List openProject(String fp_name, String userID) {
        List<String> layersName = new ArrayList<>();
        List<String> projects = queryProjectbyUser(userID);
        if (projects == null) {
            System.out.println("User " + userID + " has no access to project " + fp_name + ".");
            layersName = null;
        } else {
            Connection dm = null;
            Statement stmt = null;
//            PostgreSQLManager manager = new PostgreSQLManager();

            dm = manager.connetToPostgre(host, port, fp_name, superUser, superUserPwd);
            try {
                stmt = dm.createStatement();
                String sql = "SELECT layer_name FROM layers ";
                ResultSet rs = null;
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    layersName.add(rs.getString("layer_name"));
                }
                stmt.close();
                dm.close();
                System.out.println("Open project " + fp_name + " successfully");
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                layersName = null;
            }
        }
        return layersName;
    }

    /**
     * Delete the given project.
     *
     * @param fp_name
     * @return
     */
    public boolean deleteProject(String fp_name) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        dm = manager.connetToPostgre(host, port, database, superUser, superUserPwd);
        try {
            stmt = dm.createStatement();
            String sql = "DROP DATABASE " + fp_name;
            stmt.executeUpdate(sql);
            String sql_deleteProjects = "DELETE FROM projects WHERE fp_name= '" + fp_name + "'";
            stmt.executeUpdate(sql_deleteProjects);
            String sql_deleteProjectUser = "DELETE FROM projectuser WHERE fp_name= '" + fp_name + "'";
            stmt.executeUpdate(sql_deleteProjectUser);

            stmt.close();
            dm.close();
            System.out.println("Delete project " + fp_name + " successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * Return the SimpleFeatureCollection of the given layer
     *
     * @param fp_name
     * @param layer_name
     * @return the SimpleFeatureCollection of the given layer
     */
    public SimpleFeatureCollection getCollection(String fp_name, String layer_name) {
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
        JDBCDataStore dataStore = manager.getDataStoreFromPostgre("postgis", host, port, fp_name, superUser, superUserPwd);
        SimpleFeatureCollection featureCollection = null;
        try {
            SimpleFeatureSource featureSource = (SimpleFeatureSource) manager.getFeatureByTableName(dataStore, layer_name);
            featureCollection = featureSource.getFeatures();
        } catch (IOException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return featureCollection;
    }

    /**
     * Save a new layer or an already existed layer
     *
     * @param fp_name
     * @param layer_name
     * @param lockstatus null or the ownerID
     * @param featureCollection
     * @return
     */
    public boolean saveLayer(String fp_name, String layer_name, String lockstatus, SimpleFeatureCollection featureCollection) {
        boolean success = true;
        Connection dm = null;
        Statement stmt = null;
//        PostgreSQLManager manager = new PostgreSQLManager();
//        manager.postgisConfig.setHost(host);
//        manager.postgisConfig.setPort(port);
        manager.postgisConfig.setDatabaseName(fp_name);
//        manager.postgisConfig.setPassword(superUserPwd);
//        manager.postgisConfig.setUser(superUser);
        dm = manager.connetToPostgre(host, port, fp_name, superUser, superUserPwd);
        try {
//            manager.postgisConfig.setDatabaseName(fp_name);
            
            manager.storeFeatureCollectionToPostgis(featureCollection, layer_name);
            
//            manager.postgisConfig.setDatabaseName(database);
            
            stmt = dm.createStatement();
            String sql_exist = "SELECT * FROM layers WHERE layer_name= '" + layer_name + "'";
            ResultSet rs = stmt.executeQuery(sql_exist);
            if (rs.next()) {
                String sql = "UPDATE layers SET lockstatus= '" + lockstatus + "' WHERE layer_name= '" + layer_name + "'";
                stmt.executeUpdate(sql);
            } else {
                String sql = "INSERT INTO layers(layer_name,lockstatus)"
                        + " VALUES ('" + layer_name + "','" + lockstatus + "');";
                stmt.executeUpdate(sql);
            }
            stmt.close();
            dm.close();
            System.out.println("Save layer " + layer_name + " successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    public static void main(String[] args) {
        String host = "localhost";
        String port = "5432";
        String database = "postgis_24_sample";
        String user = "postgres";
        String pwd = "123";
        DBManager dbm = new DBManager(host, port, database, user, pwd);

//        dbm.signUp("yangliu", "1230");
//        boolean flag = dbm.signIn("yangliu", "1230");
//        System.out.println(flag);
//        dbm.newProject("fp1", "yangliu");
//        dbm.deleteProject("fp1");
//        dbm.signUp("user2", "1232");
//        dbm.newProject("fp2", "user2");
//        dbm.addUsertoProject("fp2", "yangliu");
//        dbm.signUp("user1", "1231");
//        dbm.addUsertoProject("fp2", "user1");  
//        List<String> userList=dbm.queryUserbyProject("fp2");
//        System.out.println(userList);
//        List<String> projectList=dbm.queryProjectbyUser("yangliu");
//        System.out.println(projectList);

//        ShapefileManager shpManager = new ShapefileManager();
//        File file = JFileDataStoreChooser.showOpenFile("shp", null);
//        if (file == null) {
//            System.out.print("wrong file");
//        }
//        SimpleFeatureSource featureSource = shpManager.readShpFromFile(file);
//        try {
//            SimpleFeatureCollection featureCollection = featureSource.getFeatures();
//            
//            dbm.saveLayer("fp1", "test_layer", "yangliu", featureCollection);
//        } catch (IOException ex) {
//            Logger.getLogger(PostgreSQLManager.class.getName()).log(Level.SEVERE, null, ex);
//        }


//SimpleFeatureCollection featureCollection = dbm.getCollection("fp1", "test_layer");

//dbm.deleteLayer("fp1", "test_layer");
    }
}
