package com.flyingideal.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author yanchao
 * @date 2018/3/27 15:14
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyInterceptor1 implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyInterceptor1 " + invocation.getMethod().getName());
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}
