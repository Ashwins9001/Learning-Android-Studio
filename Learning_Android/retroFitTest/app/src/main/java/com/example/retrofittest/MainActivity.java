package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class appendStr {String x; appendStr(String s) {x = s;}}
class checkList{String y; checkList(String s){y = s;}}




//Used to create (string,string) pairs for recyclerView
/*
class StationRoutes{
    String station;
    String route;

    public StationRoutes(String station, String route)
    {
        this.station = station;
        this.route = route;
    }
}*/

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private ArrayList<Books> data;
    private ArrayList<Routes> routeData;
    StringBuilder builder = new StringBuilder();
    appendStr stations = new appendStr("");
    checkList check = new checkList("");
    StringBuilder routesForStation = new StringBuilder();
    StringBuilder name = new StringBuilder();
    String name2="";


    ArrayList<String> allStationBuses = new ArrayList<>();

    List<String> forRecyclerView = new ArrayList<>();

   // List<RouteList> rAllItems = new ArrayList<>();


/*    static class RouteList{
        String allRoutes;
       // ArrayList<String> allBuses;
        String allBuses;
        public RouteList(String allRoutes, String allBuses)
        {
            this.allBuses = allBuses;
            this.allRoutes = allRoutes;
        }
    }
    int count = 0;

    static class ViewHolder{
        TextView routeName;
        TextView routeDescription;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GridView routeGrid = new GridView(this);
        setContentView(routeGrid);

        routeGrid.setNumColumns(2);

        routeGrid.setColumnWidth(60);
        routeGrid.setVerticalSpacing(20);
        routeGrid.setHorizontalSpacing(20);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myttc.ca/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api api = retrofit.create(Api.class);

        Call<JSONResponse> call1 = api.getFinch();

        call1.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                JSONResponse jsonData = response.body();
                data = new ArrayList<>(Arrays.asList(jsonData.getBooks()));
                Books[] temp = jsonData.getBooks();
                for(int i = 0; i < data.size(); i++)
                {
                    builder.append(data.get(i).getName() + "\n");
                    name.append(data.get(i).getName());
                    routeData = new ArrayList<>(Arrays.asList(temp[i].getRoutes()));
                    appendRoutes(builder, routeData, data.get(i).getName());
                    routesForStation.delete(0, routesForStation.length());
                }
                ArrayAdapter<String> routeAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.item, R.id.routes, forRecyclerView);
                routeGrid.setAdapter(routeAdapter);

                Log.w("Output", builder.toString());
            }
            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });


    }

    public void appendRoutes(StringBuilder b, ArrayList<Routes> addRoute, String currentRoute)
    {
        b.delete(0, b.length());
        for (int i = 0; i < addRoute.size(); i++)
        {
            b.append("Route number " + i + " : " + addRoute.get(i).getRoutename() + "\n");
            routesForStation.append("Route number " + i + " : " + addRoute.get(i).getRoutename() + "\n");

          //  allStationBuses.add(addRoute.get(i).getRoutename());
        }
        String temp = currentRoute + "\n\n" + b.toString() + "\n";
        check.y = temp;
        forRecyclerView.add(check.y);
        Log.w("Strings", check.y);

        //rAllItems.add(new RouteList(currentRoute, routesForStation.toString()));
        b.append("\n\n\n");
        stations.x += b.toString();

    }
    /*
    public void logRoutes()
    {
        for(int i = 0; i < forRecyclerView.size(); i++)
        {
            Log.w("Route List", forRecyclerView.get(i));
        }
    }*/
}