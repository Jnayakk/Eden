package com.example.vero.eden;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        String string = "CO\u2082";
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("Carbon Dioxide Emissions Weekly");
        graph.setTitleTextSize(60);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 187.2),
                new DataPoint(1, 165.4),
                new DataPoint(2, 175.2),
                new DataPoint(3, 150),
                new DataPoint(4, 148.5)
        });
        graph.addSeries(series);
    }

    public void cameraactivity(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void splashscreen(View view) {
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
    }

    public void mainactivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
