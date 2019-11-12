package com.Benjamin.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MybatisFirst {
    @Test
    public void findStudentByIdTest() throws InterruptedException {

        Demo demo0_100 = new Demo(0*1000, 1000);
        Demo demo100_200 = new Demo(1*1000, 1000);
        Demo demo200_300 = new Demo(2*1000, 1000);
        Demo demo300_400 = new Demo(3*1000, 1000);
        Demo demo400_500 = new Demo(4*1000, 1000);
        Demo demo500_600 = new Demo(5*1000, 1000);
        Demo demo600_700 = new Demo(6*1000, 1000);
        Demo demo700_800 = new Demo(7*1000, 1000);
        Demo demo800_900 = new Demo(8*1000, 1000);
        Demo demo900_1000 = new Demo(9*1000, 1000);
        Thread th1 = new Thread(demo0_100);
        Thread th2 = new Thread(demo100_200);
        Thread th3 = new Thread(demo200_300);
        Thread th4 = new Thread(demo300_400);
        Thread th5 = new Thread(demo400_500);
        Thread th6 = new Thread(demo500_600);
        Thread th7 = new Thread(demo600_700);
        Thread th8 = new Thread(demo700_800);
        Thread th9 = new Thread(demo800_900);
        Thread th10 = new Thread(demo900_1000);
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th6.start();
        th7.start();
        th8.start();
        th9.start();
        th10.start();
        th1.join();
        th2.join();
        th3.join();
        th4.join();
        th5.join();
        th6.join();
        th7.join();
        th8.join();
        th9.join();
        th10.join();
    }
    @Test
    public void findStudentByIdTest1() throws InterruptedException {

        Demo demo0_100 = new Demo(0, 10);
        Thread th1 = new Thread(demo0_100);
        th1.start();
        th1.join();
    }

    class Demo implements Runnable{

        private int start;
        private int nums;

        public Demo(int start, int nums) {
            this.start = start;
            this.nums = nums;
        }

        @Override
        public void run() {
            String resource = "SqlMapConfig.xml";
            SqlSession sqlSession = null;
            try (
                    InputStream inputStream = Resources.getResourceAsStream(resource)
            ) {
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

                sqlSession = sqlSessionFactory.openSession();

                Date date = new Date();

                for (int i = start; i < start+nums; i++) {
                    List<Data> datas = new ArrayList<>(1000);
                    for (int j = 0; j < 1000; j++) {
                        datas.add(new Data(i*1000+j, i*1000+j));
                    }
                    sqlSession.insert("insertIntoTable", datas);
                    System.out.println(">>> " + i*1000);
                }

                System.out.println((new Date().getTime()-date.getTime())/(1000*60));
                sqlSession.commit();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (sqlSession != null)
                    sqlSession.close();
            }
        }
    }

}
