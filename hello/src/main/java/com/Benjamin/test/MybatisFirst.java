package com.Benjamin.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MybatisFirst {
    @Test

    public void findStudentByIdTest() {
        String resource = "SqlMapConfig.xml";
        SqlSession sqlSession = null;
        try (
                InputStream inputStream = Resources.getResourceAsStream(resource)
        ) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            sqlSession = sqlSessionFactory.openSession();

            Date date = new Date();

            for (int i = 1; i <= 10000000; i++) {
                Map<String, Integer> paraMap = new HashMap<>();
                paraMap.put("id", i);
                paraMap.put("val", i);
                paraMap.put("ind", i);
                sqlSession.insert("insertIntoTable", paraMap);
            }

            System.out.println((new Date().getTime()-date.getTime())/(1000*60));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
}
