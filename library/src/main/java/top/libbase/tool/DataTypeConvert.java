package top.libbase.tool;

import android.os.Bundle;

/**
 * 数据转换工具
 * Created by us on 2017/6/24.
 */
public final class DataTypeConvert {

    /**
     * 装换成int类型
     * @param value
     * @param defaultValue
     * @return
     */
    public final static int convertToInt(Object value,int defaultValue){
        if(value==null||"".equals(value.toString().trim())){
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString()).intValue();
        } catch (NumberFormatException e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (NumberFormatException e1) {
                return defaultValue;
            }
        }
    }

    /**
     * convertToDouble装换成double
     * @param value
     * @param defaultValue
     * @return
     */
    public final static double convertToDouble(Object value,double defaultValue){
        if(value==null||"".equals(value.toString().trim())){
            return defaultValue;
        }
        try {
            return Double.valueOf(value.toString()).doubleValue();
        } catch (NumberFormatException e1) {
            return defaultValue;
        }
    }

    /**
     * convertToLong 装换成long类型
     * @param value
     * @param defaultValue
     * @return
     */
    public final static long convertToLong(Object value,long defaultValue){
        if(value==null||"".equals(value.toString().trim())){
            return defaultValue;
        }
        try {
            return Long.valueOf(value.toString()).longValue();
        } catch (NumberFormatException e1) {
            return defaultValue;
        }
    }

    /**
     * convertToString 装换成String类型
     * @param value
     * @param defaultValue
     * @return
     */
    public final static String convertToString(Object value,String defaultValue){
        if(value==null){
            return defaultValue;
        }
        return value.toString();
    }
}
