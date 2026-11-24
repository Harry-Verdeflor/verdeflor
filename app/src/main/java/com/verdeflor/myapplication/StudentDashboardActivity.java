package com.verdeflor.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Redirect to activity_student_page
        Intent intent = new Intent(this, activity_student_page.class);
        startActivity(intent);
        finish();
    }
}

