package com.example.model_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Proba_practica_NumeleMeu extends AppCompatActivity {

public static final int REQUEST_CODE_ADD = 1;
public static final String ADD_restaurante = "add_restaurante";

public static final int REQUEST_CODE_LISTA = 2;
public static ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proba_practica_numele_meu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button butonAdauga = findViewById(R.id.buttonAdaugaInregistrare);
        butonAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdauga = new Intent(getApplicationContext(), Adauga_inregistrare.class);
                startActivityForResult(intentAdauga, REQUEST_CODE_ADD);

            }
        });

        Button butonAfiseaza = findViewById(R.id.buttonAfiseazaInregistrari);
        butonAfiseaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLista = new Intent(getApplicationContext(), Lista_inregistrari.class);
                intentLista.putExtra(ADD_restaurante, restaurants);
                startActivityForResult(intentLista, REQUEST_CODE_LISTA);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE_ADD == requestCode && RESULT_OK == resultCode && data != null)
        {
            Restaurant restaurant = (Restaurant) data.getSerializableExtra(Adauga_inregistrare.ADD_restaurant);
            if(restaurant != null)
            {
                restaurants.add(restaurant);
            }
        }

    }
}