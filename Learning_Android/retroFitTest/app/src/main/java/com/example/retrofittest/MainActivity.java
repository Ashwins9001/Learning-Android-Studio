package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class appendStr {String x; appendStr(String s) {x = s;}}

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private ArrayList<Books> data;
    private ArrayList<Routes> routeData;
    StringBuilder builder = new StringBuilder();
    appendStr stations = new appendStr("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);
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
               // appendStations(builder, data);
                for(int i = 0; i < data.size(); i++)
                {
                    builder.append(data.get(i).getName() + "\n");
                    Books[] temp = jsonData.getBooks();
                    routeData = new ArrayList<>(Arrays.asList(temp[i].getRoutes()));

                    appendRoutes(builder, routeData);
                }
                textViewResult.setText(stations.x);
            }
            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void appendStations(StringBuilder b, ArrayList<Books> addData) {

        for (int i = 0; i < addData.size(); i++) {
            b.append(addData.get(i).getName() + "\n");
        }
        stations.x += b.toString();
    }
    public void appendRoutes(StringBuilder b, ArrayList<Routes> addRoute)
    {
        for (int i = 0; i < addRoute.size(); i++) {
            b.append("Route number " + i + " : " + addRoute.get(i).getRoutename() + "\n");
        }
        b.append("\n\n\n");
        stations.x += b.toString();
    }
}