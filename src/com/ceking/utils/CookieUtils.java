package com.ceking.utils;

import com.sun.org.apache.regexp.internal.REUtil;

import javax.servlet.http.Cookie;

/*
 *@author ceking
 *@description
 *@date 2020-12-22 11:38
 */
public class CookieUtils {

    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        } else {
            for (Cookie cookie:cookies){
                if (name.equals(cookie.getName())){
                    return  cookie;
                }
            }
        }
        return  null;
    }
}
