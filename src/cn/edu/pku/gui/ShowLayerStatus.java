/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import FMessage.FreeStyleClientPureSocket;
import FMessage.TransmittedMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.geotools.map.Layer;
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
public class ShowLayerStatus extends javax.swing.JFrame {

    /**
     * Creates new form ShowLayerStatus
     */

    FreeStyleClientPureSocket mSocket;
    String mProject;
    Main_win main;
     DefaultListModel dlmWL = new DefaultListModel();
      DefaultListModel dlmOB = new DefaultListModel();
      //ArrayList<SimpleFeatureSource> ListSFS=new ArrayList<SimpleFeatureSource>();
    HashMap<String, SimpleFeatureSource> hmsfs = new HashMap<>();

    public ShowLayerStatus(Main_win fMain,String Project) {
        initComponents();
        this.setLocationRelativeTo(null);//屏幕中间显示
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//退出关闭
        mSocket = new FreeStyleClientPureSocket();
        main=fMain;
        mProject=Project;
        
    }
    
   
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
                askForLayer(mProject,ja.getString(i));
            }
            
            //关闭当前界面
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
            String layer = hm.get("LayerName").toString();
            hmsfs.put(layer, (SimpleFeatureSource)hm.get("Features"));
            if(hm.get("LayerStatus").toString().equals("Editing"))
            {
                dlmOB.addElement(layer);
                jListOB.setModel(dlmOB);
            }
            else{
                dlmWL.addElement(layer);
                jListWL.setModel(dlmWL);
            }
                
            
            return true;
        } else {
            JOptionPane.showMessageDialog(null,hm.get("ReturnMsg").toString(), "FreeStyle", JOptionPane.ERROR_MESSAGE);       
            return false;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jListWL = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnOB = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListOB = new javax.swing.JList<>();
        btnWL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Layer Status");

        jScrollPane1.setViewportView(jListWL);

        jLabel1.setText("Editing Layers");

        jLabel2.setText("Unedited Layers");

        btnOB.setText("Apply for Obeserve Lock");
        btnOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOBActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jListOB);

        btnWL.setText("Apply for Write Lock");
        btnWL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnOB, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnWL, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOB)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnWL)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnWLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWLActionPerformed
        // TODO add your handling code here:
        String layer=jListWL.getSelectedValue();
        if(layer==null)
        {
            JOptionPane.showMessageDialog(null, "Please choose a layer！", "FreeStyle", JOptionPane.WARNING_MESSAGE);
        }
        else
            WLApply(mProject,layer);
        //fyn：传给服务器申请权限，申请好要enablesaveEditing按钮
    }//GEN-LAST:event_btnWLActionPerformed

    private void btnOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOBActionPerformed
        // TODO add your handling code here:
        String layer=jListOB.getSelectedValue();
        if(layer==null)
        {
            JOptionPane.showMessageDialog(null, "Please choose a layer！", "FreeStyle", JOptionPane.WARNING_MESSAGE);
        }
        else
            OBApply(mProject,layer);
        //fyn：传给服务器申请权限
    }                                     

    public boolean WLApply(String project, String layer) {

        String messageID = mSocket.clientMessageIDPool.getOneRandomID(project);
        try {
            TransmittedMessage tm = mSocket.clientMessageCreator.GetLayerWriteLock("FreeStyleServer", messageID, project, layer);
            mSocket.send(tm.convertMessageToString());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean WLApplyReceive(TransmittedMessage tm) {
        HashMap<String, Object> hm = new HashMap<>();
        hm = tm.getData();
        if (hm.get("ReturnMsg").toString().equals("OK")) {
            JOptionPane.showMessageDialog(null, "Begin Editing Layer "+jListWL.getSelectedValue()+" ！", "FreeStyle", JOptionPane.INFORMATION_MESSAGE);
           
            //关闭当前界面
            dispose();
            
            main.CurrentLayer=jListWL.getSelectedValue();
            main.isEditing=true;
            main.setBtnSaveEditing();
            main.StartEditing(hmsfs.get(main.CurrentLayer));
            
            return true;
        } else {
            JOptionPane.showMessageDialog(null,hm.get("ReturnMsg").toString(), "FreeStyle", JOptionPane.ERROR_MESSAGE);       
            return false;
        }
    }
    
    public boolean OBApply(String project, String layer) {

        String messageID = mSocket.clientMessageIDPool.getOneRandomID(project);
        try {
            TransmittedMessage tm = mSocket.clientMessageCreator.GetLayerWriteLock("FreeStyleServer", messageID, project, layer);
            mSocket.send(tm.convertMessageToString());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean OBApplyReceive(TransmittedMessage tm) {
        HashMap<String, Object> hm = new HashMap<>();
        hm = tm.getData();
        if (hm.get("ReturnMsg").toString().equals("OK")) {
            JOptionPane.showMessageDialog(null, "Begin Editing Layer "+jListOB.getSelectedValue()+" ！", "FreeStyle", JOptionPane.INFORMATION_MESSAGE);
           
            //关闭当前界面
            dispose();
            
            main.CurrentLayer=jListOB.getSelectedValue();
            //fyn:TODO picture transmission
          
            return true;
        } else {
            JOptionPane.showMessageDialog(null,hm.get("ReturnMsg").toString(), "FreeStyle", JOptionPane.ERROR_MESSAGE);       
            return false;
        }
        //fyn：传给服务器申请权限
    }//GEN-LAST:event_btnOBActionPerformed


    /**
     * StartEditing
     * 或许传名称并不可行，可以试着传geometrydescription
     */
    private void StartEditing(SimpleFeatureSource sfs){
        
        main.StartEditing(sfs);
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
            java.util.logging.Logger.getLogger(ShowLayerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowLayerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowLayerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowLayerStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ShowLayerStatus(new Main_win("testID",new FreeStyleClientPureSocket()),"test").setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ShowLayerStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOB;
    private javax.swing.JButton btnWL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jListOB;
    private javax.swing.JList<String> jListWL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
