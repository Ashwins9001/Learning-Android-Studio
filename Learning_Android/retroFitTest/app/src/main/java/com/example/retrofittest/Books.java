package com.example.retrofittest;

public class Books {
    //JSON obj fields within items array to retrieve
   /* private int pageCount;
    private int averageRating;
    private String id;
    private String publisher;
*/
   private String name;

   /*
    public Books(int pageCount, int averageRating, String id, String publisher)
    {

        this.pageCount = pageCount;
        this.averageRating = averageRating;
        this.id = id;
        this.publisher = publisher;
    }*/
   public Books(String name){this.name = name;}
   /*
    public String getId(){return id;}
    public String getPublisher(){return publisher;}
    public int getPageCount(){
        return pageCount;
    }
    public int getAverageRating(){
        return averageRating;
    }*/
   public String getName(){return name;}
}
