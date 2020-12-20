package com.ceking.test;

import com.ceking.utils.JDBCUtils;
import org.junit.Test;

/**
 * @author ceking
 * @desc
 * @date 2020/12/19 23:27
 */
public class JDBCUtilsTest {
    @Test
    public  void  testJdbcUtils(){
        System.out.println(JDBCUtils.getConnection());
    }
}
