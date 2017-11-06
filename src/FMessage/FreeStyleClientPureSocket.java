/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import cn.edu.pku.datasource.FreestyleUser;
import cn.edu.pku.gui.FreestyleClientSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author sq
 */
public class FreeStyleClientPureSocket {

    private DefaultListModel listModel;
    private boolean isConnected = false;
    public ArrayList<String> ListUser;

    private String ThisName;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private MessageThread messageThread;// 负责接收消息的线程  
    private Map<String, FreestyleUser> onlineUsers = new HashMap<>();// 所有在线用户  

    /**
     * 客户端消息处理器
     */
    public FreeStyleClientMessageHandler clientMessageHandler;

    /**
     * 客户端消息产生器
     */
    public FreeStyleClientMessageCreator clientMessageCreator;

    /**
     * 客户端消息解析器
     */
    public FreeStyleClientMessageParser clientMessageParser;

    /**
     * 客户端消息ID池
     */
    public TransmittedMessageIDPool clientMessageIDPool;

    /**
     * Creates new FreeStylePureClientSocket
     */
    public FreeStyleClientPureSocket() {
        this.ListUser = new ArrayList<String>();
        listModel = new DefaultListModel();
        ThisName = "FreeStyleUser";
    }

    public void ownerChanged(String owner) {
        clientMessageHandler = new FreeStyleClientMessageHandler(owner);
        clientMessageCreator = new FreeStyleClientMessageCreator(owner);
        clientMessageParser = new FreeStyleClientMessageParser(owner);
        clientMessageIDPool = new TransmittedMessageIDPool(owner);
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(String message) {
        if (!isConnected) {
            System.out.println("还没有连接服务器，无法发送消息！");
            return;
        }
        if (message == null || message.equals("")) {
            System.out.println("消息不能为空！");
            return;
        }
        sendMessage(message);
    }

    /**
     * 断开连接
     */
    public void disconnectActionPerformed() {
        // TODO add your handling code here:
        if (!isConnected) {
            System.out.println("已处于断开状态，不要重复断开!");
            return;
        }
        try {
            boolean flag = closeConnection();// 断开连接  
            if (flag == false) {
                throw new Exception("断开连接发生异常！");
            }
            System.out.println("成功断开!");
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * 建立连接
     *
     * @param id
     * @param hostip
     * @param port
     * @param name
     */
    public void connectActionPerformed(String id, String hostip, String port, String name) {
        // TODO add your handling code here:
        int sPort;
        if (isConnected) {
            System.out.println("已处于连接上状态，不要重复连接!");
            return;
        }
        try {
            try {
                sPort = Integer.parseInt(port.trim());
            } catch (NumberFormatException e2) {
                throw new Exception("端口号不符合要求!端口为整数!");
            }
            String sId = id.trim();
            String sHostIp = hostip.trim();
            String sName = name.trim();
            if (name.equals("") || sHostIp.equals("")) {
                throw new Exception("姓名、服务器IP不能为空!");
            }
            boolean flag = connectServer(sPort, sId, sName, sHostIp);
            if (flag == false) {
                throw new Exception("与服务器连接失败!");
            } else {
                this.ThisName = sName;
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * 消息关闭
     */
    public void processClosing() {
        // TODO add your handling code here:
        if (isConnected) {
            closeConnection();// 关闭连接  
        }
        System.exit(0);// 退出程序  �����  
    }

    /**
     * 连接服务器
     *
     * @param port
     * @param id
     * @param name
     * @param hostIp
     * @return
     */
    public boolean connectServer(int port, String id, String name, String hostIp) {
        // 连接服务器  
        try {
            socket = new Socket(hostIp, port);// 根据端口号和服务器ip建立连接  
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            // 发送客户端用户基本信息(用户名和ip地址)  
            // sendMessage(id + "@" + name + "@" + socket.getLocalAddress().toString());  
            TransmittedMessage tm = clientMessageCreator.helloHello("FreeStyleServer", clientMessageIDPool.getOneRandomID(name), name, socket.getLocalAddress().toString(),"ADD");
            System.out.println(tm.convertMessageToString());
            sendMessage(tm.convertMessageToString());
            // 开启接收消息的线程  
            messageThread = new MessageThread(reader);
            messageThread.start();
            isConnected = true;// 已经连接上了  
            //ownerChanged(name);
            return true;
        } catch (Exception e) {
            System.out.println("与端口号为：" + port + "    IP地址为：" + hostIp
                    + "   的服务器连接失败!" + "\r\n");
            isConnected = false;// 未连接上  
            return false;
        }
    }

    private void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    public synchronized boolean closeConnection() {
        try {
            sendMessage("CLOSE");// 发送断开连接命令给服务器  
            messageThread.stop();// 停止接受消息线程  
            // 释放资源  
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
            isConnected = false;
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
            isConnected = true;
            return false;
        }
    }

    class MessageThread extends Thread {

        private BufferedReader reader;
        //private JTextArea textArea;  

        // 接收消息线程的构造方法  
        public MessageThread(BufferedReader reader) {
            this.reader = reader;
            //this.textArea = textArea;  
        }

        // 被动的关闭连接  
        public synchronized void closeCon() throws Exception {
            // 清空用户列表  
            listModel.removeAllElements();
            // 被动的关闭连接释放资源  
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
            isConnected = false;// 修改状态为断开  
        }

        public void run() {
            String message = "";
            while (true) {
                try {
                    message = reader.readLine();
                    /*
                    StringTokenizer stringTokenizer = new StringTokenizer(
                            message, "/@");
                    String command = stringTokenizer.nextToken();// 命令  
                    if (command.equals("CLOSE"))// 服务器已关闭命令  
                    {
                        System.out.println("服务器已关闭!\r\n");
                        closeCon();// 被动的关闭连接  
                        return;// 结束线程  
                    } else if (command.equals("ADD")) {// 有用户上线更新在线列表  
                        String userid = "";
                        String username = "";
                        String userIp = "";
                        if ((userid = stringTokenizer.nextToken()) != null
                                && (username = stringTokenizer.nextToken()) != null
                                && (userIp = stringTokenizer.nextToken()) != null) {
                            FreestyleUser user = new FreestyleUser(userid, username, userIp);
                            onlineUsers.put(userid, user);
                            listModel.addElement(username);
                        }
                    } else if (command.equals("DELETE")) {// 有用户下线更新在线列表  
                        String userid = stringTokenizer.nextToken();
                        String username = stringTokenizer.nextToken();
                        FreestyleUser user = (FreestyleUser) onlineUsers.get(userid);
                        onlineUsers.remove(userid, user);
                        listModel.removeElement(username);
                    } else if (command.equals("USERLIST")) {// 加载在线用户列表  
                        int size = Integer
                                .parseInt(stringTokenizer.nextToken());
                        String userid = null;
                        String username = null;
                        String userIp = null;
                        for (int i = 0; i < size; i++) {
                            userid = stringTokenizer.nextToken();
                            username = stringTokenizer.nextToken();
                            userIp = stringTokenizer.nextToken();
                            FreestyleUser user = new FreestyleUser(userid, username, userIp);
                            onlineUsers.put(userid, user);
                            listModel.addElement(username);
                        }
                    } else if (command.equals("MAX")) {// 人数已达上限  
                        System.out.println(stringTokenizer.nextToken()
                                + stringTokenizer.nextToken() + "\r\n");
                        closeCon();// 被动的关闭连接  
                        //JOptionPane.showMessageDialog(this.textArea, "服务器缓冲区已满！", "错误",  JOptionPane.ERROR_MESSAGE);  
                        return;// 结束线程  
                    } else {// 普通消息  
                        /*
                        TODO
                        
                        
                        
                        
                         */
                        System.out.println(PostProcessMessage(TransmittedMessage.convertStringToMessage(message)));
                        System.out.println(message + "\r\n");
                   // }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public String PostProcessMessage(TransmittedMessage transMsg) {
            try {
                return clientMessageParser.instructionClassification_2(transMsg).toString();
            } catch (Exception err) {
                err.printStackTrace();
                return "Error";
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FreestyleClientSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FreestyleClientSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FreestyleClientSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FreestyleClientSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FreeStyleClientPureSocket fclient =  new FreeStyleClientPureSocket();
                fclient.ownerChanged("sq");
                fclient.connectActionPerformed("sq", "10.128.176.234", "8000", "sq");
            }
        });
    }

}
