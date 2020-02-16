package com.example.retrofittest;

import java.util.ArrayList;

public class Books {
   private String name;
   private Routes[] routes;
   public Books(String name){
      this.name = name;
   }
   public String getName(){return name;}
   public Routes[] getRoutes() { return routes; }
}