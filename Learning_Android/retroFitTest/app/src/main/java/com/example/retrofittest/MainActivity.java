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
                if (!response.isSuccessful()) {return;}
                JSONResponse jsonData = response.body();
                data = new ArrayList<>(Arrays.asList(jsonData.getBooks()));
                Books[] temp = jsonData.getBooks();
                for(int i = 0; i < data.size(); i++)
                {
                    builder.append(data.get(i).getName() + "\n");
                    routeData = new ArrayList<>(Arrays.asList(temp[i].getRoutes()));
                    appendRoutes(builder, routeData, data.get(i).getName());
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
        }
        String temp = currentRoute + "\n\n" + b.toString() + "\n";
        forRecyclerView.add(temp);
        Log.w("Strings", temp);
    }
}