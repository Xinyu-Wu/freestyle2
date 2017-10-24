/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.swing.JMapPane;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import javafx.scene.text.Font;
import org.geotools.data.DataUtilities;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.FeatureType;

/**
 * @lastPoint：上个采集点
 * @evtlastPoint：上个鼠标移动位置
 * @evtnowPoint：鼠标现在位置
 * @xpointsList：点序列的x值
 * @ypointsList：点序列的y值
 * @cache：缓冲图像文件
 * @drawPane：绘图控件
 * @type：0-点图层；1-线图层；2-面图层
 * @author cheng
 */
public class DrawEditingFeature {

    private Point lastPoint;
    private Point evtlastPoint;
    private Point evtnowPoint;
    private ArrayList<Coordinate> pointsList;
    private BufferedImage cache;
    private JMapPane drawPane;
    private int type;
    private Layer pointLayer;
    private Layer linelLayer;
    private Layer polygon;
    private SimpleFeature lastsimplefeature;

    public DrawEditingFeature(JMapPane mapPane) {

        drawPane = mapPane;
        lastPoint = new Point();
        evtlastPoint = new Point();
        evtnowPoint = new Point();
        pointsList = new ArrayList<Coordinate>();
        type = 2;
        //@create bufferimage
        /*BufferedImage newCache = new BufferedImage(mapPane.getWidth(), mapPane.getHeight(),  BufferedImage.TYPE_INT_ARGB);        
        if(cache!=null) { 
            newCache.createGraphics().drawImage(cache, 0, 0, null);
        }
        cache = newCache; //交替缓存*/

        ResetFeatureLayer();

        //  ds.createSchema(tb.buildFeatureType());  
        //  ds.setCharset(Charset.forName("GBK"));  
    }

    //@设置编辑的数据类型
    public void SetFeatureType(String FeatureType) {
        if (FeatureType == "Point") {
            type = 0;
        } else if (FeatureType == "PolyLine") {
            type = 1;
        } else if (FeatureType == "Polygon") {
            type = 2;
        }
    }

    //@鼠标选点
    public void MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        try {
            if (evt.getClickCount() == 2) {
                pointsList.clear();
            } 
            else {
                lastPoint = evt.getPoint();
                Coordinate lastcoor = FTranslatePoint.ScreenToWorld(lastPoint, drawPane.getMapContent().getViewport());
                pointsList.add(lastcoor);
                AddCurrentPoint();
               // DrawPointFeature(lastPoint);
            }
        } 
        catch (Exception e) {
        }

    }

    //@鼠标移动绘图
    public void MouseMoved(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here
        if (type != 0) {
            evtlastPoint = evtnowPoint;
            evtnowPoint = evt.getPoint();
            if (type == 1) {
                DrawPolylineFeature();
            } else {
                DrawPolygonFeature();
            }

        }
    }

    //@Arraylist与int数组转换
    private int[] CoordinatetoArrayIntLon(ArrayList<Coordinate> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = (int) arrayList.get(i).x;
        }
        return array;
    }

    private int[] CoordinatetoArrayIntLat(ArrayList<Coordinate> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = (int) arrayList.get(i).y;
        }
        return array;
    }

    //@Arraylist与double数组转换
    private double[] CoordinatetoArrayDoubleLon(ArrayList<Coordinate> arrayList) {
        double[] array = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i).x;
        }
        return array;
    }

    private double[] CoordinatetoArrayDoubleLat(ArrayList<Coordinate> arrayList) {
        double[] array = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i).y;
        }
        return array;
    }

    //@重置编辑状态
    public void CleanBufferImage() {
        lastPoint = new Point();
        evtlastPoint = new Point();
        evtnowPoint = new Point();
        pointsList.clear();
        //缺图层操作
        type = 0;
        //@create bufferimage
        BufferedImage newCache = new BufferedImage(drawPane.getWidth(), drawPane.getHeight(), BufferedImage.TYPE_INT_ARGB);
        if (cache != null) {
            newCache.createGraphics().drawImage(cache, 0, 0, null);
        }
        cache = newCache; //交替缓存
    }

    private void AddCurrentPoint() throws IllegalAccessException, InstantiationException, IOException {
        Geometry[] geopoints = new Geometry[1];
        int size = pointsList.size();
        geopoints[0] = GeometryManager.createOnePoint(pointsList.get(size - 1).x, pointsList.get(size - 1).y);
        SimpleFeatureManager.addGeometriesToLayer(pointLayer, geopoints);
    }
    
    /**
     * @deprecated @param lastPoint
     */
    private void DrawPointFeature(Point lastPoint) {
        if (cache != null) {
            Graphics g = cache.getGraphics(); //替换Graphics，转往缓存上画图
            g.setColor(java.awt.Color.black);
            g.fillOval((int) lastPoint.x, (int) lastPoint.y, 5, 5);
//-----------------------------------------------------------------------
            Graphics g_orig = drawPane.getGraphics();
            if (drawPane.getMapContent() != null) {
                ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                drawPane.getRenderer().paint((Graphics2D) g, rectangle, mapArea);
            }
            g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
            g_orig.drawImage(cache, 0, 0, null);
        }
    }

    /**
     * @deprecated
     */
    private void DrawPolylineFeature() {
        if (cache != null && pointsList.size() > 0) {
            Graphics g = cache.getGraphics(); //替换Graphics，转往缓存上画图
            //elastic-------------------------------------------------------
            g.setColor(java.awt.Color.WHITE);
            g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
            g.setColor(java.awt.Color.RED);
            g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
            //---------------------------------------------------------------
            g.setColor(java.awt.Color.yellow);
            int[] x = CoordinatetoArrayIntLat(pointsList);
            int[] y = CoordinatetoArrayIntLon(pointsList);
            g.drawPolyline(x, y, x.length);
            //-----------------------------------------------------------------------
            Graphics g_orig = drawPane.getGraphics();
            if (drawPane.getMapContent() != null) {
                ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                drawPane.getRenderer().paint((Graphics2D) g, rectangle, mapArea);
            }
            g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
            g_orig.drawImage(cache, 0, 0, null);
        }
    }

    /**
     * @deprecated
     */
    private void DrawPolygonFeature() {
        if (cache != null && pointsList.size() > 0) {
           /* Graphics g = cache.getGraphics(); //替换Graphics，转往缓存上画图
            //elastic-------------------------------------------------------------
            g.setColor(java.awt.Color.WHITE);
            g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
            g.drawLine(pointsList.get(0).x, pointsList.get(0).y, (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
            g.setColor(java.awt.Color.RED);
            g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
            g.drawLine(pointsList.get(0).x, pointsList.get(0).y, (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
            //-----------------------------------------------------------------------------------------------------
            g.setColor(java.awt.Color.red);
            int[] x = CoordinatetoArrayIntLat(pointsList);
            int[] y = CoordinatetoArrayIntLat(pointsList);
            g.drawPolyline(x, y, x.length);
            g.setColor(Color.blue);
            //g.fillPolygon(x, y, x.length);
            //-----------------------------------------------------------------------
            Graphics g_orig = drawPane.getGraphics();
            if (drawPane.getMapContent() != null) {
                ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                //AffineTransform affineTransform=drawPane.getWorldToScreenTransform();
                drawPane.getRenderer().paint((Graphics2D) g, rectangle, mapArea);
            }
            g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
            g_orig.drawImage(cache, 0, 0, null);*/
        }
    }

    /**
     * @deprecated
     */
    private void Elastic() {
        if (cache != null && pointsList.size() > 0) {
            Graphics g = cache.getGraphics(); //替换Graphics，转往缓存上画图
            g.setColor(java.awt.Color.WHITE);
            g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
            g.setColor(java.awt.Color.RED);
            g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
//-----------------------------------------------------------------------
            Graphics g_orig = drawPane.getGraphics();
            g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
            g_orig.drawImage(cache, 0, 0, null);
        }
    }

    
    
    private void ResetFeatureLayer() {
        SimpleFeatureTypeBuilder simpleFeatureTypeBuilder = new SimpleFeatureTypeBuilder();
        if(drawPane.getMapContent().getCoordinateReferenceSystem()!=null){
        simpleFeatureTypeBuilder.setCRS(drawPane.getMapContent().getCoordinateReferenceSystem());
        }        
        simpleFeatureTypeBuilder.setName("shapefile");
        simpleFeatureTypeBuilder.add("geom", Point.class);
        simpleFeatureTypeBuilder.add("NAME", String.class);
        simpleFeatureTypeBuilder.setDefaultGeometry("geom");
        FeatureType TYPE = simpleFeatureTypeBuilder.buildFeatureType();
        DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal", (SimpleFeatureType) TYPE);
        Style style = SLD.createPointStyle("sty", Color.yellow, Color.yellow, 0.5f, 1.5f);
        pointLayer = new FeatureLayer(featureCollection, style);
        MapContent map = drawPane.getMapContent();
        map.addLayer(pointLayer);

        //todo
        //simpleFeatureTypeBuilder.add("geom", LineString.class);
    }

}
