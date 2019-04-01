package com.kgc.pojo;

import java.util.Date;

public class Book {
   private int id;
   private String name;
   private String author;
   private Date saleTime;
   private double price;
   private int type;

   public Book() {
   }

   public Book(String name, String author, Date saleTime) {
      this.name = name;
      this.author = author;
      this.saleTime = saleTime;
   }

   public Book(int id, String name, String author, Date saleTime, double price, int type) {
      this.id = id;
      this.name = name;
      this.author = author;
      this.saleTime = saleTime;
      this.price = price;
      this.type = type;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public Date getSaleTime() {
      return saleTime;
   }

   public void setSaleTime(Date saleTime) {
      this.saleTime = saleTime;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public int getType() {
      return type;
   }

   public void setType(int type) {
      this.type = type;
   }

   @Override
   public String toString() {
      return "Book{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", author='" + author + '\'' +
              ", saleTime=" + saleTime +
              ", price=" + price +
              ", type=" + type +
              '}';
   }


   public String toString1() {
      return name + "," + author + "," + saleTime + "," + price + "," + type+","+id;
   }
}
