package com.monu.threemeals.Holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.threemeals.R;

public class All_restaurants_holder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView price_per_person;
    public TextView rating;
    public ImageView image;
    public ImageButton imageButton;
    View view;

     public All_restaurants_holder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txt_restaurant_name);
        price_per_person = itemView.findViewById(R.id.txt_priceperperson);
        rating = itemView.findViewById(R.id.tv_rating);
        image = itemView.findViewById(R.id.Img_restaurants);
        imageButton = itemView.findViewById(R.id.hearts);
        view = itemView;

    }
}
