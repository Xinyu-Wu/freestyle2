/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.datasource;

import com.vividsolutions.jts.geom.Coordinate;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author secre
 */
public class ArrayListtoArray {
    /**
     * @Coordinate转为int类型的longitude数组
     * @param arrayList coordinate列表
     * @return 
     */
    static public int[] CoordinatetoArrayIntLon(ArrayList<Coordinate> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = (int) arrayList.get(i).x;
        }
        return array;
    }

    /**
     * @Coordinate转为int类型的latitude数组
     * @param arrayList coordinate列表
     * @return 
     */
    static public int[] CoordinatetoArrayIntLat(ArrayList<Coordinate> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = (int) arrayList.get(i).y;
        }
        return array;
    }

    /**
     * @Coordinate转为double类型的longitude数组
     * @param arrayList coordinate列表
     * @return 
     */
    static public double[] CoordinatetoArrayDoubleLon(ArrayList<Coordinate> arrayList) {
        double[] array = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i).x;
        }
        return array;
    }

    /**
     * @Coordinate转为double类型的latitude数组
     * @param arrayList coordinate列表
     * @return 
     */
    static public double[] CoordinatetoArrayDoubleLat(ArrayList<Coordinate> arrayList) {
        double[] array = new double[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i).y;
        }
        return array;
    }
    
    /**
     * @Arraylist与int数组转换
     * @param arrayList Integer列表
     * @return 
     */
    //
    static public int [] ArrayListtoIntArray(ArrayList<Integer> arrayList){
        int[] array=new int[arrayList.size()];
        for(int i=0;i<arrayList.size();i++)
        {
            array[i]=arrayList.get(i);
        }
        return array;
    }
    
    /**
     * @Point转为int类型的x坐标数组
     * @param arrayList Point列表
     * @return 
     */
    static public int[] PointtoArrayIntX(ArrayList<Point> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = (int) arrayList.get(i).x;
        }
        return array;
    }

    /**
     * @Point转为int类型的y坐标数组
     * @param arrayList Point列表
     * @return 
     */
    static public int[] PointtoArrayIntY(ArrayList<Point> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = (int) arrayList.get(i).y;
        }
        return array;
    }
}
