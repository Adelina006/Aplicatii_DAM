package com.example.model_clubsportiv;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Vizualizeaza_inregistrari extends AppCompatActivity {
    public static final String edit_club ="edit_club";
    public static final int REQUEST_CODE_EDIT_INREGISTRARE = 3;
    public static ArrayList<ClubSportiv> cluburi;
    public CustomAdapter customAdapter;
    int poz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizeaza_inregistrari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        cluburi = (ArrayList<ClubSportiv>) intent.getSerializableExtra(Proba_Practica.afisare_club);
        customAdapter = new CustomAdapter(getApplicationContext(), R.layout.item_club, getLayoutInflater(), cluburi){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ClubSportiv club = cluburi.get(position);
                TextView tvImiplace = view.findViewById(R.id.textViewImiPlace);
                if(club.isImiPlace())
                {
                    tvImiplace.setTextColor(Color.GREEN);
                }
                else
                {
                    tvImiplace.setTextColor(Color.RED);
                }
                return view;
            }
        };
        ListView listViewCluburi = findViewById(R.id.listViewCluburi);
        listViewCluburi.setAdapter(customAdapter);

        listViewCluburi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(Vizualizeaza_inregistrari.this)
                        .setTitle("Stergere")
                        .setMessage("Esti sigur ca vrei sa stergi acest club?")
                        .setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ClubSportiv club = cluburi.get(position);
                                cluburi.remove(club);
                                customAdapter.notifyDataSetChanged();
                            }
                        })
                        .create();
                dialog.show();

            }
        });

        listViewCluburi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClubSportiv club = cluburi.get(position);
                Toast.makeText(Vizualizeaza_inregistrari.this, club.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Adauga_inregistrare.class);
                intent.putExtra(edit_club, club);
                startActivityForResult(intent, REQUEST_CODE_EDIT_INREGISTRARE);
                poz = position;
                return true;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EDIT_INREGISTRARE && resultCode == RESULT_OK && data != null)
        {
            ClubSportiv club = (ClubSportiv) data.getSerializableExtra(Adauga_inregistrare.ADD_club);
            if(club != null)
            {
                cluburi.set(poz, club);
               customAdapter.notifyDataSetChanged();
            }
        }
    }
}