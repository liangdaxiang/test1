package com.kgc.dao;

import com.kgc.c3p0utils.C3P0Utils;
import com.kgc.pojo.Book;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {
   /**
    * @program：c3p0study
    * @description用c3p0连接池升级增删改查
    * @author:梁大祥
    * 张宙啊啊
    * @create：2019--01--01 11:07
    我张宙到此一游啊
    我张宙又来了
    */
   public static void main(String[] args) throws Exception {
//      fun1(29,"课工场的峥嵘岁月","张宙","2011-12-1",32.5,2);//新增，用object形参数组作为参数
//      fun2(new Book(30,"我在k8512当老大的那几年","肖杏",new SimpleDateFormat("yyyy-MM-dd").parse("2007-5-20"),16.5,5));//新增，用JavaBean作为参数
//      System.out.println(fun3(Book.class,30));//查询单行数据
      List<Book> list = fun4(Book.class);//查询所有数据--使用泛型
      for (Book b:list){
         System.out.println(b);
      }

   }

   /**
    * 查询所有数据--使用泛型
    */
   private static<T> List<T>  fun4(Class<T> c) throws Exception {
      Connection con = C3P0Utils.getcon();
      String name = c.getSimpleName().toLowerCase();
      List<T> list = new ArrayList();
      String sql="select * from "+name;
      PreparedStatement pps = con.prepareStatement(sql);
      ResultSet rs = pps.executeQuery();
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      while (rs.next()){
         T t = c.newInstance();
         for (int i = 1; i <=columnCount ; i++) {
            String columnName = metaData.getColumnName(i);
            Field f = c.getDeclaredField(columnName);
            f.setAccessible(true);
            Object o = rs.getObject(i);
            if (o!=null){
               f.set(t,o);
            }
         }
         list.add(t);
      }
      return list;
   }

   /**
    * 查询单行数据
    */
   private static <T> T fun3(Class<T> c, int id) throws Exception {
      Connection con = C3P0Utils.getcon();
      String sql = "select * from book where id=?";
      PreparedStatement pps = con.prepareStatement(sql);
      pps.setInt(1, id);
      ResultSet rs = pps.executeQuery();
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      T t = c.newInstance();
      if (rs.next()) {
         for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            Field f = c.getDeclaredField(columnName);
            f.setAccessible(true);
            Object o = rs.getObject(i);
            if (o != null) {
               /**
                * 对应的属性的管理对象为t对向中所对应的属性赋值
                */
               f.set(t, o);
            }
         }
      }
      return t;
   }

   /**
    * 新增，用JavaBean作为参数
    * 特点：编码复杂，传参灵活
    */
   private static void fun2(Book book) throws Exception {
      Connection con = C3P0Utils.getcon();
      String sql = "insert into book values(?,?,?,?,?,?)";
      PreparedStatement pps = con.prepareStatement(sql);
      pps.setInt(1, book.getId());
      pps.setString(2, book.getName());
      pps.setString(3, book.getAuthor());
      pps.setDate(4, new Date(book.getSaleTime().getTime()));
      pps.setDouble(5, book.getPrice());
      pps.setInt(6, book.getType());
      pps.executeUpdate();
   }

   /**
    * 新增，用object形参数组作为参数
    * 特点：编码简单，传参复杂
    */
   private static void fun1(Object... obj) throws Exception {
      Connection con = C3P0Utils.getcon();
      String sql = "insert into book values(?,?,?,?,?,?)";
      PreparedStatement pps = con.prepareStatement(sql);
      for (int i = 0; i < obj.length; i++) {
         pps.setObject(i + 1, obj[i]);
      }
      pps.executeUpdate();
   }
}
