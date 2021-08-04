package com.monu.threemeals.Adapter;

import com.monu.threemeals.Database.RoomDB;
import com.monu.threemeals.data.Restaurant_menu_data;

import java.util.List;

public class getAllAsyncTask extends android.os.AsyncTask<Void,Void, List<Restaurant_menu_data>> {
    private RoomDB database;
    List<Restaurant_menu_data> list;
    public getAllAsyncTask(RoomDB database) {
        this.database = database;
    }

    @Override
    protected List<Restaurant_menu_data> doInBackground(Void... voids) {
        return database.mainDao().getAll();
    }
}
