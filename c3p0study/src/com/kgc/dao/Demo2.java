package com.kgc.dao;

import com.kgc.c3p0utils.C3P0Utils;
import com.kgc.pojo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class Demo2 {
   /**
    * @program：c3p0study
    * @descriptiondbutils使用,c3p0+dbutils改版升级简化增删改查
    * @author:梁大祥
    * @create：2019--01--01 13:25
    */
   public static void main(String[] args) throws Exception {
//      fun1();//查询结果集
//      fun2();//查询单行数据
//      fun3(30);
//      fun4();
//      fun5();//新增一条数据

   }

   /**
    * 新增一条数据
    */
   private static void fun5() throws SQLException {
      QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
      qr.update("insert into book values(?,?,?,?,?,?)", 31, "我为什么那么优秀", "肖杏", "2005-8-20", 20.8, 4);
   }

   /**
    * 查询类型为1的总数--使用聚合函数
    */
   private static void fun4() throws SQLException {
      QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
      Long i = qr.query("select count(*) from book where type =?", new ScalarHandler<Long>(), 3);
      System.out.println(i);
   }

   /**
    * 查询指定列
    */
   private static void fun3(int id) throws SQLException {
      QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
      String s = qr.query("select name from book where id=?", new ScalarHandler<String>(1), id);
      System.out.println(s);

   }

   /**
    * 查询单行数据
    */
   private static void fun2() throws SQLException {
      QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
      Book book = qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class), 5);
      System.out.println(book);
   }

   /**
    * 查询结果集
    */
   private static void fun1() throws Exception {
      DataSource ds = C3P0Utils.getDs();
      QueryRunner qr = new QueryRunner(ds);
      List<Book> list = qr.query("select * from book ", new BeanListHandler<Book>(Book.class));
      for (Book b : list) {
         System.out.println(b);
      }
   }
}
