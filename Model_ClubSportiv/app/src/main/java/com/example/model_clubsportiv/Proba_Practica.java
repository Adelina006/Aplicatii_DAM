package com.example.model_clubsportiv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;

public class Proba_Practica extends AppCompatActivity {

    public static ArrayList<ClubSportiv> cluburi = new ArrayList<>();

    public static final int REQUEST_CODE_ADAUGA_INREGISTRARE = 1;
    public static final String afisare_club = "afisare_club";
    public static final int REQUEST_CODE_AFISARE_INREGISTRARE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proba_practica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewDataCurenta = findViewById(R.id.textViewDataCurenta);
        textViewDataCurenta.setText(String.valueOf(new Date()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.optiune1)
        {
            Intent intent = new Intent(getApplicationContext(), Adauga_inregistrare.class);
            startActivityForResult(intent, REQUEST_CODE_ADAUGA_INREGISTRARE);
            return true;
        }
        else if(item.getItemId()==R.id.optiune2)
        {
            Intent intent = new Intent(getApplicationContext(), Vizualizeaza_inregistrari.class);
            intent.putExtra(afisare_club, cluburi);
            startActivityForResult(intent, REQUEST_CODE_AFISARE_INREGISTRARE);
            return true;
        }
        else if( item.getItemId() == R.id.optiune3)
        {
            finishAffinity();
            return true;
        }


        return false;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADAUGA_INREGISTRARE && resultCode == RESULT_OK && data != null)
        {
            ClubSportiv club = (ClubSportiv) data.getSerializableExtra(Adauga_inregistrare.ADD_club);
            if(club != null)
            {
                cluburi.add(club);
            }
        }

    }
}