package com.monu.threemeals.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.monu.threemeals.data.Restaurant_menu_data;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(Restaurant_menu_data restaurant_menu_data);
    @Delete
    void delete(Restaurant_menu_data restaurant_menu_data);
    @Query("select * from Restaurant_data")
    List<Restaurant_menu_data> getAll();
}
