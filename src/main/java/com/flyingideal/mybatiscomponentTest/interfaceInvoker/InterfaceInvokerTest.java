package com.flyingideal.mybatiscomponentTest.interfaceInvoker;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.entity.UserMaster;
import com.flyingideal.mybatiscomponentTest.BaseTest;

/**
 * @author yanchao
 * @date 2018/4/13 17:32
 */
public class InterfaceInvokerTest extends BaseTest {

    public static void main(String[] args) {
        InterfaceInvokerTest iit = new InterfaceInvokerTest();
        UserMapper userMapper = InterfaceInvocationHandler.getInstance(
                iit.getSqlSession(), UserMapper.class);
        UserMaster userMaster = userMapper.getUserById(1);
        System.out.println(userMaster);
    }
}
