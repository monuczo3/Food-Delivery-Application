package com.monu.threemeals.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.threemeals.R;

import org.w3c.dom.Text;

public class cart_Page_holder extends RecyclerView.ViewHolder {
    public TextView dish_name;
    public TextView dish_price;
    View view;
    public cart_Page_holder(@NonNull View itemView) {
        super(itemView);
        dish_name = itemView.findViewById(R.id.dish_name_cartpage);
        dish_price = itemView.findViewById(R.id.dish_price_cartPage);
        view = itemView;
    }
}
