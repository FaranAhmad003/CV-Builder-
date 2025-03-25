package com.example.cv_builder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CvScreenActivity extends AppCompatActivity {

    private ImageView cvProfile;
    private TextView tvName, tvPhone, tvEmail, tvLinkedIn, tvGitHub,
            tvSummary, tvEducation, tvExperience,tvCertifications ,tvReferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_screen);

        // Initialize all views
        cvProfile = findViewById(R.id.cvProfileImage);
        tvName = findViewById(R.id.tvFullName);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvLinkedIn = findViewById(R.id.tvLinkedIn);
        tvGitHub = findViewById(R.id.tvGitHub);
        tvSummary = findViewById(R.id.tvSummary);
        tvEducation = findViewById(R.id.tvEducation);
        tvExperience = findViewById(R.id.tvExperience);
        tvCertifications = findViewById(R.id.tvCertifications);
        tvReferences = findViewById(R.id.tvReferences);



        // Get data from intent
        Intent intent = getIntent();
        String uriString = intent.getStringExtra("profileUri");
        if (uriString != null) {
            Uri imageUri = Uri.parse(uriString);
            cvProfile.setImageURI(imageUri);
        }

        // Set data to views
        tvName.setText(intent.getStringExtra("fullName"));
        tvPhone.setText(intent.getStringExtra("phone"));
        tvEmail.setText(intent.getStringExtra("email"));
        tvLinkedIn.setText(intent.getStringExtra("linkedin"));
        tvGitHub.setText(intent.getStringExtra("github"));
        tvSummary.setText(intent.getStringExtra("summary"));
        tvEducation.setText(intent.getStringExtra("education"));
        tvExperience.setText(intent.getStringExtra("experience"));
        tvCertifications.setText(intent.getStringExtra("certifications"));
        tvReferences.setText(intent.getStringExtra("references"));


    }
}
