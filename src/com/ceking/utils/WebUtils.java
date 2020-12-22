package com.ceking.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;

/*
 *@author ceking
 *@description
 *@date 2020-12-21 14:18
 */
public class WebUtils {

    /**
     * 将所有请求参数封装到JavaBean属性中
     *
     * @param value
     * @param t
     */
    public static <T> T copyParamToBean(Map value, T t) {
        try {
            BeanUtils.populate(t, value);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转Int
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 字符串转double
     * @param str
     * @param defaultValue
     * @return
     */
    public  static Double parseDouble(String str,double defaultValue){
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return  defaultValue;
    }
}
