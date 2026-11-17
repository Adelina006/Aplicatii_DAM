package com.example.model_clubsportiv;

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
import java.util.Locale;

public class Adauga_inregistrare extends AppCompatActivity {
    public static final String ADD_club = "add_club";

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
        Intent intent = getIntent();

        String[] sporturi = {"Inot", "tenis", "Fotbal"};
        Spinner spinnerSport = findViewById(R.id.spinnerSport);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sporturi);
        spinnerSport.setAdapter(adapterSpinner);
        EditText etData = findViewById(R.id.editTextData);
        EditText etNrLocuri = findViewById(R.id.editTextNrLocuri);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        CheckBox checkBox = findViewById(R.id.checkBoxImiPlace);
        Button butonAdauga = findViewById(R.id.buttonAdauga);

        if(intent.hasExtra(Vizualizeaza_inregistrari.edit_club))
        {
            ClubSportiv club = (ClubSportiv) intent.getSerializableExtra(Vizualizeaza_inregistrari.edit_club);
            if(club != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                etData.setText(sdf.format(club.getDataCreare()));
                etNrLocuri.setText(String.valueOf(club.getNrLocuri()));
                if(club.isCosta())
                {
                    radioGroup.check(R.id.radioButtonCosta);
                }
                else
                {
                    radioGroup.check(R.id.radioButtonNuCosta);
                }
                if(club.isImiPlace())
                {
                    checkBox.setChecked(true);
                }
                else
                {
                    checkBox.setChecked(false);
                }
                spinnerSport.setSelection(adapterSpinner.getPosition(club.getSport()));
            }
        }

        butonAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etData.getText().toString().isEmpty()) {
                    etData.setError("Introduceti data");
                }
                else if(etNrLocuri.getText().toString().isEmpty()) {
                    etNrLocuri.setError("Introduceti nr de locuri");
                }
                else if(Integer.parseInt(etNrLocuri.getText().toString()) < 0) {
                    etNrLocuri.setError("Nr de locuri trebuie sa fie pozitiv");
                }
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    try {
                        Date data = sdf.parse(etData.getText().toString());
                        String sport = spinnerSport.getSelectedItem().toString();
                        int nrLocuri = Integer.parseInt(etNrLocuri.getText().toString());
                        boolean costa = false;
                        if(radioGroup.getCheckedRadioButtonId() == R.id.radioButtonCosta) {
                            costa = true;
                        }
                        boolean imiPlace = false;
                        if(checkBox.isChecked())
                        {
                            imiPlace = true;
                        }
                        ClubSportiv clubSportiv = new ClubSportiv(data, sport, costa, imiPlace, nrLocuri);
                        intent.putExtra(ADD_club, clubSportiv);
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