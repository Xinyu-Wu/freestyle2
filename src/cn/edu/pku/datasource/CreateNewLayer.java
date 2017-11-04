/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import java.awt.Point;
import javafx.scene.shape.Polyline;
import jdk.nashorn.internal.parser.TokenType;
import org.geotools.data.DataUtilities;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.map.FeatureLayer;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 *
 * @author secre
 */
public class CreateNewLayer {

    /**
     * @创建图层
     * @param layername 名称
     * @param geom 图层类型
     * @return 
     */
    static public FeatureLayer CreateLayer(String layername, String geom) {
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        typeBuilder.setCRS(DefaultGeographicCRS.WGS84);
        typeBuilder.setName("shapefile");
        if (geom == "Point") {
            typeBuilder.add("the_geom", Point.class);
        } else if (geom == "Line") {
            typeBuilder.add("the_geom", Polyline.class);
        } else {
            typeBuilder.add("the_geom", Polygon.class);
        }
        typeBuilder.add("NAME", String.class);
        //simpleFeatureTypeBuilder.setDefaultGeometry("the_geom");
        SimpleFeatureType TYPE = typeBuilder.buildFeatureType();
        ListFeatureCollection collection= new ListFeatureCollection(TYPE);
        FeatureLayer newLayer = new FeatureLayer(collection, null, layername);
        
        return newLayer;

    }

}
