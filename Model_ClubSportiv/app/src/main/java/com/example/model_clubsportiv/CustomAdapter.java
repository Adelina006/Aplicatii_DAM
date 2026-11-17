package com.example.model_clubsportiv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ClubSportiv> {

    private Context context;
    private LayoutInflater layoutInflater;
    private int resource ;
    private ArrayList<ClubSportiv> cluburi;

    public CustomAdapter(@NonNull Context context, int resource, LayoutInflater layoutInflater, ArrayList<ClubSportiv> cluburi) {
        super(context, resource, cluburi);
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.resource = resource;
        this.cluburi = cluburi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        ClubSportiv club = cluburi.get(position);

        if(club != null)
        {
            TextView textViewData = view.findViewById(R.id.textViewData);
            textViewData.setText(String.valueOf(club.getDataCreare()));
            TextView textViewSport = view.findViewById(R.id.textViewSport);
            textViewSport.setText(club.getSport());
            TextView textViewCosta = view.findViewById(R.id.textViewCosta);
            if(club.isCosta())
            {
                textViewCosta.setText("Da");
            }
            else
            {
                textViewCosta.setText("Nu");
            }
            TextView textViewImiPlace = view.findViewById(R.id.textViewImiPlace);
            if(club.isImiPlace())
            {
                textViewImiPlace.setText("Da");
            }
            else
            {
                textViewImiPlace.setText("Nu");
            }
            TextView textViewNrLocuri = view.findViewById(R.id.textViewNrLocuri);
            textViewNrLocuri.setText(String.valueOf(club.getNrLocuri()));
        }

        return view;
    }
}
