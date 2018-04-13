package com.flyingideal.mybatiscomponentTest;

import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;

/**
 * @author yanchao
 * @date 2018/3/21 17:45
 */
public class ResultMappingTest {

    public static void main(String[] args) {
        ResultMapping.Builder builder = new ResultMapping.Builder(new Configuration(), "A");
        ResultMapping mapping1 = builder.column("column").nestedQueryId("queryId").build();
        ResultMapping.Builder builder2 = new ResultMapping.Builder(new Configuration(), "A");
        ResultMapping mapping2 = builder2.column("column3").nestedQueryId("queryId3").build();
        System.out.println(mapping1.equals(mapping2));
    }
}
