package com.monu.threemeals.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.threemeals.Holder.All_restaurants_holder;
import com.monu.threemeals.R;
import com.monu.threemeals.Restaurant_Menu;
import com.monu.threemeals.data.Restaurants_data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class All_restaurants_adapter extends RecyclerView.Adapter<All_restaurants_holder> {

    List<Restaurants_data> list = new ArrayList<>();
    Context context;

    public All_restaurants_adapter(List<Restaurants_data> list){
        this.list = list;
    }

    @NonNull
    @Override
    public All_restaurants_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.allrestaurents_item,parent,false);
        All_restaurants_holder holder = new All_restaurants_holder(newView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull All_restaurants_holder holder, int position) {

        holder.name.setText(list.get(position).name);
        holder.price_per_person.setText(list.get(position).cost_for_one);
        holder.rating.setText(list.get(position).rating);
        Picasso.get().load(list.get(position).image_url).error(R.drawable.default_retaurant_image).into(holder.image);
        final Boolean[] clickme = {true};

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Restaurant_Menu.class);
                intent.putExtra("restaurant_id",list.get(position).id);
                intent.putExtra("restaurant_name",list.get(position).name);
                v.getContext().startActivity(intent);
            }
        });
        ImageButton button = holder.imageButton;

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(clickme[0] == true){

                    holder.imageButton.setImageResource(R.drawable.ic_hearts2);
                    clickme[0] = false;
                }
                else{

                    holder.imageButton.setImageResource(R.drawable.ic_hearts);
                    clickme[0] = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
