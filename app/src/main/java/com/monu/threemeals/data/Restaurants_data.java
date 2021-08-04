package com.monu.threemeals.data;

public class Restaurants_data {

    public String id;
    public String name;
    public String rating;
    public String cost_for_one;
    public String image_url;
    public Restaurants_data(String id, String name, String rating, String cost_for_one, String image_url){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.cost_for_one = "Rs "+cost_for_one+"/person";
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getCost_for_one() {
        return cost_for_one;
    }

    public String getImage_url() {
        return image_url;
    }

}
