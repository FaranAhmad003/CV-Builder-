package com.example.cv_builder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Certifications extends AppCompatActivity {

    EditText etCertificate, etOrg;
    Spinner spinnerCertYear;
    Button btnAddCert, btnSaveCert;
    ArrayList<String> certList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        etCertificate = findViewById(R.id.etCertificate);
        etOrg = findViewById(R.id.etOrg);
        spinnerCertYear = findViewById(R.id.spinnerCertYear);
        btnAddCert = findViewById(R.id.btnAddCert);
        btnSaveCert = findViewById(R.id.btnSaveCert);

        // Populate Year Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int year = 2000; year <= 2025; year++) {
            adapter.add(String.valueOf(year));
        }
        spinnerCertYear.setAdapter(adapter);

        // Add button logic
        btnAddCert.setOnClickListener(v -> {
            String cert = collectCertEntry();
            if (cert != null) {
                certList.add(cert);
                Toast.makeText(this, "Certification Added", Toast.LENGTH_SHORT).show();
                clearInputs();
            }
        });

        // Save button logic
        btnSaveCert.setOnClickListener(v -> {
            if (certList.isEmpty()) {
                String single = collectCertEntry();
                if (single != null) {
                    certList.add(single);
                } else return;
            }

            String allCerts = android.text.TextUtils.join("\n\n", certList);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("certifications", allCerts);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }

    private String collectCertEntry() {
        String title = etCertificate.getText().toString().trim();
        String org = etOrg.getText().toString().trim();
        String year = spinnerCertYear.getSelectedItem().toString();

        if (title.isEmpty() || org.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return null;
        }

        return title + " - " + org + "\nYear: " + year;
    }

    private void clearInputs() {
        etCertificate.setText("");
        etOrg.setText("");
        spinnerCertYear.setSelection(0);
    }
}
