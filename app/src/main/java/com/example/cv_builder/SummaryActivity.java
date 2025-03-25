package com.example.cv_builder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SummaryActivity extends AppCompatActivity {

    EditText etSummary;
    AppCompatButton btnSaveSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        etSummary = findViewById(R.id.etSummary);
        btnSaveSummary = findViewById(R.id.btnSaveSummary);

        btnSaveSummary.setOnClickListener(v -> {
            String summaryText = etSummary.getText().toString().trim();

            if (summaryText.isEmpty()) {
                Toast.makeText(this, "Please write a summary.", Toast.LENGTH_SHORT).show();
            } else {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("summary", summaryText);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
