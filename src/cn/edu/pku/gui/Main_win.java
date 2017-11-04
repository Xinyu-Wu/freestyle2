/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;
import FProject.FProject;
import FeatureEdit.FeatureSelection;
import cn.edu.pku.datasource.ShapefileManager;
import com.sun.prism.paint.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapFrame.Tool;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.tool.CursorTool;
import org.geotools.swing.tool.PanTool;
import org.geotools.swing.tool.ZoomInTool;
import org.geotools.swing.tool.ZoomOutTool;


/**
 *
 * @author weizy
 */
public class Main_win extends javax.swing.JFrame {

    private boolean isSelected = false;
    private Style originStyle=null;
    public FProject mFProject=null;
    
    /**
     * Creates new form Main_win
     */
    public Main_win() {
        initComponents();
        this.setTitle("Freestyle");
        this.setLocationRelativeTo(null);
        
        FeatureEdit.FeatureSelection fs=new FeatureSelection();
        System.out.println("works!");
        this.jButton15.addActionListener(e -> jMapPane3.setCursorTool(
                new CursorTool() {

                    @Override
                    public void onMouseClicked(MapMouseEvent ev) {
                        if(isSelected==true)
                        {
                            if(jMapPane3.getMapContent().layers().isEmpty()==false)
                            {
                                fs.featureSource = (SimpleFeatureSource) jMapPane3.getMapContent().layers().get(0).getFeatureSource();
                                fs.setGeometry();
                                fs.selectFeatures(ev,jMapPane3,originStyle);
                            }
                            
                        }                        
                    }
                }));
        jMapPane3.setBackground(java.awt.Color.white);
        this.start = new Point(0, 0);
        this.end = new Point(0, 0);
        this.linestart = new Point(0, 0);
        this.lineend = new Point(0, 0);
        this.temp = new Point(0, 0);
        BufferedImage newCache = new BufferedImage(jMapPane3.getWidth(), jMapPane3.getHeight(),  BufferedImage.TYPE_INT_ARGB);
        if(cache!=null) { //把上个缓存的内容画到新缓存上
        //这个地方如果Java版本太老无法编译的话的话改用getGraphics()
        newCache.createGraphics().drawImage(cache, 0, 0, null);
        }
        cache = newCache; //交替缓存
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        mapLayerTable1 = new org.geotools.swing.MapLayerTable();
        jMapPane3 = new org.geotools.swing.JMapPane();
        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollBar2 = new javax.swing.JScrollBar();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jtbEdit = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        Project = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        Open = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        Geoprocessing = new javax.swing.JMenu();
        Define_Projection = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1600, 960));

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/ZoomInTool16.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomInActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/ZoomOutTool16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomOutActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/ZoomFullExtent16.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullExtentActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/PanTool16.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PanActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/ElementSelectTool16.png"))); // NOI18N
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectionActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton15);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/GoToXY16.png"))); // NOI18N
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton16);

        jToolBar2.setRollover(true);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/GenericOpen16.png"))); // NOI18N
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton11.setMinimumSize(new java.awt.Dimension(40, 40));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton11);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/DocumentNew16.png"))); // NOI18N
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreatelayerActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton10);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/DataAdd16.png"))); // NOI18N
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenLayerActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton14);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/GenericSave16.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton3);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/GenericBlueLeftArrowCurvedDown16.png"))); // NOI18N
        jToolBar2.add(jButton12);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn.edu.pku.icons/GenericBlueRightArrowCurvedDown16.png"))); // NOI18N
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton13);

        mapLayerTable1.setToolTipText("test");
        mapLayerTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        mapLayerTable1.setMapPane(jMapPane3);

        jMapPane3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMapPane3MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMapPane3MouseMoved(evt);
            }
        });
        jMapPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMapPane3MouseClicked(evt);
            }
        });

        jScrollBar2.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

        javax.swing.GroupLayout jMapPane3Layout = new javax.swing.GroupLayout(jMapPane3);
        jMapPane3.setLayout(jMapPane3Layout);
        jMapPane3Layout.setHorizontalGroup(
            jMapPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMapPane3Layout.createSequentialGroup()
                .addComponent(jScrollBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jMapPane3Layout.setVerticalGroup(
            jMapPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
            .addGroup(jMapPane3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(214, 217, 223));
        jTextField1.setText("Scale: ");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(214, 217, 223));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("longitude latitude Unit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapLayerTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jMapPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapLayerTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jMapPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jMapPane3.getAccessibleContext().setAccessibleName("");
        jMapPane3.getAccessibleContext().setAccessibleDescription("");

        jtbEdit.setText("Editing");

        jLabel1.setText("Message");

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(237, 237, 237));
        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane2.setViewportView(jEditorPane1);

        jButton4.setText("Send");
        jButton4.setActionCommand("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jButton5.setText("jButton5");

        Project.setText("Project");

        New.setText("New Project");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateFProjectActionPerformed(evt);
            }
        });
        Project.add(New);

        Open.setText("Open Project");
        Project.add(Open);

        Save.setText("Save Project");
        Project.add(Save);

        jMenuItem1.setText("Save as...");
        jMenuItem1.setToolTipText("");
        Project.add(jMenuItem1);

        Exit.setText("Exit");
        Project.add(Exit);

        jMenuBar1.add(Project);

        Geoprocessing.setText("Geoprocessing");

        Define_Projection.setText("Define Projection");
        Geoprocessing.add(Define_Projection);

        jMenuBar1.add(Geoprocessing);

        Help.setText("Help");

        jMenuItem2.setText("About");
        Help.add(jMenuItem2);

        jMenuBar1.add(Help);

        jMenu2.setText("Account");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(jtbEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtbEdit)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // 放大
    private void ZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomInActionPerformed
        ZoomInTool zoomInTool1=new ZoomInTool();
        jMapPane3.setCursorTool(zoomInTool1);    
    }//GEN-LAST:event_ZoomInActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        AddLayer als = new AddLayer(Main_win.this, jMapPane3);
        als.setVisible(true);
        jMapPane3.getMapContent();
    }//GEN-LAST:event_jButton11ActionPerformed

    // 缩小
    private void ZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomOutActionPerformed
        ZoomOutTool zoomOutTool1=new ZoomOutTool();
        jMapPane3.setCursorTool(zoomOutTool1);
    }//GEN-LAST:event_ZoomOutActionPerformed

    // 缩放到图层
    private void FullExtentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullExtentActionPerformed
        jMapPane3.reset();
        jMapPane3.setCursorTool(null);
    }//GEN-LAST:event_FullExtentActionPerformed

    // 漫游
    private void PanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PanActionPerformed
        PanTool panTool1=new PanTool();
        jMapPane3.setCursorTool(panTool1);
        System.out.println(this.mFProject.getFName());
    }//GEN-LAST:event_PanActionPerformed

    //要素选择
    private void SelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectionActionPerformed
        if(isSelected==false)
        {
            isSelected=true;
            originStyle=jMapPane3.getMapContent().layers().get(0).getStyle();
            this.jButton15.setBackground(java.awt.Color.LIGHT_GRAY);
        }
        else
        {
            isSelected=false;
            this.jButton15.setBackground(this.jButton1.getBackground());
            jMapPane3.setCursorTool(null);
            Layer layer = jMapPane3.getMapContent().layers().get(0);
            ((FeatureLayer) layer).setStyle(originStyle);
            jMapPane3.repaint();
        }
        
    }//GEN-LAST:event_SelectionActionPerformed

    private void CreateFProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateFProjectActionPerformed
        // TODO add your handling code here:
        CreateFProject cfp=new CreateFProject(this);
    }//GEN-LAST:event_CreateFProjectActionPerformed

Point start;
Point end;
Point temp;
int which;
Point linestart;
Point lineend;
BufferedImage cache;
    private void jMapPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMapPane3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMapPane3MouseClicked

    private void jMapPane3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMapPane3MouseDragged
        // TODO add your handling code here:
setBackground(java.awt.Color.white);
linestart.setLocation(lineend);
temp.setLocation(end);
end.setLocation(evt.getPoint());
lineend.setLocation(evt.getPoint());
if(cache!=null) {
Graphics g = cache.getGraphics(); //替换Graphics，转往缓存上画图
g.setColor(java.awt.Color.WHITE);
g.drawLine((int)start.getX(), (int)start.getY(), (int)temp.getX(), (int)temp.getY());
g.setColor(java.awt.Color.RED);
g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());

//-----------------------------------------------------------------------
Graphics g_orig=jMapPane3.getGraphics();
//g_orig.clearRect(0, 0, getWidth(), getHeight());
//g_orig.drawImage(cache, 0, 0, null);
ReferencedEnvelope mapArea=jMapPane3.getMapContent().getMaxBounds();
Rectangle rectangle=new Rectangle(jMapPane3.getWidth(), jMapPane3.getHeight());
jMapPane3.getRenderer().paint((Graphics2D)g, rectangle, mapArea);
g_orig.clearRect(0, 0, getWidth(), getHeight());
g_orig.drawImage(cache, 0, 0, null);
//jMapPane3.repaint();
    }
    }//GEN-LAST:event_jMapPane3MouseDragged

    private void jMapPane3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMapPane3MouseMoved
        // TODO add your handling code here:
        start.setLocation(evt.getPoint());
end.setLocation(evt.getPoint());
linestart.x=(evt.getX());
linestart.y=(evt.getY());
lineend.x=(evt.getX());
lineend.y=(evt.getY());
    }//GEN-LAST:event_jMapPane3MouseMoved

     //打开本地图层
    private void OpenLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenLayerActionPerformed
        // TODO add your handling code here:
        if(this.mFProject==null)
        {
            JOptionPane.showMessageDialog(null,"请先新建一个工程!","FreeStyle",JOptionPane.WARNING_MESSAGE); 
            CreateFProject cfp=new CreateFProject(this);
            return;
        }
        else
        {
             ShapefileManager sm =new ShapefileManager();
             sm.readShpTest(jMapPane3,this.mFProject);
        }  
    }//GEN-LAST:event_OpenLayerActionPerformed

    //新建图层
    private void CreatelayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreatelayerActionPerformed
        // TODO add your handling code here:
        if(this.mFProject==null)
        {
            JOptionPane.showMessageDialog(null,"请先新建一个工程!","FreeStyle",JOptionPane.WARNING_MESSAGE);   
            CreateFProject cfp=new CreateFProject(this);
        }
        else
        {
             CreateLayer cl=new CreateLayer();
             cl.setVisible(true);
        }
    }//GEN-LAST:event_CreatelayerActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton4ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Main_win.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_win.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_win.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_win.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_win().setVisible(true);
            }
        });
    }
    
     /**
     * @param title the command line arguments
     */
    public void setNewTitle(String title) {
        this.setTitle(title);
        this.setVisible(true);
        //this.setVisible(true);
    }
    
    public void setJMapPane(MapContent content){
        this.jMapPane3.setMapContent(content);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Define_Projection;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu Geoprocessing;
    private javax.swing.JMenu Help;
    private javax.swing.JMenuItem New;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenu Project;
    private javax.swing.JMenuItem Save;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private org.geotools.swing.JMapPane jMapPane3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToggleButton jtbEdit;
    private org.geotools.swing.MapLayerTable mapLayerTable1;
    // End of variables declaration//GEN-END:variables
}
