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

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private ArrayList<Books> data;

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
        Call<JSONResponse> call2 = api.getUnion();
        Call<JSONResponse> call3 = api.getSpadina();
        StringBuilder builder = new StringBuilder();

        call1.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                JSONResponse jsonData = response.body();
                data = new ArrayList<>(Arrays.asList(jsonData.getBooks()));

                for(int i = 0; i < data.size(); i++)
                {
                    builder.append(data.get(i).getName() + "\n" );
                    //builder.append(data.get(i).getAverageRating() + "\n" );
                }
                Log.w("Output", builder.toString());


                // textViewResult.setText(builder.toString());
                //textViewResult.setText(data.get(0).getName());
                //Log.w("Output", data.get(0).getName());
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        call2.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                JSONResponse jsonData = response.body();
                data = new ArrayList<>(Arrays.asList(jsonData.getBooks()));

                for(int i = 0; i < data.size(); i++)
                {
                    builder.append(data.get(i).getName() + "\n" );
                    //builder.append(data.get(i).getAverageRating() + "\n" );
                }
                Log.w("Output", builder.toString());


                // textViewResult.setText(builder.toString());
                //textViewResult.setText(data.get(0).getName());
                //Log.w("Output", data.get(0).getName());
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        call3.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                JSONResponse jsonData = response.body();
                data = new ArrayList<>(Arrays.asList(jsonData.getBooks()));

                for(int i = 0; i < data.size(); i++)
                {
                    builder.append(data.get(i).getName() + "\n" );
                    //builder.append(data.get(i).getAverageRating() + "\n" );
                }
                Log.w("Output", builder.toString());
                textViewResult.setText(builder.toString());


                // textViewResult.setText(builder.toString());
                //textViewResult.setText(data.get(0).getName());
                //Log.w("Output", data.get(0).getName());
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }
}
