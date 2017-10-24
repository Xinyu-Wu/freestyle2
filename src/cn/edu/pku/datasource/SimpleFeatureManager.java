/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import static cn.edu.pku.datasource.GeometryManager.createOnePoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hwpf.converter.FontReplacer.Triplet;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ng.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.map.Layer;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;

/**
 * 对SimpleFeature进行管理的类
 *
 * @version 1.0.2
 * @author sq
 */
public class SimpleFeatureManager {

    /**
     * 获得要素类型（Class）
     *
     * @param feature 要素
     * @return
     */
    public static Class getFeatureClass(SimpleFeature feature) {
        return feature.getAttribute(0).getClass();
    }

    /**
     * 获取要素类型（String）
     *
     * @param feature 要素
     * @return
     */
    public static String getFeatureClassString(SimpleFeature feature) {
        return ((Geometry) feature.getAttribute(0)).getGeometryType();
    }

    /**
     * 获取要素的几何图形
     *
     * @param feature 要素
     * @return
     */
    public static Geometry getGeometryFromFeature(SimpleFeature feature) {
        //return (Geometry)feature.getAttribute(0);  这个是自己找的
        //下面这个是网上给的
        return (Geometry) feature.getDefaultGeometry();
    }

    /**
     * 获取要素集合的几何图形集合
     *
     * @param featureCollection 要素集合
     * @return
     */
    public static ArrayList<Geometry> getGeometriesFromFeatureCollection(SimpleFeatureCollection featureCollection) {
        ArrayList<Geometry> sGeometryList = new ArrayList<Geometry>();
        //GeometryCollection geometryCollection =new GeometryCollection();
        SimpleFeatureIterator it = featureCollection.features();
        try {
            while (it.hasNext()) {
                SimpleFeature feature = it.next();
                sGeometryList.add((Geometry) feature.getDefaultGeometry());
            }
        } finally {
            it.close();
        }
        return sGeometryList;
    }

    /**
     * 新建一个点要素
     *
     * @deprecated
     * @param point 点对象
     * @param poiid 点ID
     * @param fieldDefinition 字段定义及数据类型
     * @param fieldValue 字段值
     * @return 点要素对象
     * @throws Exception
     */
    public static SimpleFeature createOnePointFeature(Point point, String poiid,
            Map<String, Class> fieldDefinition, Map<String, Object> fieldValue) throws Exception {

        int sFieldDefinationCount = fieldDefinition.size();
        int sFieldValueCount = fieldValue.size();
        if (sFieldDefinationCount != sFieldValueCount) {
            throw new Exception("字段定义与值数目不相等");
        }
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("Location");
        //builder.setCRS(DefaultGeographicCRS.WGS84); // <- Coordinate reference system  
        builder.add("the_geom", Point.class);  //这个为地理属性字段 postgis中为 the——geom  
        builder.add("Poiid", String.class);
        for (Map.Entry<String, Class> entry : fieldDefinition.entrySet()) {
            builder.add(entry.getKey(), entry.getValue()); // 这是其他属性字段 自己定义的....
        }
        // build the type  
        final SimpleFeatureType TYPE = builder.buildFeatureType();

        //GeometryFactory geometryFactory = new GeometryFactory();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        featureBuilder.set("the_geom", point);
        featureBuilder.set("Poiid", poiid);
        for (Map.Entry<String, Object> entry : fieldValue.entrySet()) {
            featureBuilder.set(entry.getKey(), entry.getValue()); // 写入属性值
        }
        SimpleFeature sfeature = featureBuilder.buildFeature(null);
        return sfeature;
    }

    /**
     * 新建一个点要素
     *
     * @deprecated
     * @param point 点对象
     * @param poiid 点ID
     * @param fieldDefinition 字段定义及数据类型
     * @param fieldValue 字段值
     * @return 点要素对象
     * @throws Exception
     */
    public static SimpleFeature createOnePointFeature2(Point point, String poiid,
            Map<String, String> fieldDefinition, Map<String, Object> fieldValue) throws Exception {
        int sFieldDefinationCount = fieldDefinition.size();
        int sFieldValueCount = fieldValue.size();
        if (sFieldDefinationCount != sFieldValueCount) {
            throw new Exception("字段定义与值数目不相等");
        }
        String sRestFeatureType = "";
        ArrayList<Object> sRestFieldValue = new ArrayList<Object>();

        for (Map.Entry<String, String> entry : fieldDefinition.entrySet()) {
            sRestFeatureType += entry.getKey() + ":" + entry.getValue() + ",";
            sRestFieldValue.add(fieldValue.get(entry.getKey()));// 写入属性值
        }
        sRestFeatureType = sRestFeatureType.substring(0, sRestFeatureType.length() - 1);
        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "Location:Point,"
                + // <- the geometry attribute: LineString type  
                "PointID:String," + sRestFeatureType
        );
        //GeometryFactory geometryFactory = new GeometryFactory();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        sRestFieldValue.add(0, point);
        sRestFieldValue.add(1, poiid);
        Object[] obj = sRestFieldValue.toArray();
        SimpleFeature sfeature = featureBuilder.buildFeature(null, obj);
        return sfeature;
    }

    /**
     * 新建一个线要素
     *
     * @deprecated
     * @param lineString 线对象
     * @param lineID 线ID
     * @param fieldDefinition 字段定义及数据类型
     * @param fieldValue 字段值
     * @return 线要素对象
     * @throws Exception
     */
    public static SimpleFeature createOneLineStringFeature(LineString lineString, String lineID,
            Map<String, String> fieldDefinition, Map<String, Object> fieldValue) throws Exception {
        int sFieldDefinationCount = fieldDefinition.size();
        int sFieldValueCount = fieldValue.size();
        if (sFieldDefinationCount != sFieldValueCount) {
            throw new Exception("字段定义与值数目不相等");
        }
        String sRestFeatureType = "";
        ArrayList<Object> sRestFieldValue = new ArrayList<Object>();

        for (Map.Entry<String, String> entry : fieldDefinition.entrySet()) {
            sRestFeatureType += entry.getKey() + ":" + entry.getValue() + ",";
            sRestFieldValue.add(fieldValue.get(entry.getKey()));// 写入属性值
        }
        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "Location:LineString,"
                + // <- the geometry attribute: LineString type  
                "LineID:String," + sRestFeatureType
        );
        //GeometryFactory geometryFactory = new GeometryFactory();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        sRestFieldValue.add(0, lineString);
        sRestFieldValue.add(1, lineID);
        Object[] obj = sRestFieldValue.toArray();
        SimpleFeature sfeature = featureBuilder.buildFeature(null, obj);
        return sfeature;
    }

    /**
     * 新建一个线性环要素
     *
     * @deprecated
     * @param linearRing 环对象
     * @param ringID 环ID
     * @param fieldDefinition 字段定义及数据类型
     * @param fieldValue 字段值
     * @return 环要素对象
     * @throws Exception
     */
    public static SimpleFeature createOneLinearRingFeature(LinearRing linearRing, String ringID,
            Map<String, String> fieldDefinition, Map<String, Object> fieldValue) throws Exception {
        int sFieldDefinationCount = fieldDefinition.size();
        int sFieldValueCount = fieldValue.size();
        if (sFieldDefinationCount != sFieldValueCount) {
            throw new Exception("字段定义与值数目不相等");
        }
        String sRestFeatureType = "";
        ArrayList<Object> sRestFieldValue = new ArrayList<Object>();

        for (Map.Entry<String, String> entry : fieldDefinition.entrySet()) {
            sRestFeatureType += entry.getKey() + ":" + entry.getValue() + ",";
            sRestFieldValue.add(fieldValue.get(entry.getKey()));// 写入属性值
        }
        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "Location:LinearRing,"
                + // <- the geometry attribute: LineString type  
                "RingID:String," + sRestFeatureType
        );
        //GeometryFactory geometryFactory = new GeometryFactory();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        sRestFieldValue.add(0, linearRing);
        sRestFieldValue.add(1, ringID);
        Object[] obj = sRestFieldValue.toArray();
        SimpleFeature sfeature = featureBuilder.buildFeature(null, obj);
        return sfeature;
    }

    /**
     * 新建一个多边形要素
     *
     * @deprecated
     * @param polygon 多边形对象
     * @param polygonID 多边形ID
     * @param fieldDefinition 字段定义及数据类型
     * @param fieldValue 字段值
     * @return 多边形要素
     * @throws Exception
     */
    public static SimpleFeature createOnePolygonFeature(Polygon polygon, String polygonID,
            Map<String, String> fieldDefinition, Map<String, Object> fieldValue) throws Exception {
        int sFieldDefinationCount = fieldDefinition.size();
        int sFieldValueCount = fieldValue.size();
        if (sFieldDefinationCount != sFieldValueCount) {
            throw new Exception("字段定义与值数目不相等");
        }
        String sRestFeatureType = "";
        ArrayList<Object> sRestFieldValue = new ArrayList<Object>();

        for (Map.Entry<String, String> entry : fieldDefinition.entrySet()) {
            sRestFeatureType += entry.getKey() + ":" + entry.getValue() + ",";
            sRestFieldValue.add(fieldValue.get(entry.getKey()));// 写入属性值
        }
        final SimpleFeatureType TYPE = DataUtilities.createType("Location",
                "Location:Polygon,"
                + // <- the geometry attribute: LineString type  
                "PolygonID:String," + sRestFeatureType
        );
        //GeometryFactory geometryFactory = new GeometryFactory();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        sRestFieldValue.add(0, polygon);
        sRestFieldValue.add(1, polygonID);
        Object[] obj = sRestFieldValue.toArray();
        SimpleFeature sfeature = featureBuilder.buildFeature(null, obj);
        return sfeature;
    }

    /**
     * 获取要素源的要素类型
     *
     * @param sTargetFeatureSource 要素源
     * @return
     */
    public static SimpleFeatureType getSimpleFeatureType(SimpleFeatureSource sTargetFeatureSource) {
        return sTargetFeatureSource.getSchema();
    }

    /**
     * 获取要素源中字段的名称及数据类型
     *
     * @param sTargetFeatureSource 要素源
     * @return
     */
    public static HashMap<String, Class> getSimpleFeatureFields(SimpleFeatureSource sTargetFeatureSource) {
        int sFieldsCount = sTargetFeatureSource.getSchema().getAttributeCount();
        SimpleFeatureType sType = sTargetFeatureSource.getSchema();
        HashMap<String, Class> sFields = new HashMap<String, Class>();
        for (int i = 0; i < sFieldsCount; i++) {
            sFields.put(sType.getType(i).getName().toString(), sType.getType(i).getBinding());
        }
        return sFields;
    }

    /**
     * 将几何图形添加到要素源中
     *
     * @param sTargetFeatureSource 要素源
     * @param sTargetGeometry 几何图形
     * @param sFieldValue 属性值
     * @return 添加结果
     */
    public static boolean addGeometryToFeatureSource(SimpleFeatureSource sTargetFeatureSource, Geometry sTargetGeometry, Map<String, Object> sFieldValue) {
        //构建要素
        final SimpleFeatureType type = getSimpleFeatureType(sTargetFeatureSource);
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(type);
        featureBuilder.add(sTargetGeometry);
        HashMap<String, Class> sFieldDefinition = getSimpleFeatureFields(sTargetFeatureSource);
        for (Map.Entry<String, Class> entry : sFieldDefinition.entrySet()) {
            if (sFieldValue.containsKey(entry.getKey()) == true) {
                featureBuilder.set(entry.getKey(), sFieldValue.get(entry.getKey())); // 写入属性值
            }
        }
        SimpleFeature simpleFeature = featureBuilder.buildFeature(null);
        //构建事务，添加要素
        Transaction transaction = new DefaultTransaction("Append");
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.add(simpleFeature);
        try {
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Object getFeatureFieldDefaultValue(Class fieldClass) {
        if (fieldClass == int.class) {
            return 0;
        } else if (fieldClass == float.class) {
            return 0f;
        } else if (fieldClass == double.class) {
            return 0d;
        } else if (fieldClass == String.class) {
            return "0";
        } else {
            return 0;
        }
    }

    /**
     * 将几何要素添加到要素源（默认值）
     *
     * @param sTargetFeatureSource
     * @param sTargetGeometry
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws java.io.IOException
     */
    public static boolean addGeometryToFeatureSource(SimpleFeatureSource sTargetFeatureSource, Geometry sTargetGeometry) throws InstantiationException, IllegalAccessException, IOException {
        //检查shp与geometry是否同类型
        Class featureSourceClass = sTargetFeatureSource.getFeatures().getSchema().getType(0).getBinding();
        Class geometryClass = sTargetGeometry.getClass();
        if (featureSourceClass.equals(geometryClass) == false) {
            return false;
        }
        //构建要素
        final SimpleFeatureType type = getSimpleFeatureType(sTargetFeatureSource);
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(type);
        featureBuilder.add(sTargetGeometry);
        HashMap<String, Class> sFieldDefinition = getSimpleFeatureFields(sTargetFeatureSource);
        for (Map.Entry<String, Class> entry : sFieldDefinition.entrySet()) {
            if (entry.getValue() != featureSourceClass) {
                featureBuilder.set(entry.getKey(), getFeatureFieldDefaultValue(entry.getValue())); // 写入属性值
            }
        }
        SimpleFeature simpleFeature = featureBuilder.buildFeature(null);
        //构建事务，添加要素
        Transaction transaction = new DefaultTransaction("Append");
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.add(simpleFeature);
        try {
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将几何图形添加到图层中
     *
     * @param layer
     * @param sTargetGeometries
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static boolean addGeometriesToLayer(Layer layer, Geometry[] sTargetGeometries) throws IllegalAccessException, InstantiationException, IOException {
        //准备参数
        int sGeometryCout = sTargetGeometries.length;
        SimpleFeatureSource featureSource = (SimpleFeatureSource) layer.getFeatureSource();
        Class featureSourceClass = featureSource.getFeatures().getSchema().getType(0).getBinding();
        final SimpleFeatureType type = getSimpleFeatureType(featureSource);
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        for (int i = 0; i < sGeometryCout; i++) {
            //检查shp与geometry是否同类型
            Class geometryClass = sTargetGeometries[i].getClass();
            if (featureSourceClass.equals(geometryClass) == false) {
                return false;
            }
            //构建要素
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(type);
            featureBuilder.add(sTargetGeometries[i]);
            HashMap<String, Class> sFieldDefinition = getSimpleFeatureFields(featureSource);
            for (Map.Entry<String, Class> entry : sFieldDefinition.entrySet()) {
                if (entry.getValue() != featureSourceClass) {
                    featureBuilder.set(entry.getKey(), getFeatureFieldDefaultValue(entry.getValue())); // 写入属性值
                }
            }
            SimpleFeature simpleFeature = featureBuilder.buildFeature(null);
            collection.add(simpleFeature);
        }

        //构建事务，添加要素
        Transaction transaction = new DefaultTransaction("Append");
        try {
            if (featureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(featureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将要素添加至目标源
     *
     * @deprecated
     * @param sTargetFeatureSource 目标要素源
     * @param sTargetFeature 目标要素
     * @return 是否添加成功
     * @throws Exception
     */
    public static boolean addFeatureToFeatureSource(SimpleFeatureSource sTargetFeatureSource, SimpleFeature sTargetFeature) throws Exception {
        //判断两者要素类型是否一致
        /*这一部分代码还有bug，暂时不用，等找到更好的方法再更改
        String featureType = sTargetFeature.getAttribute(0).toString().split(" ")[0];
        String sourceType = sTargetFeatureSource.getFeatures().features().next().getAttribute(0).toString().split(" ")[0];
        if (featureType.toLowerCase().equals(sourceType.toLowerCase()) == false) {
            throw new Exception("要素类型不一致");
        }*/
        //添加事务
        Transaction transaction = new DefaultTransaction("Append");
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.add(sTargetFeature);
        try {
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从要素源中删除指定要素
     *
     * @param sTargetFeatureSource 要素源
     * @param sTargetFeature 指定的要素
     * @return 删除是否成功
     * @throws Exception
     */
    public static boolean deleteFeatureFromFeatureSource(SimpleFeatureSource sTargetFeatureSource, SimpleFeature sTargetFeature) throws Exception {
        //判断两者要素类型是否一致
        /*这一部分代码还有bug，暂时不用，等找到更好的方法再更改
        String featureType = sTargetFeature.getAttribute(0).toString().split(" ")[0];
        String sourceType = sTargetFeatureSource.getFeatures().features().next().getAttribute(0).toString().split(" ")[0];
        if (featureType.toLowerCase().equals(sourceType.toLowerCase()) == false) {
            throw new Exception("要素类型不一致");
        }*/
        //添加事务
        Transaction transaction = new DefaultTransaction("Delete");
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.add(sTargetFeature);
        FeatureIterator<SimpleFeature> itertor = collection.features();
        Set<FeatureId> fids = new HashSet<FeatureId>();
        while (itertor.hasNext()) {
            SimpleFeature feature = itertor.next();
            fids.add(feature.getIdentifier());
        }
        itertor.close();
        FilterFactory2 sFilterFactory2 = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
        Filter sRemoveFilter = (Filter) sFilterFactory2.id(fids);

        try {
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.removeFeatures(sRemoveFilter);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从要素源中删除若干指定要素
     *
     * @param sTargetFeatureSource 要素源
     * @param sTargetFeatures 指定的若干要素
     * @return 删除是否成功
     * @throws Exception
     */
    public static boolean deleteFeatureFromFeatureSource(SimpleFeatureSource sTargetFeatureSource, SimpleFeatureCollection sTargetFeatures) throws Exception {
        //判断两者要素类型是否一致
        /*这一部分代码还有bug，暂时不用，等找到更好的方法再更改
        String featureType = sTargetFeatures.features().next().getAttribute(0).toString().split(" ")[0];
        String sourceType = sTargetFeatureSource.getFeatures().features().next().getAttribute(0).toString().split(" ")[0];
        if (featureType.toLowerCase().equals(sourceType.toLowerCase()) == false) {
            throw new Exception("要素类型不一致");
        }*/
        //添加事务
        Transaction transaction = new DefaultTransaction("Delete");
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.addAll(sTargetFeatures);
        FeatureIterator<SimpleFeature> itertor = collection.features();
        Set<FeatureId> fids = new HashSet<FeatureId>();
        while (itertor.hasNext()) {
            SimpleFeature feature = itertor.next();
            fids.add(feature.getIdentifier());
        }
        itertor.close();
        FilterFactory2 sFilterFactory2 = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
        Filter sRemoveFilter = (Filter) sFilterFactory2.id(fids);
        try {
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.removeFeatures(sRemoveFilter);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从要素源中按筛选器删除要素
     *
     * @param sTargetFeatureSource 要素源
     * @param sDeleteFilter 删除筛选器
     * @return 删除是否成功
     * @throws Exception
     */
    public static boolean deleteFeatureFromFeatureSource(SimpleFeatureSource sTargetFeatureSource, Filter sDeleteFilter) throws Exception {
        try {
            Transaction transaction = new DefaultTransaction("Delete");
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.removeFeatures(sDeleteFilter);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改要素源中的要素
     *
     * @param sTargetFeatureSource 要素源
     * @param sModifyFilter 筛选器
     * @param sModifyValues 属性值
     * @return
     */
    public static boolean modifyFeatureInFeatureSource(SimpleFeatureSource sTargetFeatureSource, Filter sModifyFilter, Map<String, Object> sModifyValues) {
        try {
            //构建修改字段及字段值
            ArrayList<String> sFieldName = new ArrayList<String>();
            ArrayList<Object> sFieldValue = new ArrayList<Object>();
            if (sModifyValues != null) {
                for (Map.Entry<String, Object> entry : sModifyValues.entrySet()) {
                    sFieldName.add(entry.getKey());
                    sFieldValue.add(entry.getValue());
                }
            }

            String[] sFieldNames = new String[sFieldName.size()];
            //构建事务
            Transaction transaction = new DefaultTransaction("Delete");
            if (sTargetFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) sTargetFeatureSource;
                featureStore.setTransaction(transaction);
                try {
                    featureStore.modifyFeatures(sFieldName.toArray(sFieldNames), sFieldValue.toArray(), sModifyFilter);
                    transaction.commit();
                } catch (Exception problem) {
                    problem.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(sTargetFeatureSource.getSchema().toString() + " does not support read/write access");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将要素源转换为JsonArray
     *
     * @param sFeatureSource
     * @return
     * @throws IOException
     */
    public static JSONArray convertFeaturesToJSONArray(SimpleFeatureSource sFeatureSource) throws IOException {
        JSONArray sJsonArray = new JSONArray();
        SimpleFeatureIterator it = sFeatureSource.getFeatures().features();
        SimpleFeatureType sType = sFeatureSource.getSchema();
        WKTWriter wktWriter = new WKTWriter();
        try {
            while (it.hasNext()) {
                SimpleFeature feature = it.next();
                JSONObject jObject = new JSONObject();
                int attrCount = feature.getAttributeCount();
                jObject.put("the_geom", wktWriter.write((Geometry) feature.getAttribute(0)));
                for (int i = 1; i < attrCount; i++) {
                    Class sClass = sType.getType(i).getClass();
                    String sValue = "";
                    if (feature.getAttribute(i) == null) {
                        if (sClass == String.class) {
                            sValue = "";
                        } else {
                            sValue = "0";
                        }
                    } else {
                        sValue = feature.getAttribute(i).toString();
                    }
                    jObject.put(sType.getType(i).getName().toString(), sValue);
                }
                sJsonArray.add(jObject);
                //System.out.println(jObject);
            }
        } finally {
            it.close();
        }
        return sJsonArray;
    }

    /**
     * 将要素源的字段类型转换为JsonObject
     *
     * @param sFeatureSource
     * @return
     */
    public static JSONObject convertFeaturesFieldsToJSONObject(SimpleFeatureSource sFeatureSource) {
        JSONObject jObject = new JSONObject();
        SimpleFeatureType sType = sFeatureSource.getSchema();
        int attrCount = sType.getAttributeCount();
        for (int i = 0; i < attrCount; i++) {
            String key = sType.getType(i).getName().toString();
            if ("Point".equals(key) || "LineString".equals(key) || "LinearRing".equals(key) || "Polygon".equals(key)) {
                jObject.put("the_geom", sType.getType(i).getBinding().getName());
            } else {
                jObject.put(key, sType.getType(i).getBinding().getName());
            }
        }
        return jObject;
    }

    /**
     * 将Json格式转换为SimpleFeatureSource
     *
     * @param sFeatures
     * @param sDataType
     * @param sCachePath
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException
     */
    public static SimpleFeatureSource convertJSONObjectsToSimpleFeatureSource(JSONArray sFeatures, JSONObject sDataType, String sCachePath) throws MalformedURLException, IOException, ParseException, ClassNotFoundException {

        //create shape destination file object
        Map<String, Serializable> params = new HashMap<>();
        FileDataStoreFactorySpi factory = new org.geotools.data.shapefile.ShapefileDataStoreFactory();
        params.put(org.geotools.data.shapefile.ShapefileDataStoreFactory.URLP.key, new File(sCachePath).toURI().toURL());
        ShapefileDataStore ds = (ShapefileDataStore) factory.createNewDataStore(params);
        //SimpleFeatureSource featureSource = (SimpleFeatureSource) new DefaultFeatureCollection();
        SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
        Iterator iterator = sDataType.keys();
        tb.setCRS(DefaultGeographicCRS.WGS84);
        tb.setName("shapefile");
        HashMap<String, Class> featureFields = new HashMap<>();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Class value = Class.forName(sDataType.get(key).toString());
            if (key == "Point" || key == "LineString" || key == "LinearRing" || key == "Polygon") {
                tb.add("the_geom", value);
                featureFields.put("the_geom", value);
            } else {
                tb.add(key, value);
            }
            featureFields.put(key, value);
        }
        ds.createSchema(tb.buildFeatureType());

        //设置Writer  
        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
        WKTReader wktReader = new WKTReader();
        for (int i = 0, len = sFeatures.size(); i < len; i++) {
            String strFeature = sFeatures.get(i).toString();
            Reader reader = new StringReader(strFeature);
            SimpleFeature feature = writer.next();
            Iterator featureIterator = sFeatures.getJSONObject(i).keys();
            while (featureIterator.hasNext()) {
                String key = (String) featureIterator.next();
                Object value = sFeatures.getJSONObject(i).get(key);
                if (key == "the_geom") {
                    feature.setAttribute("the_geom", wktReader.read(value.toString()));
                } else {
                    feature.setAttribute(key, CastClass(featureFields.get(key),value));
                }
            }
            writer.write();
        }
 writer.close();  
        return ds.getFeatureSource();
    }

    private static Object CastClass(Class sClass, Object value) {
        if (Integer.class == sClass) {
            return Integer.parseInt(value.toString());
        } else if (Double.class == sClass) {
            return Double.parseDouble(value.toString());
        } else if (Float.class == sClass) {
            return Float.parseFloat(value.toString());
        } else {
            return value.toString();
        }
    }

    //调试时主函数
    public static void main(String[] args) throws Exception {

        double latitude = Double.parseDouble("120");
        double longitude = Double.parseDouble("40");
        Point testPoint = createOnePoint(latitude, longitude);
        /*
        String POIID = "139";
        String MESHID = "3";
        String OWNER = "cy";
        //Dictionary<String,Class> fieldDefination =new Dictionary<String,Class>();
        Map<String, String> fieldDefinition = new HashMap<String, String>();
        fieldDefinition.put("MeShID", "String");
        fieldDefinition.put("Owner", "String");
        //fieldDefinition.put("wtf", "String");
        Map<String, Object> fieldValue = new HashMap<String, Object>();
        fieldValue.put("pop", "54");
        fieldValue.put("sum", "2");
        // fieldValue.put("wtf", "wtf");

        Point testPoint = createOnePoint(latitude, longitude);
        SimpleFeature simpleFeature = createOnePointFeature2(testPoint, POIID, fieldDefinition, fieldValue);
        System.out.println(simpleFeature.getAttribute(0).getClass());*/
        File newFile = new File("F:\\ArcGISDoc\\suzhou\\test.shp");
        FileDataStore store = FileDataStoreFinder.getDataStore(newFile);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        Class featureSourceClass = featureSource.getFeatures().getSchema().getType(0).getBinding();
        Class geometryClass = testPoint.getClass();
        //SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;  
        try {
            //之前的测试
            //System.out.println(addGeometryToFeatureSource(featureSource, testPoint, fieldValue));
            addGeometryToFeatureSource(featureSource, testPoint);
            //测试convertFeaturesToJSONArray函数
            JSONObject jObject = new JSONObject();
            //jObject.put("Features",convertFeaturesToJSONArray(featureSource));
            jObject = convertFeaturesFieldsToJSONObject(featureSource);
            System.out.println(jObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
