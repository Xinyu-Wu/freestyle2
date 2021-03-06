/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
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
import java.awt.BasicStroke;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Font;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.FeatureType;

/**
 *
 * @author cheng
 * @lastPoint：上个采集点
 * @evtlastPoint：上个鼠标移动位置
 * @evtnowPoint：鼠标现在位置
 * @pointsList：点序列的值
 * @coorfinatesList：点序列的地理坐标值
 * @cache：缓冲图像文件
 * @drawPane：绘图控件
 * @type：0-点图层；1-线图层；2-面图层
 * @author cheng
 */
public class DrawEditingFeature {

    private Point lastPoint;
    private Point evtlastPoint;
    private Point evtnowPoint;
    private ArrayList<Point> pointsList;
    private ArrayList<Coordinate> coordinatesList;
    private ArrayList<LineString> polylines;
    private ArrayList<Polygon> polygons;
    private ArrayList<java.awt.Polygon> drawPolygons;
    private BufferedImage cache;
    private JMapPane drawPane;
    private int type;

    /*private Layer pointLayer;
    private Layer linelLayer;
    private Layer polygon;
    private SimpleFeature lastsimplefeature;*/
    public DrawEditingFeature(JMapPane mapPane) throws SchemaException, ParseException, ParseException, ParseException, Exception {

        drawPane = mapPane;
        lastPoint = new Point();
        evtlastPoint = new Point();
        evtnowPoint = new Point();
        pointsList = new ArrayList<>();
        polylines = new ArrayList<>();
        polygons = new ArrayList<>();
        drawPolygons = new ArrayList<>();
        coordinatesList = new ArrayList<>();
        type = 0;
        //@create bufferimage
        BufferedImage newCache = new BufferedImage(mapPane.getWidth(), mapPane.getHeight(), BufferedImage.TYPE_INT_ARGB);
        if (cache != null) {
            newCache.createGraphics().drawImage(cache, 0, 0, null);
        }
        cache = newCache; //交替缓存
        //  ds.createSchema(tb.buildFeatureType());  
        //  ds.setCharset(Charset.forName("GBK"));  
    }

    /**
     * 开始编辑
     *
     * @ 设置编辑的数据类型
     *
     */
    public void StartEditing(String FeatureType) {
        //todo 重置画布&设置背景
        Reset();
        if (FeatureType.contains("Point")) {
            type = 0;
        } else if (FeatureType.contains("Line")) {
            type = 1;
        } else if (FeatureType.contains("Polygon")) {
            type = 2;
        }
    }

    /**
     * 结束编辑
     *
     */
    public void EndEditing() throws IllegalAccessException, InstantiationException, IOException {
        CleanBufferImage();
        CoverNewLayer();
        Reset();

        if (type == 0) {

        } else if (type == 1) {

        } else if (type == 2) {

        }
    }

    /**
     * @鼠标选点 @param evt 鼠标事件
     */
    public void MouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (type == 1) {
                LineString newPolyline = GeometryManager.createOneLineString(ArrayListtoArray.CoordinatetoArrayDoubleLat(coordinatesList), ArrayListtoArray.CoordinatetoArrayDoubleLon(coordinatesList));
                polylines.add(newPolyline);
                DrawPolylineFeature(true);
            } else if (type == 2) {
                pointsList.add(pointsList.get(0));
                coordinatesList.add(coordinatesList.get(0));
                Polygon newpolygon = GeometryManager.createOnePolygon(ArrayListtoArray.CoordinatetoArrayDoubleLat(coordinatesList), ArrayListtoArray.CoordinatetoArrayDoubleLon(coordinatesList));
                polygons.add(newpolygon);
                java.awt.Polygon newdrawPolygon = new java.awt.Polygon(ArrayListtoArray.PointtoArrayIntX(pointsList), ArrayListtoArray.PointtoArrayIntY(pointsList), pointsList.size());
                drawPolygons.add(newdrawPolygon);
                DrawPolygonFeature(true);
            }
            //TODO add transform function
            pointsList.clear();
            coordinatesList.clear();
        } else {
            lastPoint = evt.getPoint();
            pointsList.add(lastPoint);
            Coordinate lastcoor = FTranslatePoint.ScreenToWorld(lastPoint, drawPane.getMapContent().getViewport());
            coordinatesList.add(lastcoor);
            if (type == 0) {
                DrawPointFeature(lastPoint);
            }
        }
    }

    /**
     * @鼠标移动绘图 @param evt 鼠标事件
     */
    public void MouseMoved(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here
        if (type != 0) {
            evtlastPoint = evtnowPoint;
            evtnowPoint = evt.getPoint();
            if (type == 1) {
                DrawPolylineFeature(false);
            } else {
                DrawPolygonFeature(false);
            }

        }
    }

    /**
     * @清空画布
     */
    private void CleanBufferImage() {
        BufferedImage newCache = new BufferedImage(drawPane.getWidth(), drawPane.getHeight(), BufferedImage.TYPE_INT_ARGB);
        cache = newCache; //交替缓存
        Graphics g_orig = drawPane.getGraphics();
        g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
        g_orig.drawImage(cache, 0, 0, drawPane);
        drawPane.repaint();
    }

    /**
     * @在画布上添加点
     */
    private void DrawPointFeature(Point lastPoint) {
        if (cache != null) {
            Graphics g = cache.getGraphics(); //替换Graphics，转往缓存上画图
            g.setColor(java.awt.Color.red);
            g.fillOval((int) lastPoint.x, (int) lastPoint.y, 8, 8);
//-----------------------------------------------------------------------
            Graphics g_orig = drawPane.getGraphics();
            if (drawPane.getMapContent() != null) {
                ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                drawPane.getRenderer().paint((Graphics2D) g, rectangle, drawPane.getWorldToScreenTransform());
            }
            g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
            g_orig.drawImage(cache, 0, 0, null);
        }
    }

    /**
     * @在画布上添加线图层
     */
    private void DrawPolylineFeature(boolean isdone) {
        if (!isdone) {
            if (cache != null && pointsList.size() > 0) {
                Graphics2D g = (Graphics2D) cache.getGraphics(); //替换Graphics，转往缓存上画图
                //elastic-------------------------------------------------------------
                BasicStroke bs = new BasicStroke(8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g.setStroke(bs);
                g.setColor(java.awt.Color.WHITE);
                g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
                g.setColor(java.awt.Color.RED);
                g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
                //---------------------------------------------------------------
                g.setColor(java.awt.Color.yellow);
                int[] x = ArrayListtoArray.PointtoArrayIntX(pointsList);
                int[] y = ArrayListtoArray.PointtoArrayIntY(pointsList);
                g.drawPolyline(x, y, x.length);
                //-----------------------------------------------------------------------
                Graphics g_orig = drawPane.getGraphics();
                if (drawPane.getMapContent() != null) {
                    ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                    Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                    drawPane.getRenderer().paint((Graphics2D) g, rectangle, drawPane.getWorldToScreenTransform());
                }
                g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
                g_orig.drawImage(cache, 0, 0, null);
            }
        } else {
            if (cache != null && pointsList.size() > 0) {
                Graphics2D g = (Graphics2D) cache.getGraphics(); //替换Graphics，转往缓存上画图
                BasicStroke bs = new BasicStroke(8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g.setStroke(bs);
                g.setColor(java.awt.Color.yellow);
                int[] x = ArrayListtoArray.PointtoArrayIntX(pointsList);
                int[] y = ArrayListtoArray.PointtoArrayIntY(pointsList);
                g.drawPolyline(x, y, x.length);
                //-----------------------------------------------------------------------
                Graphics g_orig = drawPane.getGraphics();
                if (drawPane.getMapContent() != null) {
                    ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                    Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                    drawPane.getRenderer().paint((Graphics2D) g, rectangle, drawPane.getWorldToScreenTransform());
                }
                g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
                g_orig.drawImage(cache, 0, 0, null);
            }
        }
    }

    /**
     * @在画布上添加面图层
     */
    private void DrawPolygonFeature(boolean isdone) {
        if (!isdone) {
            if (cache != null && pointsList.size() > 0) {
                Graphics2D g = (Graphics2D) cache.getGraphics(); //替换Graphics，转往缓存上画图
                //elastic-------------------------------------------------------------
                BasicStroke bs = new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g.setStroke(bs);
                g.setColor(java.awt.Color.WHITE);
                g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
                g.drawLine(pointsList.get(0).x, pointsList.get(0).y, (int) evtlastPoint.getX(), (int) evtlastPoint.getY());
                //g.setStroke(bs_2);
                g.setColor(java.awt.Color.RED);
                g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
                g.drawLine(pointsList.get(0).x, pointsList.get(0).y, (int) evtnowPoint.getX(), (int) evtnowPoint.getY());
                //-----------------------------------------------------------------------------------------------------
                // g.setStroke(bs_3);
                g.setColor(java.awt.Color.red);
                int[] x = ArrayListtoArray.PointtoArrayIntX(pointsList);
                int[] y = ArrayListtoArray.PointtoArrayIntY(pointsList);
                g.drawPolyline(x, y, x.length);
                g.setColor(Color.blue);
                //g.fillPolygon(x, y, x.length);
                //------------------------------------------------------------------------------------------------------

                g.setColor(Color.yellow);
                for (int i = 0; i < polygons.size(); i++) {
                    g.drawPolygon(drawPolygons.get(i));
                }
                //------------------------------------------------------------------------------------------------------
                Graphics g_orig = drawPane.getGraphics();
                if (drawPane.getMapContent() != null) {
                    ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                    Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                    //AffineTransform affineTransform=drawPane.getWorldToScreenTransform();
                    drawPane.getRenderer().paint((Graphics2D) g, rectangle, drawPane.getWorldToScreenTransform());
                }
                g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
                g_orig.drawImage(cache, 0, 0, null);
            }
        } else {
            Graphics2D g = (Graphics2D) cache.getGraphics(); //替换Graphics，转往缓存上画图               
            //-----------------------------------------------------------------------------------------------------                
            BasicStroke bs = new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g.setStroke(bs);
            g.setColor(Color.yellow);
            for (int i = 0; i < polygons.size(); i++) {
                g.drawPolygon(drawPolygons.get(i));
            }
            //------------------------------------------------------------------------------------------------------
            Graphics g_orig = drawPane.getGraphics();
            if (drawPane.getMapContent() != null) {
                ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
                Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
                //AffineTransform affineTransform=drawPane.getWorldToScreenTransform();
                drawPane.getRenderer().paint((Graphics2D) g, rectangle, drawPane.getWorldToScreenTransform());
            }
            g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
            g_orig.drawImage(cache, 0, 0, null);
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

    public void CoverNewLayer() throws IllegalAccessException, InstantiationException, IOException {
        int size = drawPane.getMapContent().layers().size() - 1;
        Layer newLayer = drawPane.getMapContent().layers().get(size);
        SimpleFeatureType TYPE = (SimpleFeatureType) newLayer.getFeatureSource().getSchema();
        ListFeatureCollection collection = new ListFeatureCollection(TYPE);
        SimpleFeatureSource layerSource = null;
        Style style2 = null;
        //TODO add geometry to collection(simpleFeatureManege Update)
        //Change Style
        switch (type) {
            case 0:

                break;
            case 1:
                
                break;
            case 2:
                 for(int i=0;i<polygons.size();i++)
                {
                    
                    collection.add(SimpleFeatureBuilder.build(TYPE, new Object[]{polygons.get(i), ""}, null));
                }
                 /*
                Map<String, Serializable> params = new HashMap<>();
                FileDataStoreFactorySpi factory = new org.geotools.data.shapefile.ShapefileDataStoreFactory();
                params.put(org.geotools.data.shapefile.ShapefileDataStoreFactory.URLP.key, new File("doc\\2.shp").toURI().toURL());
                ShapefileDataStore ds = (ShapefileDataStore) factory.createNewDataStore(params);
                ds.createSchema(TYPE);
                ds.forceSchemaCRS(DefaultGeographicCRS.WGS84);
                FileDataStore store = FileDataStoreFinder.getDataStore(new File("doc\\2.shp"));
                layerSource = store.getFeatureSource();
                SimpleFeatureIterator iterator = layerSource.getFeatures().features();
                style2 = SLD.createSimpleStyle(layerSource.getSchema());*/
                //-----------------------------
                Color color1 = Color.BLUE;
                Color color2 = Color.YELLOW;
                style2 = SLD.createPolygonStyle(color1, color2.brighter(), 0.5f);
                break;
            default:

                break;
        }
        FeatureLayer layer;
        layer = new FeatureLayer(collection, style2,newLayer.getTitle());
        /*
        if (layerSource != null) {
            layer = new FeatureLayer(layerSource, style2, "new");
        } else {
            layer = new FeatureLayer(collection, style2, newLayer.getTitle());
        }
         */
        drawPane.getMapContent().removeLayer(newLayer);
        drawPane.getMapContent().addLayer(layer);
        //layer.setVisible(true);
        drawPane.repaint();

    }

    private void Reset() {
        lastPoint = new Point();
        evtlastPoint = new Point();
        evtnowPoint = new Point();
        pointsList.clear();
        polylines.clear();
        polygons.clear();
        drawPolygons.clear();
        coordinatesList.clear();
        //-----------------------------------------------------------------------------------------------------
        Graphics2D g = (Graphics2D) cache.getGraphics(); //替换Graphics，转往缓存上画图               
        Graphics g_orig = drawPane.getGraphics();
        if (drawPane.getMapContent() != null) {
            ReferencedEnvelope mapArea = drawPane.getMapContent().getMaxBounds();
            Rectangle rectangle = new Rectangle(drawPane.getWidth(), drawPane.getHeight());
            drawPane.getRenderer().paint((Graphics2D) g, rectangle, drawPane.getWorldToScreenTransform());
        }
        g_orig.clearRect(0, 0, drawPane.getWidth(), drawPane.getHeight());
        g_orig.drawImage(cache, 0, 0, null);
    }

    /*
    //TODO clean bitmap
    private void ResetFeatureLayer() throws SchemaException, ParseException, Exception {
        SimpleFeatureTypeBuilder simpleFeatureTypeBuilder = new SimpleFeatureTypeBuilder();
        if (drawPane.getMapContent().getCoordinateReferenceSystem() != null) {
            simpleFeatureTypeBuilder.setCRS(drawPane.getMapContent().getCoordinateReferenceSystem());
        }
        /*
        simpleFeatureTypeBuilder.setName("shapefile");
        simpleFeatureTypeBuilder.add("the_geom", Point.class);
        simpleFeatureTypeBuilder.add("NAME", String.class);
        simpleFeatureTypeBuilder.setDefaultGeometry("the_geom");
       // FeatureType TYPE = simpleFeatureTypeBuilder.buildFeatureType();
        GeometryFactory geometryFactory = new GeometryFactory();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(simpleFeatureTypeBuilder.buildFeatureType());
        DefaultFeatureCollection features = new DefaultFeatureCollection(null, featureBuilder.getFeatureType());
        for (int i = 0; i < 1; i++) {
            featureBuilder.add(geometryFactory.createPoint(new Coordinate(i, i)));
            featureBuilder.add(i);
            features.add(featureBuilder.buildFeature(i + ""));
        }
        //DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal", (SimpleFeatureType) TYPE);
        Style style = SLD.createPointStyle("sty", Color.yellow, Color.yellow, 0.5f, 1.5f);

        pointLayer = new FeatureLayer(features, style);
        MapContent map = drawPane.getMapContent();
        map.addLayer(pointLayer);
        //----------------------------------------------------------------------预设图层内图形
        SimpleFeatureType TYPE = DataUtilities.createType("location", "geom:Polygon,name:String");
        DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal", TYPE);
        WKTReader2 wkt = new WKTReader2();
        featureCollection.add(SimpleFeatureBuilder.build(TYPE, new Object[]{wkt.read("POLYGON((10 5, 15 0, 20 5, 15 10, 10 5))"),"name1"}, null));
        Color color1 = Color.BLUE;
        Color color2 = Color.RED;
        Style style2 = SLD.createPolygonStyle(color1, color2.brighter(), 0.5f);
       // polygon = new FeatureLayer(featureCollection, style2);   
        //-----------------------------------------------------------------------------
        //----------------------------------------------------------------------测试加入图形
        double[] x={20,30,40,30};
        double[] y={10,0,10,20};
        Geometry[] polyGeometry=new Geometry[1];
        polyGeometry[0]=GeometryManager.createOnePolygon(x,y);
        SimpleFeatureManager.addGeometriesToLayer(polygon, polyGeometry);            
       // polygon.setVisible(true);
        drawPane.getMapContent().addLayer(polygon);

    }
     */
}
