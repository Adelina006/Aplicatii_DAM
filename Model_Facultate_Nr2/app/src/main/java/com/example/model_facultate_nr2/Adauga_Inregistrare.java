package com.example.model_facultate_nr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Adauga_Inregistrare extends AppCompatActivity {

    public static final String ADD_facultate = "Facultate adaugata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_inregistrare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] apecializari = {"Info", "Germana", "Biologie", "Matematica"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, apecializari);
        Spinner spinnerSpecializari = findViewById(R.id.spinnerSpecializari);
        spinnerSpecializari.setAdapter(adapter);

        EditText etNume = findViewById(R.id.editTextNume);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox checkBox = findViewById(R.id.checkBoxAreMaster);
        EditText etNrSpecializari = findViewById(R.id.editTextNrSpecializari);
        EditText etTaxa = findViewById(R.id.editTextTaxa);
        Spinner spinner = findViewById(R.id.spinnerSpecializari);
        Button butonSalveaza= findViewById(R.id.buttonSalveaza);

        Intent intent = getIntent();

        if(intent.hasExtra(Lista_Inregistrari.EDIT_FACULTATE))
        {
            Facultate facultate = (Facultate) intent.getSerializableExtra(Lista_Inregistrari.EDIT_FACULTATE);
            if(facultate != null)
            {
                etNume.setText(facultate.getNume());
                if(facultate.isAreDoctorat())
                {
                    radioGroup.check(R.id.radioButtonAreDoctorat);
                }
                else
                {
                    radioGroup.check(R.id.radioButtonNUAreDoctorat);
                }
                if(facultate.isAreMaster())
                {
                    checkBox.setChecked(true);
                }
                else
                {
                    checkBox.setChecked(false);
                }
                etNrSpecializari.setText(String.valueOf(facultate.getNrSpecializari()));
                etTaxa.setText(String.valueOf(facultate.getTaxa()));
                ArrayAdapter<String> adapter1 = (ArrayAdapter<String>) spinnerSpecializari.getAdapter();
                for(int i = 0; i < adapter1.getCount(); i++)
                {
                    if(facultate.getSpecializare().equals(adapter1.getItem(i)))
                    {
                        spinner.setSelection(i);
                        break;
                    }
                }


            }
        }

        butonSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNume.getText().toString().isEmpty())
                {
                    etNume.setError("Introduceti numele");
                }
                else if(etNrSpecializari.getText().toString().isEmpty())
                {
                    etNrSpecializari.setError("introduceti numarul de specializari");
                }
                else if(Integer.parseInt(etNrSpecializari.getText().toString()) < 0)
                {
                    etNrSpecializari.setError("introduceti un numar valud de specializari ");
                }
                else if(etTaxa.getText().toString().isEmpty())
                {
                    etTaxa.setError("introduceti taxa");

                }
                else
                {
                    String nume = etNume.getText().toString();
                    int id = radioGroup.getCheckedRadioButtonId();
                    boolean areDoctorat = false;
                    if(id == R.id.radioButtonAreDoctorat)
                    {
                        areDoctorat = true;
                    }
                    boolean areMaster = checkBox.isChecked();
                    int nrSpecializari = Integer.parseInt(etNrSpecializari.getText().toString());
                    double taxa = Double.parseDouble(etTaxa.getText().toString());
                    String specializare = spinner.getSelectedItem().toString();

                    Facultate facultate = new Facultate(nume, areDoctorat, areMaster, nrSpecializari, specializare, taxa);
                    intent.putExtra(ADD_facultate, facultate);
                    setResult(RESULT_OK, intent);
                    finish();


                }
            }
        });


    }
}