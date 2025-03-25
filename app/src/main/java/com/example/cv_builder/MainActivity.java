package com.example.cv_builder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_REQUEST = 100;
    private static final int PERSONAL_DETAILS_REQUEST = 200;
    private static final int SUMMARY_REQUEST = 300;
    private static final int EDUCATION_REQUEST = 400;
    private static final int EXPERIENCE_REQUEST = 500;
    private static final int CERTIFICATION_REQUEST = 600;
    private static final int REFERENCES_REQUEST = 700;



    private AppCompatButton btnProfile, btnSubmit, btnPersonalDetails, btnSummary, btnEducation, btnExperience, btnCertifications, btnReferences;
    private ImageView imgProfile;
    private Uri selectedImageUri = null;
    private String experience = "";
    private String certifications = "";
    private String references = "";


    // Store all user input data
    private String fullName = "", phone = "", email = "", linkedin = "", github = "", summary = "", education = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfile = findViewById(R.id.btnProfile);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnPersonalDetails = findViewById(R.id.btnPersonalDetails);
        btnSummary = findViewById(R.id.btnSummary);
        btnEducation = findViewById(R.id.btnEducation);
        imgProfile = findViewById(R.id.imgProfile);
        btnExperience = findViewById(R.id.btnExperience);
        btnCertifications = findViewById(R.id.btnCertifications);
        btnReferences = findViewById(R.id.btnReferences);

        // Select profile picture
        btnProfile.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, IMAGE_PICK_REQUEST);
        });

        // Submit and go to CV screen
        btnSubmit.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                Intent intent = new Intent(MainActivity.this, CvScreenActivity.class);
                intent.putExtra("profileUri", selectedImageUri.toString());
                intent.putExtra("fullName", fullName);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("linkedin", linkedin);
                intent.putExtra("github", github);
                intent.putExtra("summary", summary);
                intent.putExtra("education", education);
                intent.putExtra("experience", experience);
                intent.putExtra("certifications", certifications);
                intent.putExtra("references", references);



                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select all the attributes", Toast.LENGTH_SHORT).show();
            }
        });

        // Launch Personal Details
        btnPersonalDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PersonalDetailsActivity.class);
            startActivityForResult(intent, PERSONAL_DETAILS_REQUEST);
        });

        // Launch Summary
        btnSummary.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivityForResult(intent, SUMMARY_REQUEST);
        });

        // Launch Education
        btnEducation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Education.class);
            startActivityForResult(intent, EDUCATION_REQUEST);
        });
        btnExperience.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Experience.class);
            startActivityForResult(intent, EXPERIENCE_REQUEST);
        });
        btnCertifications.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Certifications.class);
            startActivityForResult(intent, CERTIFICATION_REQUEST);
        });
        btnReferences.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, References.class);
            startActivityForResult(intent, REFERENCES_REQUEST);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICK_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imgProfile.setImageURI(selectedImageUri);
            imgProfile.setVisibility(ImageView.VISIBLE);
            Toast.makeText(this, "Image Selected!", Toast.LENGTH_SHORT).show();

        } else if (requestCode == PERSONAL_DETAILS_REQUEST && resultCode == RESULT_OK && data != null) {
            fullName = data.getStringExtra("fullName");
            phone = data.getStringExtra("phone");
            email = data.getStringExtra("email");
            linkedin = data.getStringExtra("linkedin");
            github = data.getStringExtra("github");

            Toast.makeText(this, "Details saved successfully!", Toast.LENGTH_SHORT).show();

        } else if (requestCode == SUMMARY_REQUEST && resultCode == RESULT_OK && data != null) {
            summary = data.getStringExtra("summary");
            Toast.makeText(this, "Summary saved!", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDUCATION_REQUEST && resultCode == RESULT_OK && data != null) {
            education = data.getStringExtra("education");
            Toast.makeText(this, "Education saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EXPERIENCE_REQUEST && resultCode == RESULT_OK && data != null) {
            experience = data.getStringExtra("experience");
            Toast.makeText(this, "Experience saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == CERTIFICATION_REQUEST && resultCode == RESULT_OK && data != null) {
            certifications = data.getStringExtra("certifications");
            Toast.makeText(this, "Certifications saved!", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == REFERENCES_REQUEST && resultCode == RESULT_OK && data != null) {
            references = data.getStringExtra("references");
            Toast.makeText(this, "References saved!", Toast.LENGTH_SHORT).show();
        }



    }
}
