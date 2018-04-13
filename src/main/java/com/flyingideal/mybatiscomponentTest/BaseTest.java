package com.flyingideal.mybatiscomponentTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yanchao
 * @date 2018/4/13 17:34
 * @function 基础测试类，方便获取执行查询时的各个组件
 */
public abstract class BaseTest {

    private Configuration configuration;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private String configPath = "mybatis-config.xml";

    public BaseTest() {
        InputStream is = null;
        try {
             is = Resources.getResourceAsStream(configPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is == null) {
            throw new NullPointerException(configPath + " 配置文件不存在！");
        }
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        this.sqlSession = this.sqlSessionFactory.openSession();
        this.configuration = this.sqlSessionFactory.getConfiguration();

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
}
