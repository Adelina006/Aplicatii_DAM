package com.example.model_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Custom_Adapter extends ArrayAdapter<Restaurant> {
    private Context context;
    private ArrayList<Restaurant> restaurants;
    private LayoutInflater inflater;
    private int resource;

    public Custom_Adapter(@NonNull Context context, int resource, ArrayList<Restaurant> restaurants, LayoutInflater inflater) {
        super(context, resource, restaurants);
        this.context = context;
        this.restaurants = restaurants;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        Restaurant restaurant = restaurants.get(position);
        TextView textViewNume = view.findViewById(R.id.textViewNume);
        TextView textViewData = view.findViewById(R.id.textViewData);
        textViewNume.setText(restaurant.getNume());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        textViewData.setText(sdf.format(restaurant.getData()));

        return view ;
    }
}
