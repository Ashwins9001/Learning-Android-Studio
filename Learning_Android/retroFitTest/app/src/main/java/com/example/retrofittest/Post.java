package com.example.retrofittest;

import com.google.gson.annotations.SerializedName;

public class Post {
    //@SerializedName("value")
    //private String val;

    //Use JSON obj (key-val pair) to create Java obj, done automatically assuming
    //Var name and JSON key match, yet if they differ must apply SerializedName annotation
    //GSON handles
    //POST array gets retrieved from REST API via RetroFit
    //@SerializedName("body")
    private String body;

    public String getDescription() {
        return body;
    }

    //public String getInstructions() {
    //    return strDescription;
    //}
}
