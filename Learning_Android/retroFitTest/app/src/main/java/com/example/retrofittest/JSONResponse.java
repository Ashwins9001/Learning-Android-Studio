package com.example.retrofittest;

public class JSONResponse {
    //JSON response to parse, which will look for an array
    //Define array ocntents in separate class
    //Interface (API) implements call in terms of this response, searches for it
    private Books[] items;

    public Books[] getBooks()
    {
        return items;
    }
}
