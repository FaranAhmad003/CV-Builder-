package com.example.cv_builder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class PersonalDetailsActivity extends AppCompatActivity {

    EditText etFullName, etPhoneNumber, etEmail, etLinkedIn, etGitHub;
    AppCompatButton btnSaveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        etFullName = findViewById(R.id.etFullName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);
        etLinkedIn = findViewById(R.id.etLinkedIn);
        etGitHub = findViewById(R.id.etGitHub);
        btnSaveDetails = findViewById(R.id.btnSaveDetails);

        btnSaveDetails.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String linkedin = etLinkedIn.getText().toString().trim();
            String github = etGitHub.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
            } else {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("fullName", name);
                resultIntent.putExtra("phone", phone);
                resultIntent.putExtra("email", email);
                resultIntent.putExtra("linkedin", linkedin);
                resultIntent.putExtra("github", github);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
