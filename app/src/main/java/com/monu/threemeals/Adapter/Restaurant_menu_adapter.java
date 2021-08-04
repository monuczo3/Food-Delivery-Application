package com.monu.threemeals.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.monu.threemeals.Database.RoomDB;
import com.monu.threemeals.Holder.Restaurant_menu_holder;
import com.monu.threemeals.R;
import com.monu.threemeals.Restaurant_Menu;
import com.monu.threemeals.data.Restaurant_menu_data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Restaurant_menu_adapter extends RecyclerView.Adapter<Restaurant_menu_holder> {

    List<Restaurant_menu_data> list = new ArrayList<>();

    private RoomDB database;

    public Restaurant_menu_adapter(List<Restaurant_menu_data> list, List<Restaurant_menu_data> list2)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public Restaurant_menu_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        database = Room.databaseBuilder(context,RoomDB.class,"database")
                .build();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurant_menu_item,parent,false);
        Restaurant_menu_holder holder = new Restaurant_menu_holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Restaurant_menu_holder holder, int position) {

        Restaurant_menu_data restaudata = list.get(position);
        holder.name.setText(list.get(position).name);
        holder.cost_for_one.setText(list.get(position).cost_for_one);
        holder.serialNo.setText(list.get(position).sno);
        final boolean[] clicking = {true};

       holder.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Restaurant_menu_data d = list.get(holder.getAdapterPosition());
               String sno = d.sno;
               String id = d.id;
               String name = d.name;
               String costForOne = d.cost_for_one;
               String restaurantid = d.restaurant_id;

              Button button = v.findViewById(R.id.btn_add);
               if(clicking[0]){
                       //<color name="orange">#FF9800</color>      <color name="statusbarcolor">#FF5722</color>
                 //  Restaurant_menu_data data = new Restaurant_menu_data(sno,id,name,costForOne,restaurantid);
                 // insertion(data);

                   button.setBackgroundColor(Color.parseColor("#FF5722"));
                   clicking[0] = false;
               }else{
                    Restaurant_menu_data di = list.get(holder.getAdapterPosition());

                   // deletion(di);

                   button.setBackgroundColor(Color.parseColor("#FF9800"));
                   clicking[0] = true;
               }
           }
       });
    }

    private void deletion(Restaurant_menu_data di) {
        class deletion extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                database.mainDao().delete(di);
                notifyDataSetChanged();
                return null;
            }
        }
    }

    private void insertion(Restaurant_menu_data data) {
        class insertion extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                database.mainDao().insert(data);
                notifyDataSetChanged();
                return null;
            }
        }
        insertion i = new insertion();
        i.execute();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
