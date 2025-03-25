package com.example.cv_builder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class References extends AppCompatActivity {

    EditText etRefName, etRefContact, etRefOrg;
    Button btnAddRef, btnSaveRef;
    ArrayList<String> refList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        etRefName = findViewById(R.id.etRefName);
        etRefContact = findViewById(R.id.etRefContact);
        etRefOrg = findViewById(R.id.etRefOrg);
        btnAddRef = findViewById(R.id.btnAddRef);
        btnSaveRef = findViewById(R.id.btnSaveRef);

        btnAddRef.setOnClickListener(v -> {
            String entry = collectReference();
            if (entry != null) {
                refList.add(entry);
                Toast.makeText(this, "Reference Added", Toast.LENGTH_SHORT).show();
                clearInputs();
            }
        });

        btnSaveRef.setOnClickListener(v -> {
            if (refList.isEmpty()) {
                String entry = collectReference();
                if (entry != null) {
                    refList.add(entry);
                } else {
                    return;
                }
            }

            String allRefs = android.text.TextUtils.join("\n\n", refList);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("references", allRefs);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }

    private String collectReference() {
        String name = etRefName.getText().toString().trim();
        String contact = etRefContact.getText().toString().trim();
        String org = etRefOrg.getText().toString().trim();

        if (name.isEmpty() || contact.isEmpty() || org.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return null;
        }

        return name + " | " + contact + "\n" + org;
    }

    private void clearInputs() {
        etRefName.setText("");
        etRefContact.setText("");
        etRefOrg.setText("");
    }
}
