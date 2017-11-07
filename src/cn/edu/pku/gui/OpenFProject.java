/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import FMessage.FreeStyleClientPureSocket;
import FMessage.TransmittedMessage;
import FProject.FProject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import net.sf.json.JSONArray;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;

/**
 *
 * @author 冯雨宁
 */
public class OpenFProject extends javax.swing.JFrame {

    DefaultListModel dlm = new DefaultListModel();
    Main_win main;
    FreeStyleClientPureSocket mSocket;
    /**
     * Creates new form OpenFProject
     */
    public OpenFProject(Main_win fMain,FreeStyleClientPureSocket sSocket) {
        initComponents();
        this.setLocationRelativeTo(null);
        mSocket = sSocket;
        sSocket.clientMessageParser.fOpenFProject= this;
        this.setVisible(true);
        main=fMain;
    }
    public void setList(){
        jList1.setModel(dlm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FreeStyle");

        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Your projects list：");

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnOpen.setText("Open");
        btnOpen.setToolTipText("");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnOpen)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        CreateFProject cfp=new CreateFProject(main);
        this.dispose();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:

        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:

        main.setVisible(true);
        String name=jList1.getSelectedValue();
        String title= "FreeStyle-"+name+ ".prj";
        main.setTitle(title);
        dispose();
        selectProject(name);
        
//        main.mFProject=new FProject();
        
        main.setVisible(true);
    }//GEN-LAST:event_btnOpenActionPerformed

     public boolean selectProject(String project) {

        String messageID = mSocket.clientMessageIDPool.getOneRandomID(project);
        try {
            TransmittedMessage tm = mSocket.clientMessageCreator.GetProjectContetnt("FreeStyleServer", messageID, project);
            mSocket.send(tm.convertMessageToString());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean selectProjectReceive(TransmittedMessage tm) {
        HashMap<String, Object> hm = new HashMap<>();
        hm = tm.getData();
        if (hm.get("ReturnMsg").toString().equals("OK")) {
            
            JSONArray ja=(JSONArray)hm.get("LayerList");
            for(int i=0;i<ja.size();i++)
            {
                askForLayer(jList1.getSelectedValue(),ja.getString(i));
            }
            
            //关闭当前界面
            JOptionPane.showMessageDialog(null,"Project is open successfully !","FreeStyle",JOptionPane.INFORMATION_MESSAGE);   
            dispose();
            
            return true;
        } else {
            JOptionPane.showMessageDialog(null,hm.get("ReturnMsg").toString(), "FreeStyle", JOptionPane.ERROR_MESSAGE);       
            return false;
        }
    }
    
     public boolean askForLayer(String project,String layer) {

        String messageID = mSocket.clientMessageIDPool.getOneRandomID(project);
        try {
            TransmittedMessage tm = mSocket.clientMessageCreator.GetLayerContent("FreeStyleServer", messageID, project,layer);
            mSocket.send(tm.convertMessageToString());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean askForLayerReceive(TransmittedMessage tm) {
        HashMap<String, Object> hm = new HashMap<>();
        hm = tm.getData();
        if (hm.get("ReturnMsg").toString().equals("OK")) {
            
            //显示
            SimpleFeatureSource features = (SimpleFeatureSource) hm.get("Features");
            Style style2 = SLD.createSimpleStyle(features.getSchema());
            FeatureLayer layer;
            layer = new FeatureLayer(features,style2,hm.get("LayerName").toString());
            main.addLayer(layer);
            return true;
        } else {
            JOptionPane.showMessageDialog(null,hm.get("ReturnMsg").toString(), "FreeStyle", JOptionPane.ERROR_MESSAGE);       
            return false;
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
            java.util.logging.Logger.getLogger(OpenFProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpenFProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpenFProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpenFProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main_win main;
                try {
                    main = new Main_win("test",new FreeStyleClientPureSocket());
                    new OpenFProject(main,new FreeStyleClientPureSocket()).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(OpenFProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
