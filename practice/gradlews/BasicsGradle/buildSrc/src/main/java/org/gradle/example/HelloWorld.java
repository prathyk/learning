package org.gradle.example;

import groovy.lang.MissingPropertyException;
class HelloWorld{
   public static void main(String [] args){
      System.out.println("Hello, world");
   }

   public String name;

   public void setName(String value){
       this.name = value;
   }

   public String getName(){
       return name;
   }

   public String getProperty(String prop){
       return "Unknown Property";
   }

   public boolean hasProperty(String prop){
      return true;
   }
   
   public Object property(String propertyName) throws MissingPropertyException {
	        return "TestString";
   }


}
