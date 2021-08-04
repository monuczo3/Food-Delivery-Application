package com.monu.threemeals.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Restaurant_data")
public class Restaurant_menu_data {
    public String sno;
    @PrimaryKey @NonNull
   public  String id;
    @ColumnInfo(name = "name")
   public  String name;
    @ColumnInfo(name = "costForOne")
   public  String cost_for_one;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost_for_one() {
        return cost_for_one;
    }

    public void setCost_for_one(String cost_for_one) {
        this.cost_for_one = cost_for_one;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public  String restaurant_id;

    public Restaurant_menu_data(String sno, String id, String name, String cost_for_one, String restaurant_id){
        this.sno = sno;
        this.id = id;
        this.name = name;
        this.cost_for_one = "Rs " + cost_for_one +"/person";
        this.restaurant_id = restaurant_id;
    }
}
