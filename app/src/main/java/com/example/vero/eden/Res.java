package com.example.vero.eden;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Res extends AppCompatActivity {
    private TextView resultText;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        String overallM = getIntent().getStringExtra(MainActivity.CO2_KEY);
        resultText = (TextView) findViewById(R.id.amt);
        resultText.setText(overallM);
    }

    public void cameraactivity(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void splashscreen(View view) {
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
    }

    public void mainactivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void graphactivity(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
}
