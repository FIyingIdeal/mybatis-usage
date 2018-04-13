package com.flyingideal.mybatiscomponentTest.interfaceInvoker;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * @author yanchao
 * @date 2018/4/13 16:56
 */
public class InterfaceInvocationHandler implements InvocationHandler {

    private SqlSession sqlSession;
    private Class target;

    private InterfaceInvocationHandler(SqlSession sqlSession, Class target) {
        this.sqlSession = sqlSession;
        this.target = target;
    }

    public static <T> T getInstance(SqlSession sqlSession, Class<T> target) {
        if (!target.isInterface()) {
            // 暂时使用这一个异常代替
            throw new ClassCastException(target.getName() + "不是interface类型！");
        }
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target},
                new InterfaceInvocationHandler(sqlSession, target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();
        // 获取statementId
        String statementId = this.target.getName() + "." + methodName;
        Object params = null;
        params = args[0];
        Object result;
        if (Collection.class.isAssignableFrom(returnType)) {
            result = sqlSession.selectList(statementId, args);
        } else {
            result = sqlSession.selectOne(statementId, params);
        }
        return result;
    }
}
