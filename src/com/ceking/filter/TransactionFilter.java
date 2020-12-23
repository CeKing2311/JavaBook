package com.ceking.filter;

import com.ceking.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/*
 *@author ceking
 *@description
 *@date 2020-12-23 11:11
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            //提交事务
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            JDBCUtils.rollBackAndClose();
            //将异常抛给Tomcat服务器
            throw new  RuntimeException(e);
        }

    }

    @Override
    public void destroy() {

    }
}
