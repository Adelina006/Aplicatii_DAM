package com.example.model_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adauga_inregistrare extends AppCompatActivity {

    public static final String ADD_restaurant = "add_restaurant";

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


        String[] vibeuri = {"Meh", "SUper", "Wow"};
        Spinner spinnerVibe = findViewById(R.id.spinnerVibe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, vibeuri);

        spinnerVibe.setAdapter(adapter);
        Intent intent = getIntent();
        EditText editTextNume = findViewById(R.id.editTextNume);
        EditText editTextNrLocuri = findViewById(R.id.editTextNrLocuri);
        EditText editTextData = findViewById(R.id.editTextData);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox checkBoxImiplace = findViewById(R.id.checkBoxImiplace);
        Button adauga = findViewById(R.id.buttonAdaugare);

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextNume.getText().toString().isEmpty())
                {
                    editTextNume.setError("Introduceti numele");
                }
                else if(editTextNrLocuri.getText().toString().isEmpty())
                {
                    editTextNrLocuri.setError("Introduceti nr de locuri");
                }
                else if(Integer.parseInt(editTextNrLocuri.getText().toString()) < 0)
                {
                    editTextNrLocuri.setError("Nr de locuri trebuie sa fie pozitiv");
                }
                else if(editTextData.getText().toString().isEmpty())
                {
                    editTextData.setError("Introduceti data");
                }
                else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    String data = editTextData.getText().toString();
                    try {
                        Date date = sdf.parse(data);
                        String nume = editTextNume.getText().toString();
                        int nrLocuri = Integer.parseInt(editTextNrLocuri.getText().toString());
                        boolean rezervat;
                        if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonEsteRezervat)
                        {
                            rezervat = true;
                        }
                        else
                        {
                            rezervat = false;
                        }
                        String vibe = spinnerVibe.getSelectedItem().toString();
                        boolean imiPlace = checkBoxImiplace.isChecked();
                        Restaurant restaurant = new Restaurant(nume, nrLocuri, rezervat, vibe, imiPlace, date);

                        intent.putExtra(ADD_restaurant, restaurant);
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });


    }


}