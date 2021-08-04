package com.monu.threemeals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.monu.threemeals.Adapter.Restaurant_menu_adapter;
import com.monu.threemeals.Adapter.cartPage_adapter;
import com.monu.threemeals.data.Restaurant_menu_data;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity {
    Toolbar toolbar;
    Button button;
    TextView textView;

    cartPage_adapter adapter2;
    RecyclerView recyclerView;
    List<Restaurant_menu_data> list = new ArrayList<>();
    List<Restaurant_menu_data> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        toolbar = findViewById(R.id.toolbar_cart_page);
        textView = findViewById(R.id.restaurant_name_cartPage);
        recyclerView = findViewById(R.id.recyclerView_cart_page);
        button = findViewById(R.id.btn_Place_order);

        adapter2 = new cartPage_adapter(list2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter2);
        setuptoolbar();
        Intent intent = getIntent();
        String name = intent.getStringExtra("restaurant_name");
        textView.setText(name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartPage.this, successPage.class);
                startActivity(intent);
            }
        });

    }

    private void setuptoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Cart");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}