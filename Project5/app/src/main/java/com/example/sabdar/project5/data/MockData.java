package com.example.sabdar.project5.data;

import android.content.Context;

import com.example.sabdar.project5.R;
import com.example.sabdar.project5.pojo.Location;

import java.util.ArrayList;

public class MockData {
    private Context mContext;

    public MockData(Context context) {
        mContext = context;
    }

    public ArrayList<Location> getPlaces() {
        String[] title = mContext.getResources().getStringArray(R.array.places_title);
        String[] subTitle = mContext.getResources().getStringArray(R.array.places_sub_title);
        String[] description = mContext.getResources().getStringArray(R.array.places_description);
        String[] address = mContext.getResources().getStringArray(R.array.places_address);
        String[] phone = mContext.getResources().getStringArray(R.array.places_phone);

        ArrayList<Location> places = new ArrayList<>();

        places.add(new Location(title[0], subTitle[0], 4.4, description[0], address[0], phone[0], R.drawable.p_101));
        places.add(new Location(title[1], subTitle[1], 4.4, description[1], address[1], phone[1], R.drawable.p_102));
        places.add(new Location(title[2], subTitle[2], 4.4, description[2], address[2], phone[2], R.drawable.p_104));
        places.add(new Location(title[3], subTitle[3], 4.4, description[3], address[3], phone[3], R.drawable.p_105));
        places.add(new Location(title[4], subTitle[4], 4.6, description[4], address[4], phone[4], R.drawable.p_103));

        return places;
    }

    public ArrayList<Location> getHotels() {
        String[] title = mContext.getResources().getStringArray(R.array.hotels_title);
        String[] subTitle = mContext.getResources().getStringArray(R.array.hotels_sub_title);
        String[] description = mContext.getResources().getStringArray(R.array.hotels_description);
        String[] address = mContext.getResources().getStringArray(R.array.hotels_address);
        String[] phone = mContext.getResources().getStringArray(R.array.hotels_phone);

        ArrayList<Location> hotels = new ArrayList<>();

        hotels.add(new Location(title[0], subTitle[0], 4.2, description[0], address[0], phone[0], R.drawable.h_111));
        hotels.add(new Location(title[1], subTitle[1], 4.3, description[1], address[1], phone[1], R.drawable.h_112));
        hotels.add(new Location(title[2], subTitle[2], 3.8, description[2], address[2], phone[2], R.drawable.h_113));
        hotels.add(new Location(title[3], subTitle[3], 4.1, description[3], address[3], phone[3], R.drawable.h_114));
        hotels.add(new Location(title[4], subTitle[4], 4.3, description[4], address[4], phone[4], R.drawable.h_115));

        return hotels;
    }

    public ArrayList<Location> getRestaurants() {

        String[] title = mContext.getResources().getStringArray(R.array.restaurants_title);
        String[] subTitle = mContext.getResources().getStringArray(R.array.restaurants_sub_title);
        String[] description = mContext.getResources().getStringArray(R.array.restaurants_description);
        String[] address = mContext.getResources().getStringArray(R.array.restaurants_address);
        String[] phone = mContext.getResources().getStringArray(R.array.restaurants_phone);

        ArrayList<Location> restaurants = new ArrayList<>();

        restaurants.add(new Location(title[0], subTitle[0], 4.0, description[0], address[0], phone[0], R.drawable.r_121));
        restaurants.add(new Location(title[1], subTitle[1], 4.0, description[1], address[1], phone[1], R.drawable.r_122));
        restaurants.add(new Location(title[2], subTitle[2], 4.5, description[2], address[2], phone[2], R.drawable.r_123));
        restaurants.add(new Location(title[3], subTitle[3], 4.2, description[3], address[3], phone[3], R.drawable.r_124));
        restaurants.add(new Location(title[4], subTitle[4], 4.5, description[4], address[4], phone[4], R.drawable.r_125));

        return restaurants;
    }

    public ArrayList<Location> getShopping() {
        String[] title = mContext.getResources().getStringArray(R.array.shopping_title);
        String[] subTitle = mContext.getResources().getStringArray(R.array.shopping_sub_title);
        String[] description = mContext.getResources().getStringArray(R.array.shopping_description);
        String[] address = mContext.getResources().getStringArray(R.array.shopping_address);
        String[] phone = mContext.getResources().getStringArray(R.array.shopping_phone);

        ArrayList<Location> shopping = new ArrayList<>();
        shopping.add(new Location(title[0], subTitle[0], 4.4, description[0], address[0], phone[0]));
        shopping.add(new Location(title[1], subTitle[1], 3.9, description[1], address[1], phone[1]));
        shopping.add(new Location(title[2], subTitle[2], 3.8, description[2], address[2], phone[2]));
        shopping.add(new Location(title[3], subTitle[3], 3.7, description[3], address[3], phone[3]));
        shopping.add(new Location(title[4], subTitle[4], 3.9, description[4], address[4], phone[4]));
        return shopping;
    }
}
