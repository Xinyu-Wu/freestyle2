/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author wuxinyu
 */
public class OBFrame extends javax.swing.JFrame implements Runnable{
    private Socket socket;
    private HashMap<String, String> configMap;
    
    /**
     * Creates new form OBFrame
     */
    public OBFrame(HashMap<String, String> configs) {
        try {
            this.configMap = configs;
            this.socket = new Socket(configMap.get("ip"), Integer.parseInt(configMap.get("port_download")));
//            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
            }
            });
            initComponents();
        } catch (IOException ex) {
            Logger.getLogger(OBFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g){
        ImageObserver imageObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int i, int i1, int i2, int i3, int i4) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                return false;
            }
        };
        try {
            BufferedImage tempImage = null;
            if(!socket.isClosed()){
                InputStream is = socket.getInputStream();
                tempImage = ImageIO.read(is);
            }
            else
                return;
            if(tempImage !=null){
                g.clearRect(0, 0, this.getWidth(), this.getHeight());
                g.drawImage(tempImage, 0, 0, imageObserver);
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(OBFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(980, 800));
        setSize(new java.awt.Dimension(980, 800));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(OBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        HashMap<String, String> defaultConfig = new HashMap<>();
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                defaultConfig.put("ip", "127.0.0.1");
//                defaultConfig.put("port_upload", "8001");
//                defaultConfig.put("port_download", "8002");
//                new OBFrame(defaultConfig).setVisible(true);
//            }
//        });
//        OBFrame OB = new OBFrame(defaultConfig);
//        OB.setVisible(true);
//        OB.run();
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void run(){
        while (true) {        
            repaint();
            try {
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addWindowListener(new WindowAdapter() {
                @Override
                    public void windowClosing(WindowEvent e) {
                        setVisible(false);
//                        System.exit(0);
                    }
                });
                sleep(3000);
                this.socket = new Socket(configMap.get("ip"), Integer.parseInt(configMap.get("port_download")));
            } catch (InterruptedException e) {
            } catch (IOException ex) {
                Logger.getLogger(OBFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}