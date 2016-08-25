package com.example.ada.tucanocaffe;

/**
 * Created by ada on 8/14/16.
 */
public class Coffee {
    private String name;
    private int price;
    private String description;
    private int imageResourceId;

    //array of drinks
    public static final Coffee[] typesCoffee = {
        new Coffee("Latte", 25, "A couple of expresso shots with steamed milk", R.drawable.latte),
        new Coffee("Cappuccino", 30, "Espresso, hot milk and steamed milk foam", R.drawable.cappucino2),
        new Coffee("Espresso", 35, "Highest quality beans roasted and brewed fresh", R.drawable.espresso),
        new Coffee("Machiatto", 40, "Literally means \"stained milk\". The name comes from the procedure through which the milk is \"stained\" by the addition of espresso", R.drawable.machiatto),

    };

    //each drink has a name, description, and an image
    private Coffee(String name, int price, String description, int imageResourceId){
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;

    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public String getDescription(){
        return description;
    }

    public  int getImageResourceId(){
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}


