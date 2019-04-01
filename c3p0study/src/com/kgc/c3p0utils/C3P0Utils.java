package com.kgc.c3p0utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class C3P0Utils {
   /**
    * @program：c3p0study
    * @descriptionc3p0工具类
    * @author:梁大祥
    * @create：2019--01--01 10:22
    */
   //加载四大配置参数,创建都是对象时，会读取特定位置的消灭了配置文件中的信息，将四大参数封装进多少对象
   private static DataSource ds = new ComboPooledDataSource();

   private C3P0Utils() {
   }

   public static DataSource getDs() {
      return ds;
   }

   public static Connection getcon() throws Exception {
      // ds.getConnection()得到的是代理连接对象
      return ds.getConnection();
   }

}
