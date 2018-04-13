package com.flyingideal;

import com.flyingideal.dao.UserMapper;
import com.flyingideal.entity.UserMaster;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author yanchao
 * @date 2018/3/16 14:07
 */
public class SqlSessionFactoryTest {

    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession(true);

        // selectOne()及结果映射测试
        /*UserMaster userMaster = session.selectOne("com.flyingideal.dao.UserMasterDao.getUserByUsername", "name_mysql");
        System.out.println(userMaster);*/

        // 使用<mapper class=''>配置方式测试
        /*UserMaster userMaster1 = session.selectOne("com.flyingideal.dao.UserMapper.getUserById", 1);
        System.out.println(userMaster1);*/

        // 结果为单值映射测试
        /*String name = session.selectOne("com.flyingideal.dao.UserMasterDao.getUsernameById", 1);
        System.out.println(name);*/

        // selectList()及结果测试
        /*List<String> names = session.selectList("com.flyingideal.dao.UserMasterDao.getAllUsername");
        System.out.println(names);*/

        // insert() 主键测试
        /*UserMaster userMaster = new UserMaster("test2", "test2@test.com", "234565", "location2");
        int count = session.insert("com.flyingideal.dao.UserMasterDao.addUser", userMaster);
        System.out.println(count);
        System.out.println(userMaster);*/

        // 调用接口测试：先获取“接口对象”（是一个代理对象），然后调用方法
        /**
         * 1. 获取Configuration对象，因为要调用其getMapper(Class, SqlSession)方法；
         * 2. 调用getMapper(Class, SqlSession)获取一个“接口对象”（其实是一个代理对象）；
         * 3. 使用第二步拿到的对象调用相应的方法执行查询。
         */
        Configuration configuration = session.getConfiguration();
        // Configuration configuration1 = factory.getConfiguration();
        UserMapper userMapper = configuration.getMapper(UserMapper.class, session);
        UserMaster userMaster2 = userMapper.getUserById(1);
        System.out.println(userMaster2);

        /*UserMaster userMaster = session.selectOne("com.flyingideal.dao.UserMapper.getUserById", 1);
        System.out.println(userMaster);*/
    }

}
