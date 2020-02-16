package com.example.retrofittest;

public class JSONResponse {
    private Books[] stops;
    //private Books[][] routes;
    //public Books[][] getRoutes() { return routes; }
    public Books[] getBooks()
    {
        return stops;
    }
}
