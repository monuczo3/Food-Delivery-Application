package com.monu.threemeals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.monu.threemeals.Adapter.Restaurant_menu_adapter;
import com.monu.threemeals.data.Restaurant_menu_data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant_Menu extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Restaurant_menu_adapter adapter;
    List<Restaurant_menu_data> list = new ArrayList<>();
    List<Restaurant_menu_data> list2 = new ArrayList<>();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__menu);
        toolbar = findViewById(R.id.toolbar_resaurant_menu);
        recyclerView = findViewById(R.id.recylclerview_restaurant_menu);
        button = findViewById(R.id.btn_proceed_to_cart2);
        adapter = new Restaurant_menu_adapter(list, list2);

        Intent intent = getIntent();
        String id = intent.getStringExtra("restaurant_id");
        String name = intent.getStringExtra("restaurant_name");

        setuptoolbar(name);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        gettingData(id,progressDialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant_Menu.this, successPage.class);
                intent.putExtra("restaurant_name",name);
                startActivity(intent);
                Toast.makeText(Restaurant_Menu.this, "Your Order is Successfully Placed!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void gettingData(String id, ProgressDialog progressDialog) {
        String url = "http://13.235.250.119/v2/restaurants/fetch_result/"+id;
        Log.e("Url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject data = object.getJSONObject("data");
                    JSONArray dataarray = data.getJSONArray("data");

                    for(int i = 0 ; i< dataarray.length(); i++){
                        String sno = String.valueOf(i+1);
                        JSONObject o = dataarray.getJSONObject(i);
                        Log.e("data",o.toString());
                        list.add(new Restaurant_menu_data(sno,
                                o.getString("id"),
                                o.getString("name"),
                                o.getString("cost_for_one"),
                                o.getString("restaurant_id")
                        ));

                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Restaurant_Menu.this, "Error Occured!", Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("token", "a22ec6b2ab5980");
                header.put("Content-Type", "application/json");
                return header;
            }
        };

        requestQueue.add(request);
    }


    private void setuptoolbar(String name) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}