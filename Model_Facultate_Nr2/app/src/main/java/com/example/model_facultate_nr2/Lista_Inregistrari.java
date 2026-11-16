package com.example.model_facultate_nr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Lista_Inregistrari extends AppCompatActivity {

    List<Facultate> listaFacultati = new ArrayList<>();
    ListView listViewFacultati;
    int poz;
    public static final int REQUEST_CODE_ADD_FACULTATE = 1;
    public static final int REQUES_CODE_EDIT_FACULTATE = 2;
    public static final String EDIT_FACULTATE = "Facultate editata";

    ArrayAdapter<Facultate> adapter;

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

        listViewFacultati = findViewById(R.id.ListViewInregistrari);

        listViewFacultati.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poz = position;
                Facultate facultate = listaFacultati.get(poz);
                Intent intent = new Intent(getApplicationContext(), Adauga_Inregistrare.class);
                intent.putExtra(EDIT_FACULTATE, facultate);
                startActivityForResult(intent, REQUES_CODE_EDIT_FACULTATE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1)
        {
            Intent intent = new Intent(getApplicationContext(), Adauga_Inregistrare.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_FACULTATE);

            return true;
        }
        if(item.getItemId() == R.id.item2)
        {
            finishAffinity();
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_FACULTATE && resultCode == RESULT_OK &&data != null)
        {
            Facultate facultate = (Facultate) data.getSerializableExtra(Adauga_Inregistrare.ADD_facultate);
            if(facultate != null)
            {
                listaFacultati.add(facultate);
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listaFacultati);
                listViewFacultati.setAdapter(adapter);
            }
        }
        if(requestCode == REQUES_CODE_EDIT_FACULTATE && resultCode == RESULT_OK && data != null)
        {
            Facultate facultate = (Facultate) data.getSerializableExtra(Adauga_Inregistrare.ADD_facultate);
            if(facultate != null)
            {
                listaFacultati.get(poz).setNume(facultate.getNume());
                listaFacultati.get(poz).setAreDoctorat(facultate.isAreDoctorat());
                listaFacultati.get(poz).setAreMaster(facultate.isAreMaster());
                listaFacultati.get(poz).setNrSpecializari(facultate.getNrSpecializari());
                listaFacultati.get(poz).setSpecializare(facultate.getSpecializare());
                listaFacultati.get(poz).setTaxa(facultate.getTaxa());
               ArrayAdapter<Facultate> adapter = (ArrayAdapter<Facultate>) listViewFacultati.getAdapter();
               adapter.notifyDataSetChanged();

            }


        }
    }
}