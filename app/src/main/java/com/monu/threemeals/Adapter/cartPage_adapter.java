package com.monu.threemeals.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.threemeals.Database.RoomDB;
import com.monu.threemeals.Holder.Restaurant_menu_holder;
import com.monu.threemeals.Holder.cart_Page_holder;
import com.monu.threemeals.R;
import com.monu.threemeals.data.Restaurant_menu_data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class cartPage_adapter extends RecyclerView.Adapter<cart_Page_holder> {

    List<Restaurant_menu_data> list = new ArrayList<>();
    Context context;

    public cartPage_adapter(List<Restaurant_menu_data> list){
        this.list = list;
    }

    @NonNull
    @Override
    public cart_Page_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_page_item,parent,false);
        cart_Page_holder holder = new cart_Page_holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull cart_Page_holder holder, int position) {
        gettingData();

        holder.dish_name.setText(list.get(position).name);
        holder.dish_price.setText(list.get(position).cost_for_one);

    }

    private void gettingData() {
        class gettingData extends AsyncTask<Void,Void,List<Restaurant_menu_data>>{

            @Override
            protected List<Restaurant_menu_data> doInBackground(Void... voids) {
             //   list.addAll(database.mainDao().getAll());
                return null;
            }
        }
        gettingData gd = new gettingData();

    }

   /* private List<Restaurant_menu_data> getalldata() throws ExecutionException, InterruptedException {
        RoomDB database = RoomDB.getInstance(context);
        return new getAllAsyncTask(database).execute().get();
    } */

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
