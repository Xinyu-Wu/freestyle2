/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

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
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author wuxinyu
 */
public class FreestyleServerSocket extends javax.swing.JFrame {
    private ServerSocket serverSocket;  
    private ServerChatThread serverThread;  
    private ArrayList<ClientChatThread> clients;  
    private DefaultListModel listModel;
    private DBManager dbManager;
  
    private boolean isStart = false;  
    
    private FreeStyleServerMessageHandler freeStyleServerMessageHandler;
    
    
    /**
     * Creates new form FreestyleServerSocket
     */
    public FreestyleServerSocket() {
        listModel = new DefaultListModel();
        initComponents();
        ListUsers.setModel(listModel);
        dbManager=new DBManager("10.128.176.234","5433","gis_20170922","sunqi","sunqi");
        freeStyleServerMessageHandler= new FreeStyleServerMessageHandler("FreeStyleServer",dbManager);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PaneConfig = new javax.swing.JPanel();
        LabelPort = new javax.swing.JLabel();
        TextFieldPort = new javax.swing.JTextField();
        ButtonStart = new javax.swing.JButton();
        ButtonStop = new javax.swing.JButton();
        PanelUserList = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListUsers = new javax.swing.JList<>();
        PanelChat = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextChatMessages = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        ServerChatEditor = new javax.swing.JEditorPane();
        ButtonSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PaneConfig.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PaneConfig.setToolTipText("");

        LabelPort.setText("�˿ںţ�");

        TextFieldPort.setText("8000");

        ButtonStart.setText("Start");
        ButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStartActionPerformed(evt);
            }
        });

        ButtonStop.setText("Stop");
        ButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneConfigLayout = new javax.swing.GroupLayout(PaneConfig);
        PaneConfig.setLayout(PaneConfigLayout);
        PaneConfigLayout.setHorizontalGroup(
            PaneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(ButtonStart)
                .addGap(34, 34, 34)
                .addComponent(ButtonStop)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PaneConfigLayout.setVerticalGroup(
            PaneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaneConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPort)
                    .addComponent(TextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonStart)
                    .addComponent(ButtonStop))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelUserList.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jScrollPane4.setViewportView(ListUsers);

        javax.swing.GroupLayout PanelUserListLayout = new javax.swing.GroupLayout(PanelUserList);
        PanelUserList.setLayout(PanelUserListLayout);
        PanelUserListLayout.setHorizontalGroup(
            PanelUserListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUserListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelUserListLayout.setVerticalGroup(
            PanelUserListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUserListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelChat.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        TextChatMessages.setEditable(false);
        TextChatMessages.setColumns(20);
        TextChatMessages.setRows(5);
        jScrollPane2.setViewportView(TextChatMessages);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setViewportView(ServerChatEditor);

        ButtonSend.setText("Send");
        ButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelChatLayout = new javax.swing.GroupLayout(PanelChat);
        PanelChat.setLayout(PanelChatLayout);
        PanelChatLayout.setHorizontalGroup(
            PanelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addGroup(PanelChatLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonSend)))
                .addContainerGap())
        );
        PanelChatLayout.setVerticalGroup(
            PanelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(ButtonSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PaneConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelUserList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelUserList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(isStart)
            closeServer();
        else
            System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void ButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSendActionPerformed
        // TODO add your handling code here:
        sendMessages();
    }//GEN-LAST:event_ButtonSendActionPerformed

    private void ButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStartActionPerformed
        // TODO add your handling code here:
        if (isStart) {  
            JOptionPane.showMessageDialog(this, "服务器已处于启动状态，不要重复启动！",  
                    "错误", JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        int max;  
        int port;  
        try {  
            try {  
                max = Integer.parseInt(TextFieldPort.getText());  //待修改
            } catch (Exception e1) {  
                throw new Exception("人数上限为正整数！");  
            }  
            if (max <= 0) {  
                throw new Exception("人数上限为正整数！");  
            }  
            try {  
                port = Integer.parseInt(TextFieldPort.getText());  
            } catch (Exception e1) {  
                throw new Exception("端口号为正整数！");  
            }  
            if (port <= 0) {  
                throw new Exception("端口号为正整数！");  
            }  
            serverStart(max, port);  
            TextChatMessages.append("服务器已成功启动!人数上限：" + max + ",端口：" + port  
                    + "\r\n");  
            JOptionPane.showMessageDialog(this, "服务器成功启动!");  
            ButtonStart.setEnabled(false);
            TextFieldPort.setEnabled(false);  
            ButtonStop.setEnabled(true);  
        } catch (Exception exc) {  
            JOptionPane.showMessageDialog(this, exc.getMessage(),  
                    "错误", JOptionPane.ERROR_MESSAGE);  
        }  
    }//GEN-LAST:event_ButtonStartActionPerformed

    private void ButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStopActionPerformed
        // TODO add your handling code here:
        if (!isStart) {  
            JOptionPane.showMessageDialog(this, "服务器还未启动，无需停止！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        try {  
            closeServer();  
            ButtonStart.setEnabled(true);  
            TextFieldPort.setEnabled(true);  
            ButtonStop.setEnabled(false);  
            TextChatMessages.append("服务器成功停止!\r\n");  
            JOptionPane.showMessageDialog(this, "服务器成功停止！");  
        } catch (Exception exc) {  
            JOptionPane.showMessageDialog(this, "停止服务器发生异常！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
        }  
    }//GEN-LAST:event_ButtonStopActionPerformed
    
    public void sendMessages(){
        if (!isStart) {  
            JOptionPane.showMessageDialog(this, "服务器还未启动,不能发送消息！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        if (clients.size() == 0) {  
            JOptionPane.showMessageDialog(this, "没有用户在线,不能发送消息！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        String message = ServerChatEditor.getText().trim();
        if (message == null || message.equals("")) {  
            JOptionPane.showMessageDialog(this, "消息不能为空！", "错误",  
                    JOptionPane.ERROR_MESSAGE);  
            return;  
        }  
        sendServerMessage(message);// 群发服务器消息  
        TextChatMessages.append("服务器说：" + ServerChatEditor.getText() + "\r\n");  
        ServerChatEditor.setText(null);  
    }

    private void sendServerMessage(String message) {
        for (int i = clients.size() - 1; i >= 0; i--) {  
            clients.get(i).getWriter().println("服务器：" + message + "(多人发送)");  
            clients.get(i).getWriter().flush();  
        }  
    }

    private void closeServer() {
        try {  
            if (serverThread != null)  
                serverThread.stop();// 停止服务器线程  
  
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
        } catch (BindException e) {  
            isStart = false;  
            throw new BindException("端口号已被占用，请换一个！");  
        } catch (Exception e1) {  
            e1.printStackTrace();  
            isStart = false;  
            throw new BindException("启动服务器异常！");  
        }  
    }
    
    class ServerChatThread extends Thread{
        private ServerSocket serverSocket;
        private int maxUsers;
        
        public ServerChatThread(ServerSocket sSocket, int maxUsers){
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
                        StringTokenizer st = new StringTokenizer(inf, "@");  
                        FreestyleUser user = new FreestyleUser(st.nextToken(), st.nextToken(), st.nextToken());  
                        // 反馈连接成功信息  
                        w.println("MAX@服务器：对不起，" + user.getUserName()
                                + user.getUserIP() + "，服务器在线人数已达上限，请稍后尝试连接！");  
                        w.flush();  
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
                    TextChatMessages.append(client.getUser().getUserName()
                            + client.getUser().getUserIP() + "上线!\r\n");  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }
    }
    
    class ClientChatThread extends Thread{
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
                StringTokenizer st = new StringTokenizer(inf, "@");  
                user = new FreestyleUser(st.nextToken(), st.nextToken(), st.nextToken());  
                // 反馈连接成功信息  
                writer.println(user.getUserName() + user.getUserIP() + "与服务器连接成功!");  
                writer.flush();  
                // 反馈当前在线用户信息  
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
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
  
        @SuppressWarnings("deprecation")  
        public void run() {// 不断接收客户端的消息，进行处理。  
            String message = null;  
            while (true) {  
                try {  
                    message = reader.readLine();// 接收客户端消息  
                    if (message.equals("CLOSE"))// 下线命令  
                    {  
                        TextChatMessages.append(this.getUser().getUserName()
                                + this.getUser().getUserIP() + "下线!\r\n");  
                        // 断开连接释放资源  
                        reader.close();  
                        writer.close();  
                        socket.close();  
  
                        // 向所有在线用户发送该用户的下线命令  
                        for (int i = clients.size() - 1; i >= 0; i--) {  
                            clients.get(i).getWriter().println(  
                                    "DELETE@" + user.getUserID() + "/" + user.getUserName());  
                            clients.get(i).getWriter().flush();  
                        }  
  
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
            StringTokenizer stringTokenizer = new StringTokenizer(message, "@");  
            String source = stringTokenizer.nextToken();  
            String owner = stringTokenizer.nextToken();  
            String content = stringTokenizer.nextToken();  
            message = source + "说：" + content;  
            TextChatMessages.append(message + "\r\n");  
            if (owner.equals("ALL")) {// 群发  
                for (int i = clients.size() - 1; i >= 0; i--) {  
                    clients.get(i).getWriter().println(message + "(多人发送)");  
                    clients.get(i).getWriter().flush();  
                }  
            }  
        }  
        
        public void processMessage(String message)
        {
            
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
            java.util.logging.Logger.getLogger(FreestyleServerSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FreestyleServerSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FreestyleServerSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FreestyleServerSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FreestyleServerSocket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonSend;
    private javax.swing.JButton ButtonStart;
    private javax.swing.JButton ButtonStop;
    private javax.swing.JLabel LabelPort;
    private javax.swing.JList<String> ListUsers;
    private javax.swing.JPanel PaneConfig;
    private javax.swing.JPanel PanelChat;
    private javax.swing.JPanel PanelUserList;
    private javax.swing.JEditorPane ServerChatEditor;
    private javax.swing.JTextArea TextChatMessages;
    private javax.swing.JTextField TextFieldPort;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
