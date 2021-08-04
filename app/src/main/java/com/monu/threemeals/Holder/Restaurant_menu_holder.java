package com.monu.threemeals.Holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.threemeals.R;

import org.w3c.dom.Text;

public class Restaurant_menu_holder extends RecyclerView.ViewHolder {
    public TextView serialNo;
    public TextView name;
    public TextView cost_for_one;
    public Button button;
    View view;
    public Restaurant_menu_holder(@NonNull View itemView) {
        super(itemView);
        serialNo = itemView.findViewById(R.id.txt_serial_no);
        name = itemView.findViewById(R.id.dish_name);
        cost_for_one = itemView.findViewById(R.id.dish_cost);
        button = itemView.findViewById(R.id.btn_add);
        view = itemView;
    }
}
