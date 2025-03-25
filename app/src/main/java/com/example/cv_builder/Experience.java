package com.example.cv_builder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Experience extends AppCompatActivity {

    EditText etRole, etCompany;
    Spinner spinnerStartYear, spinnerEndYear;
    Button btnAddExperience, btnSaveExperience;

    ArrayList<String> experienceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        // Initialize views
        etRole = findViewById(R.id.etRole);
        etCompany = findViewById(R.id.etCompany);
        spinnerStartYear = findViewById(R.id.spinnerStartYear);
        spinnerEndYear = findViewById(R.id.spinnerEndYear);
        btnAddExperience = findViewById(R.id.btnAddExperience);
        btnSaveExperience = findViewById(R.id.btnSaveExperience);

        // Populate year spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int year = 2000; year <= 2025; year++) {
            adapter.add(String.valueOf(year));
        }
        spinnerStartYear.setAdapter(adapter);
        spinnerEndYear.setAdapter(adapter);

        // Add Experience Button
        btnAddExperience.setOnClickListener(v -> {
            String entry = collectExperienceEntry();
            if (entry != null) {
                experienceList.add(entry);
                Toast.makeText(this, "Experience Added", Toast.LENGTH_SHORT).show();
                clearInputs();
            }
        });

        // Save Button
        btnSaveExperience.setOnClickListener(v -> {
            if (experienceList.isEmpty()) {
                String singleEntry = collectExperienceEntry();
                if (singleEntry != null) {
                    experienceList.add(singleEntry);
                } else {
                    return;
                }
            }

            String allExperience = android.text.TextUtils.join("\n\n", experienceList);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("experience", allExperience);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }

    private String collectExperienceEntry() {
        String role = etRole.getText().toString().trim();
        String company = etCompany.getText().toString().trim();
        String startYear = spinnerStartYear.getSelectedItem().toString();
        String endYear = spinnerEndYear.getSelectedItem().toString();

        if (role.isEmpty() || company.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return null;
        }

        return role + " at " + company + "\n" + startYear + " - " + endYear;
    }

    private void clearInputs() {
        etRole.setText("");
        etCompany.setText("");
        spinnerStartYear.setSelection(0);
        spinnerEndYear.setSelection(0);
    }
}
