package com.kgc.dao;

import com.kgc.c3p0utils.C3P0Utils;
import com.kgc.pojo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Demo3 {
   /**
    * @program：c3p0study
    * @description
    * @author:梁大祥
    * @create：2019--01--02 14:02
    */
   public static void main(String[] args) throws Exception {
     /* Book book = new Book();
      book.setId(32);
      book.setName("sadF");
      add(book);*/
//     add2(34,"我爱你中国");
//      fun1();
//      fun2();
      fun3();

   }

   private static void fun3() throws SQLException {
      System.out.println(new QueryRunner(C3P0Utils.getDs()).query("select * from book where id=30",new ScalarHandler<String>(2)));
   }

   private static void fun2() throws SQLException {
      System.out.println(new QueryRunner(C3P0Utils.getDs()).query("select * from book where id=30", new BeanHandler<Book>(Book.class)));
   }

   private static void fun1() throws SQLException {
      List<Book> li = new QueryRunner(C3P0Utils.getDs()).query("select * from book", new BeanListHandler<Book>(Book.class));
      System.out.println(li);
   }

   /**
    * 使用object形参数组作为参数
    */
   private static void add2(Object... obj) throws Exception {
      Connection con = C3P0Utils.getcon();
      PreparedStatement pps = con.prepareStatement("insert into book(id,name) values (?,?)");
      for (int i = 0; i < obj.length; i++) {
         pps.setObject(i + 1, obj[i]);
      }
      pps.executeUpdate();
   }

   /**
    * 使用JavaBean对象作为参数实现新增
    */
   private static void add(Book book) throws Exception {
      Connection con = C3P0Utils.getcon();
      PreparedStatement pps = con.prepareStatement("insert into book(id,name) values (?,?)");
      pps.setInt(1, book.getId());
      pps.setString(2, book.getName());
      pps.executeUpdate();
   }
}
