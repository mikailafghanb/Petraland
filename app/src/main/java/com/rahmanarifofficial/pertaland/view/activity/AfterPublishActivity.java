package com.rahmanarifofficial.pertaland.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.rahmanarifofficial.pertaland.R;

public class AfterPublishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_publish);
    }

    public void wellDone(View view) {
        finish();
    }
}
