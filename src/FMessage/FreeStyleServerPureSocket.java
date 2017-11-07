/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import FMessage.FreeStyleServerMessageHandler;
import cn.edu.pku.datasource.DBManager;
import cn.edu.pku.datasource.FreestyleUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author sq
 */
public class FreeStyleServerPureSocket {

    private ServerSocket serverSocket;
    private ServerChatThread serverThread;
    private ArrayList<ClientChatThread> clients;
    private DefaultListModel listModel;
    private DBManager dbManager;

    private boolean isStart = false;

    /**
     * 服务器消息构造器
     */
    public FreeStyleServerMessageCreator serverMessageCreator;

    /**
     * 服务器消息处理器
     */
    public FreeStyleServerMessageHandler serverMessageHandler;

    /**
     * 服务器消息解析器
     */
    public FreeStyleServerMessageParser serverMessageParser;

    /**
     * 服务器消息ID池
     */
    public TransmittedMessageIDPool serverMessageIDPool;

    /**
     * Creates new form FreestyleServerSocket
     */
    public FreeStyleServerPureSocket(DBManager dbmanager) {
        listModel = new DefaultListModel();
        dbManager = dbmanager;
        serverMessageHandler = new FreeStyleServerMessageHandler("FreeStyleServer", dbManager);
        serverMessageCreator = new FreeStyleServerMessageCreator("FreeStyleServer");
        serverMessageParser = new FreeStyleServerMessageParser("FreeStyleServer");
        serverMessageIDPool = new TransmittedMessageIDPool("FreeStyleServer");
    }

    /**
     * 进程关闭
     */
    public void processClosing() {
        // TODO add your handling code here:
        if (isStart) {
            closeServer();
        } else {
            System.exit(0);
        }
    }

    /**
     * 开始服务
     *
     * @param maxUserCount
     * @param sPort
     */
    public void startActionPerformed(int maxUserCount, String sPort) {
        // TODO add your handling code here:
        if (isStart) {
            System.out.println("服务器已处于启动状态，不要重复启动！");
            return;
        }
        int max;
        int port;
        try {
            try {
                max = maxUserCount; //待修改
            } catch (Exception e1) {
                throw new Exception("人数上限为正整数！");
            }
            if (max <= 0) {
                throw new Exception("人数上限为正整数！");
            }
            try {
                port = Integer.parseInt(sPort);
            } catch (Exception e1) {
                throw new Exception("端口号为正整数！");
            }
            if (port <= 0) {
                throw new Exception("端口号为正整数！");
            }
            serverStart(max, port);
            System.out.println("服务器已成功启动!人数上限：" + max + ",端口：" + port + "\r\n");
            System.out.println("服务器成功启动!");
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * 停止服务
     */
    public void stopActionPerformed() {
        // TODO add your handling code here:
        if (!isStart) {
            System.out.println("服务器还未启动，无需停止！");
            return;
        }
        try {
            closeServer();
            System.out.println("服务器成功停止!\r\n");
            System.out.println("服务器成功停止！");
        } catch (Exception exc) {
            System.out.println("停止服务器发生异常！");
        }
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessages(String msg) {
        if (!isStart) {
            System.out.println("服务器还未启动,不能发送消息！");
            return;
        }
        if (clients.size() == 0) {
            System.out.println("没有用户在线,不能发送消息！");
            return;
        }
        String message = msg.trim();
        if (message == null || message.equals("")) {
            System.out.println("消息不能为空！");
            return;
        }
        //sendServerMessage(message);// 群发服务器消息  
    }

    /**
     * 发送服务器消息(群发)
     *
     * @param message
     */
    public void sendServerMessage(String message) {
        for (int i = clients.size() - 1; i >= 0; i--) {
            //TO DO 
            clients.get(i).getWriter().println(message);
            clients.get(i).getWriter().flush();
        }
    }

    private void closeServer() {
        try {
            if (serverThread != null) {
                serverThread.stop();// 停止服务器线程  
            }
            for (int i = clients.size() - 1; i >= 0; i--) {
                // 给所有在线用户发送关闭命令  
                clients.get(i).getWriter().println("CLOSE");
                clients.get(i).getWriter().flush();
                // 释放资源  
                clients.get(i).stop();// 停止此条为客户端服务的线程  
                clients.get(i).reader.close();
                clients.get(i).writer.close();
                clients.get(i).socket.close();
                clients.remove(i);
            }
            if (serverSocket != null) {
                serverSocket.close();// 关闭服务器端连接  
            }
            listModel.removeAllElements();// 清空用户列表  
            isStart = false;
        } catch (IOException e) {
            e.printStackTrace();
            isStart = true;
        }

    }

    private void serverStart(int max, int port) throws BindException {
        try {
            clients = new ArrayList<>();
            serverSocket = new ServerSocket(port);
            serverThread = new ServerChatThread(serverSocket, max);
            serverThread.start();
            isStart = true;
            System.out.println("FMessage.FreeStyleServerPureSocket.serverStart()");
        } catch (BindException e) {
            isStart = false;
            throw new BindException("端口号已被占用，请换一个！");
        } catch (Exception e1) {
            e1.printStackTrace();
            isStart = false;
            throw new BindException("启动服务器异常！");
        }
    }

    class ServerChatThread extends Thread {

        private ServerSocket serverSocket;
        private int maxUsers;

        public ServerChatThread(ServerSocket sSocket, int maxUsers) {
            this.serverSocket = sSocket;
            this.maxUsers = maxUsers;
        }

        public void run() {
            while (true) {// 不停的等待客户端的链接  
                try {
                    Socket socket = serverSocket.accept();
                    if (clients.size() == maxUsers) {// 如果已达人数上限  
                        BufferedReader r = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        PrintWriter w = new PrintWriter(socket
                                .getOutputStream());
                        // 接收客户端的基本用户信息  
                        String inf = r.readLine();
                        TransmittedMessage tm = TransmittedMessage.convertStringToMessage(inf);

                        HashMap<String, Object> sdata = tm.getData();
                        //StringTokenizer st = new StringTokenizer(inf, "@");  
                        FreestyleUser user = new FreestyleUser(sdata.get("UserName").toString(), sdata.get("UserName").toString(), sdata.get("IP").toString());
                        // 反馈连接成功信息  
                        /*
                        w.println("MAX@服务器：对不起，" + user.getUserName()
                                + user.getUserIP() + "，服务器在线人数已达上限，请稍后尝试连接！");  
                        w.flush();  */
                        // 释放资源  
                        r.close();
                        w.close();
                        socket.close();
                        continue;
                    }
                    ClientChatThread client = new ClientChatThread(socket);
                    client.start();// 开启对此客户端服务的线程  
                    clients.add(client);
                    listModel.addElement(client.getUser().getUserName());// 更新在线列表  
                    System.out.println(client.getUser().getUserName()
                            + client.getUser().getUserIP() + "上线!\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FreeStyleServerPureSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class ClientChatThread extends Thread {

        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private FreestyleUser user;

        public BufferedReader getReader() {
            return reader;
        }

        public PrintWriter getWriter() {
            return writer;
        }

        public FreestyleUser getUser() {
            return user;
        }

        // 客户端线程的构造方法  
        public ClientChatThread(Socket socket) {
            try {
                this.socket = socket;
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
                // 接收客户端的基本用户信息  
                String inf = reader.readLine();
                //StringTokenizer st = new StringTokenizer(inf, "@");  
                TransmittedMessage tm = TransmittedMessage.convertStringToMessage(inf);
                HashMap<String, Object> sdata = tm.getData();

                user = new FreestyleUser(sdata.get("UserName").toString(), sdata.get("UserName").toString(), sdata.get("IpPort").toString());
                // 反馈连接成功信息  
                TransmittedMessage rm = serverMessageHandler.helloHello(tm);
                writer.println(rm.convertMessageToString());
                writer.flush();
                // 反馈当前在线用户信息  
                /*
                if (clients.size() > 0) {  
                    String temp = "";  
                    for (int i = clients.size() - 1; i >= 0; i--) {  
                        temp += (clients.get(i).getUser().getUserID() + "/" + clients
                                .get(i).getUser().getUserName() + "/" + clients  
                                .get(i).getUser().getUserIP())  
                                + "@";  
                    }  
                    writer.println("USERLIST@" + clients.size() + "@" + temp);  
                    writer.flush();  
                }  
                // 向所有在线用户发送该用户上线命令  
                for (int i = clients.size() - 1; i >= 0; i--) {  
                    clients.get(i).getWriter().println(  
                            "ADD@" + user.getUserID() + "/" + user.getUserName() + "/" + user.getUserIP());  
                    clients.get(i).getWriter().flush();  
                
                }  */
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(FreeStyleServerPureSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @SuppressWarnings("deprecation")
        public void run() {// 不断接收客户端的消息，进行处理。  
            String message = null;
            while (true) {
                try {
                    message = reader.readLine();// 接收客户端消息  
                    if (message.equals("")) {
                        continue;
                    }
                    if (message.equals("CLOSE"))// 下线命令  
                    {
                        System.out.println(this.getUser().getUserName()
                                + this.getUser().getUserIP() + "下线!\r\n");
                        // 断开连接释放资源  
                        reader.close();
                        writer.close();
                        socket.close();

                        // 向所有在线用户发送该用户的下线命令  
                        /*
                        for (int i = clients.size() - 1; i >= 0; i--) {  
                            clients.get(i).getWriter().println(  
                                    "DELETE@" + user.getUserID() + "/" + user.getUserName());  
                            clients.get(i).getWriter().flush();  
                        }  */
                        listModel.removeElement(this.getUser().getUserName());

                        // 删除此条客户端服务线程  
                        for (int i = clients.size() - 1; i >= 0; i--) {
                            if (clients.get(i).getUser().getUserID().equals(user.getUserID())) {
                                ClientChatThread temp = clients.get(i);
                                clients.remove(i);// 删除此用户的服务线程  
                                temp.stop();// 停止这条服务线程  
                                return;
                            }
                        }
                    } else {
                        dispatcherMessage(message);// 转发消息  
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 转发消息  
        public void dispatcherMessage(String message) {
            /*StringTokenizer stringTokenizer = new StringTokenizer(message, "@");  
            String source = stringTokenizer.nextToken();  
            String owner = stringTokenizer.nextToken();  
            String content = stringTokenizer.nextToken();  
            message = source + "说：" + content;  
            System.out.println(message + "\r\n");  
            if (owner.equals("ALL")) {// 群发  
                for (int i = clients.size() - 1; i >= 0; i--) {  
                    clients.get(i).getWriter().println(message + "(多人发送)");  
                    clients.get(i).getWriter().flush();  
                }  
            }  */
            try {
                System.out.println("ServerPureSocket:\r\n" + message);
                TransmittedMessage transMsg = TransmittedMessage.convertStringToMessage(message);
                TransmittedMessage returnMsg = serverMessageHandler.instructionClassification(transMsg);
                System.out.println("ServerPureSocket\tReturnMsg:\r\n" + returnMsg.convertMessageToString());
                for (int i = clients.size() - 1; i >= 0; i--) {
                    if (clients.get(i).user.getUserID().equals(returnMsg.getReceiver())) {
                        clients.get(i).getWriter().println(returnMsg.convertMessageToString());
                        clients.get(i).getWriter().flush();
                    }

                }
            } catch (Exception err) {

            }
        }

        public void processMessage(String message) {

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DBManager dbmanager = new DBManager("localhost", "5432", "freestyleUser", "postgres", "123");
                FreeStyleServerPureSocket sps = new FreeStyleServerPureSocket(dbmanager);
                sps.startActionPerformed(100, "8000");
            }
        });
    }

}
