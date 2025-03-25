package com.example.cv_builder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Education extends AppCompatActivity {

    RadioGroup radioGroupEducationLevel;
    Spinner spinnerYear;
    EditText etInstitution;
    Button btnAddEducation, btnSaveEducation;

    ArrayList<String> educationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        radioGroupEducationLevel = findViewById(R.id.radioGroupEducationLevel);
        spinnerYear = findViewById(R.id.spinnerYear);
        etInstitution = findViewById(R.id.etInstitution);
        btnAddEducation = findViewById(R.id.btnAddEducation);
        btnSaveEducation = findViewById(R.id.btnSaveEducation);

        // Populate Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int year = 2000; year <= 2025; year++) {
            adapter.add(String.valueOf(year));
        }
        spinnerYear.setAdapter(adapter);

        btnAddEducation.setOnClickListener(v -> {
            String entry = collectEducationEntry();
            if (entry != null) {
                educationList.add(entry);
                Toast.makeText(this, "Added: " + entry.split("\n")[0], Toast.LENGTH_SHORT).show();
                etInstitution.setText("");
                radioGroupEducationLevel.clearCheck();
            }
        });

        btnSaveEducation.setOnClickListener(v -> {
            if (educationList.isEmpty()) {
                String singleEntry = collectEducationEntry();
                if (singleEntry != null) {
                    educationList.add(singleEntry);
                } else {
                    return;
                }
            }
            String allEducation = android.text.TextUtils.join("\n\n", educationList);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("education", allEducation);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }

    private String collectEducationEntry() {
        int selectedId = radioGroupEducationLevel.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select education level", Toast.LENGTH_SHORT).show();
            return null;
        }

        RadioButton selectedRadio = findViewById(selectedId);
        String level = selectedRadio.getText().toString();
        String year = spinnerYear.getSelectedItem().toString();
        String institution = etInstitution.getText().toString().trim();

        if (institution.isEmpty()) {
            Toast.makeText(this, "Please enter institution name", Toast.LENGTH_SHORT).show();
            return null;
        }

        return level + " - " + year + "\n" + institution;
    }
}
