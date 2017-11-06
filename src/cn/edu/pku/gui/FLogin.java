/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author 冯雨宁
 */
public class FLogin extends javax.swing.JFrame implements ActionListener{
    //定义组件
    JPanel jp1,jp2,jp3;//面板
    JLabel jlb1,jlb2;//标签
    JButton jbtnRegister, jbtnLogin, jbtnReset;//按钮
    JTextField jtfID;//文本
    JPasswordField jpf;//密码
    
    /**
     * Creates new form login
     */
    //构造函数
    public FLogin() {
        initComponents();
        //创建面板 
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        
        //创建标签
        jlb1=new JLabel("用户名");
        jlb2=new JLabel("密   码");
        
        //创建按钮
        jbtnRegister= new JButton("登录");
        jbtnLogin=new JButton("注册");
        jbtnReset=new JButton("重置");
        
        //创建文本框
        jtfID=new JTextField(20);
      
        //创建密码框
        jpf=new JPasswordField(20);
        
        //设置布局管理
        this.setLayout(new GridLayout(6,1));
        
        //加入组件
        jp1.add(jlb1);
        jp1.add(jtfID);
        
        jp2.add(jlb2);
        jp2.add(jpf);
        
        jp3.add(jbtnRegister);
        jp3.add(jbtnLogin);
        jp3.add(jbtnReset);
        
        //加入到JFrame
        this.add(new JPanel(null));
        this.add(jp1);
        this.add(jp2);
        this.add(new JPanel(null));
        this.add(jp3);
        this.add(new JPanel(null));

        //设置窗体
        this.setTitle("Welcome!");
        this.setSize(350,230);
        this.setLocationRelativeTo(null);//屏幕中间显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭
        this.setVisible(true);
        
        //锁定窗体
        this.setResizable(false);   
        
        //按钮监听
        jbtnLogin.addActionListener(this);
        jbtnRegister.addActionListener(this);
        jbtnReset.addActionListener(this);
        
        //键盘回车监听
        KeyListener keyListener_login=new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyReleased(KeyEvent e){}
            public void keyPressed(KeyEvent e){
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                {
                    try {
                        login();
                    } catch (Exception ex) {
                        Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        KeyListener keyListener_moveon=new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyReleased(KeyEvent e){}
            public void keyPressed(KeyEvent e){
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                {
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
            }
        };
        
        jtfID.addKeyListener(keyListener_moveon);
        jpf.addKeyListener(keyListener_login);
        
    }

    public static void initGlobalFontSetting(Font font){
        FontUIResource fontRes = new FontUIResource(font);
        for(Enumeration keys = UIManager.getDefaults().keys();keys.hasMoreElements();){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
            UIManager.put(key, fontRes);
        }
    }
    
    
    
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand())
        {
            case "登录":
        {
            try {
                login();
            } catch (Exception ex) {
                Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case "注册":
                register();
                break;
            case "重置":
                clear();
                break;
        }
    }
    
    public void login() throws Exception{
        System.out.println("cn.edu.pku.gui.FLogin.login()");
        if(userLogin(jtfID.getText(),jpf.getText()))
        {
            JOptionPane.showMessageDialog(null,"登录成功！","FreeStyle",JOptionPane.INFORMATION_MESSAGE);
            clear();
            //关闭当前界面
            dispose();
            //进入主界面
            Main_win fMain=new Main_win();
            fMain.setVisible(true);   
        }
        else if(jtfID.getText().isEmpty()&& jpf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入用户名与密码!","FreeStyle",JOptionPane.WARNING_MESSAGE);
        }
        else if(jtfID.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入用户名!","FreeStyle",JOptionPane.WARNING_MESSAGE);
        }
        else if(jpf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入密码!","FreeStyle",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"用户名或密码错误！\n 请重新输入！","FreeStyle",JOptionPane.ERROR_MESSAGE);
            clear();
        }
        
    }
    
    public void register(){
        System.out.println("cn.edu.pku.gui.FLogin.register()");
    }
    
    public void clear(){
        jtfID.setText("");
        jpf.setText("");
        System.out.println("cn.edu.pku.gui.FLogin.clear()");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FLogin fLogin=new FLogin();
                fLogin.setVisible(true);
            }
        });
    }

    private boolean userLogin(String id,String password) {
        HashMap<String,String> sUserPwd = new HashMap<>();
        sUserPwd.put("1", "1");
        sUserPwd.put("sq", "sq");
        sUserPwd.put("ccy", "ccy");
        sUserPwd.put("yl", "yl");
        sUserPwd.put("fyn", "fyn");
        sUserPwd.put("wxy", "wxy");
        return sUserPwd.keySet().contains(id) && password.equals(sUserPwd.get(id));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
