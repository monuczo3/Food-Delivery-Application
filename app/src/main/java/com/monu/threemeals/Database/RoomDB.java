package com.monu.threemeals.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.monu.threemeals.data.Restaurant_menu_data;

@Database(entities = {Restaurant_menu_data.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME ="database";


   /* public synchronized static RoomDB getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }*/
    public abstract MainDao mainDao();
}
