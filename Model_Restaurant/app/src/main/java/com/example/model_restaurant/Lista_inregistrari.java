package com.example.model_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Lista_inregistrari extends AppCompatActivity {
    ListView listViewInregistrari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_inregistrari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) intent.getSerializableExtra(Proba_practica_NumeleMeu.ADD_restaurante);
        Custom_Adapter adapter = new Custom_Adapter(getApplicationContext(), R.layout.item_restaurant, restaurants, getLayoutInflater());
         listViewInregistrari = findViewById(R.id.listViewInregistrari);
        listViewInregistrari.setAdapter(adapter);


        listViewInregistrari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = restaurants.get(position);
                Toast.makeText(getApplicationContext(), restaurant.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}