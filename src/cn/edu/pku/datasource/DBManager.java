/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Liu Yang
 */
public class DBManager {
    private String host;
    private String port;
    private String database;
    private String user;
    private String pwd;
    
    public DBManager(String host, String port, String database, String user, String pwd) {
        this.host=host;
        this.port=port;
        this.database=database;
        this.user=user;
        this.pwd=pwd;
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String create_userinfo = "CREATE TABLE IF NOT EXISTS UserInfo" +
                      "(UserID VARCHAR(20) PRIMARY KEY NOT NULL," +
                      "Password VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(create_userinfo);
            String create_projects = "CREATE TABLE IF NOT EXISTS Projects" +
                      "(FP_Name VARCHAR(20) PRIMARY KEY NOT NULL," +
                      "OwnerID VARCHAR(20) NOT NULL,"+
                       "IndexPath VARCHAR(100) NOT NULL)";
            stmt.executeUpdate(create_projects);
            String create_prouser = "CREATE TABLE IF NOT EXISTS ProjectUser" +
                      "(UserID VARCHAR(20) NOT NULL," +
                      "FP_Name VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(create_prouser);
            stmt.close();
            dm.close();
            System.out.println("Tables created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Verify if userid and password are matched
     * @param userid
     * @param password
     * @return boolean flag:true(passed) false(failed)
     */
    public boolean verifyLogin(String userid, String password){
        boolean flag=false;
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql = "SELECT * FROM userinfo" +
                      " WHERE userid='"+userid+"' AND password='"+password+"';";
            ResultSet rs=null;
            rs=stmt.executeQuery(sql);
            if (rs.next()) {
                flag=true;
            } 
            stmt.close();
            dm.close();
            System.out.println("Query executed successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    /**
     * Add a record when a new user sign up.
     * @param userid
     * @param password 
     */
    public boolean signup(String userid, String password){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql_exist="SELECT * FROM userinfo WHERE userid= '"+userid+"'";
            ResultSet rs=stmt.executeQuery(sql_exist);
            if(rs.next()){
                System.out.println(userid+" already exists. Add "+userid+" failed.");
                stmt.close();
                dm.close();
                return false;
            }
            
            String sql = "INSERT INTO userinfo(userid,password)" +
                      "VALUES ('"+userid+"','"+password+"' );";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Signed up successfully");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * Invite a user to a project.
     * @param fp_name
     * @param userid 
     */
    public void inviteProjectUser(String fp_name,String userid){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql_exist="SELECT * FROM projectuser WHERE fp_name= '"+fp_name+"' AND userid= '"+userid+"'";
            ResultSet rs=stmt.executeQuery(sql_exist);
            if(rs.next()){
                System.out.println("This record already exists. Add this record failed.");
                stmt.close();
                dm.close();
                return;
            }
            
            String sql = "INSERT INTO projectuser(fp_name,userid)" +
                      "VALUES ('"+fp_name+"','"+userid+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Invite user successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Find users who have access to the given project.
     * @param fp_name
     * @return List userlist
     */
    public List queryUserbyProject(String fp_name){
        List<String> userList=new ArrayList<>();
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql = "SELECT userid FROM projectuser" +
                      " WHERE fp_name='"+fp_name+"';";
            ResultSet rs=null;
            rs=stmt.executeQuery(sql);
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
     * Find projects which belong to the given user.
     * @param userid
     * @return List projectlist
     */
    public List queryProjectbyUser(String userid){
        List<String> projectList=new ArrayList<>();
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql = "SELECT fp_name FROM projectuser" +
                      " WHERE userid='"+userid+"';";
            ResultSet rs=null;
            rs=stmt.executeQuery(sql);
            while (rs.next()) {
                projectList.add(rs.getString("fp_name"));
            }
            stmt.close();
            dm.close();
            System.out.println("Query executed successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projectList;
    }
    /**
     * Insert a record to TABLE projects when a project is added or created.
     * @param fp_name
     * @param ownerid
     * @param indexpath 
     */
    private void insertProjectRecord(String fp_name,String ownerid,String indexpath){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql_exist="SELECT * FROM projects Where fp_name='"+fp_name+"'";
            ResultSet rs=stmt.executeQuery(sql_exist);
            if (rs.next()) {
                System.out.println("Error: "+fp_name+" has already been inserted.There is no need to insert again.");
                stmt.close();
                dm.close();
                return;
            } 
            
            String sql = "INSERT INTO projects(fp_name,ownerid,indexpath)" +
                      " VALUES ('"+fp_name+"','"+ownerid+"','"+indexpath+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Insert "+fp_name+" successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Create a cooresponding project database when a project is added or created.
     * @param fp_name
     * @param ownerid
     * @param indexpath 
     */
    public void addProject(String fp_name,String ownerid,String indexpath){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, database, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql_exist="SELECT datname FROM pg_database WHERE datname= '"+fp_name+"'";
            ResultSet rs=stmt.executeQuery(sql_exist);
            if(rs.next()){
                System.out.println("Database already existed.");
                stmt.close();
                dm.close();
                return;
            }

            String sql="CREATE DATABASE "+fp_name;
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Create database "+fp_name+" successfully");
            insertProjectRecord(fp_name, ownerid, indexpath);
            
            dm=manager.connetToPostgre(host, port, fp_name, user, pwd);
            stmt =dm.createStatement();
            String create_layers = "CREATE TABLE IF NOT EXISTS layers" +
                      "(layer_name VARCHAR(20) PRIMARY KEY NOT NULL," +
                      "simplefeaturetype VARCHAR(50) NOT NULL,"
                    + "lockstatus VARCHAR(100) )";
            stmt.executeUpdate(create_layers);
            String create_blanklayer = "CREATE TABLE IF NOT EXISTS blanklayer" +
                      "(layer_name VARCHAR(20) PRIMARY KEY NOT NULL," +
                       "simplefeaturetype VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(create_blanklayer);
            stmt.close();
            dm.close();
            System.out.println("Layers and blanklayer created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);

        } finally{       
        }
        
    }
    /**
     * Add layers to a given project.
     * @param fp_name
     * @param layer_name
     * @param simplefeaturetype
     * @param lockstatus null or userid
     */
    public void addLayers(String fp_name,String layer_name,String simplefeaturetype,String lockstatus){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, fp_name, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql_exist="SELECT * FROM layers WHERE layer_name= '"+layer_name+"'";
            ResultSet rs=stmt.executeQuery(sql_exist);
            if(rs.next()){
                System.out.println(layer_name+" already exists. Add "+layer_name+" failed.");
                stmt.close();
                dm.close();
                return;
            }
            
            String sql = "INSERT INTO layers(layer_name,simplefeaturetype,lockstatus)" +
                      " VALUES ('"+layer_name+"','"+simplefeaturetype+"','"+lockstatus+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Add layers successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Create a blank layer.
     * @param fp_name
     * @param layer_name
     * @param simplefeaturetype 
     */
    public void newBlankLayer(String fp_name,String layer_name,String simplefeaturetype){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, fp_name, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql_exist="SELECT * FROM blanklayer WHERE layer_name= '"+layer_name+"'";
            ResultSet rs=stmt.executeQuery(sql_exist);
            if(rs.next()){
                System.out.println(layer_name+" already exists. Add "+layer_name+" failed.");
                stmt.close();
                dm.close();
                return;
            }
            
            String sql = "INSERT INTO blanklayer(layer_name,simplefeaturetype)" +
                      " VALUES ('"+layer_name+"','"+simplefeaturetype+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("New blank layer successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Delete a layer from a project.
     * @param fp_name
     * @param layer_name 
     */
    public void deleteLayer(String fp_name,String layer_name){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, fp_name, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql="DELETE FROM layers WHERE layer_name= '"+layer_name+"'";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Delete layer successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Check the lock status of the given layer
     * @param fp_name
     * @param layer_name
     * @return null or the userid
     */
    public String checkLayerLock(String fp_name,String layer_name){
        String lockstatus=null;
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, fp_name, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql="SELECT lockstatus FROM layers WHERE layer_name= '"+layer_name+"'";
            ResultSet rs=null;
            rs=stmt.executeQuery(sql);
            if (rs.next()) {
                lockstatus=rs.getString("lockstatus");
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
     * @param fp_name
     * @param layer_name
     * @param lockstatus 
     */
    public void setLayerLock(String fp_name,String layer_name,String lockstatus){
        Connection dm = null;
        Statement stmt = null;
        PostgreSQLManager manager = new PostgreSQLManager();
        dm=manager.connetToPostgre(host, port, fp_name, user, pwd);
        try {
            stmt =dm.createStatement();
            String sql="UPDATE layers SET lockstatus= '"+lockstatus+"' WHERE layer_name= '"+layer_name+"'";
            stmt.executeUpdate(sql);
            stmt.close();
            dm.close();
            System.out.println("Set lockstatus successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        String host = "localhost";
        String port = "5432";
        String database = "test";
        String user = "postgres";
        String pwd = "123";
        DBManager dbm=new DBManager(host,port,database,user,pwd);
        
//        dbm.signup("yangliu", "123");
        
        //boolean flag=dbm.verifyLogin("yangliu", "123");
        //System.out.println(flag);
        
        //dbm.addProject("fp1", "yangliu", "c:/");

        dbm.inviteProjectUser("fp1", "yangliu");
//        dbm.inviteProjectUser("fp2", "yangliu");
//        dbm.inviteProjectUser("fp2", "yl");

//        List<String> userList=dbm.queryUserbyProject("fp2");
//        System.out.println(userList);
//        List<String> projectList=dbm.queryProjectbyUser("yangliu");
//        System.out.println(projectList);

//        dbm.addProject("fp1", "yangliu", "c:/");
//          dbm.insertProjectRecord("fp1", "yangliu", "c:/");
//          dbm.addProject("fp2", "yangliu", "c:/");
//dbm.addLayers("fp1", "layer1", "location", "yangliu");
//String status=dbm.checkLayerLock("fp1", "layer1");
//        System.out.println(status);
//dbm.newBlankLayer("fp1", "blanklayer", "location");
//dbm.deleteLayer("fp1", "layer1");
//dbm.setLayerLock("fp1", "layer1", null);
//dbm.addProject("fp1", "yl", "path");

    }
}
