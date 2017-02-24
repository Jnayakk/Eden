package com.example.vero.eden;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> sitem = new ArrayList<String>();
    private LinearLayout mLayout;
    private ArrayList<Spinner> listofItemSpin = new ArrayList<>();
    private ArrayList<EditText> listofKgSpin = new ArrayList<>();
    private ArrayList<EditText> listofEditQuantities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.addSpinnerLayout);

        sitem.add("Lamb");
        sitem.add("Beef");
        sitem.add("Cheese");
        sitem.add("Pork");
        sitem.add("Turkey");
        sitem.add("Chicken");
        sitem.add("Tuna");
        sitem.add("Eggs");
        sitem.add("Potatoes");
        sitem.add("Rice");
        sitem.add("Nuts");
        sitem.add("Beans");
        sitem.add("Vegetables");


    }

    public void cameraactivity(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void splashscreen(View view) {
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
    }

    public void addItem(View view) {
        // Set the weight of the button to 1
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;

        // Set the weight of the textviews to 3
        LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p2.weight = 3;

        // Create a new linear layout containing the edittexts of the assignments and weights in
        // this IndividualCourseActivity
        LinearLayout l1 = new LinearLayout(MainActivity.this);
        LinearLayout l2 = new LinearLayout(MainActivity.this);

        // Create EditTexts for grocery item and kg.
        Spinner itemSpin = new Spinner(MainActivity.this);
        EditText editKg = new EditText(MainActivity.this);
        EditText editQuantity = new EditText(MainActivity.this);

        // Create the placeholder textviews just for nicer UI.
        TextView blankText = new TextView(MainActivity.this);
        TextView blankText2 = new TextView(MainActivity.this);

        // Set the EditTexts so that they are centered.
        //editWeight.setGravity(Gravity.END);
        itemSpin.setLayoutParams(p);
        editKg.setLayoutParams(p);
        l2.setLayoutParams(p);
        editQuantity.setLayoutParams(p);
        blankText.setLayoutParams(p2);
        blankText2.setLayoutParams(p2);

        // Programmatically adding the spinner and edittext now as a simple dropdown list.
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sitem);
        itemSpin.setAdapter(spinnerArrayAdapter);

        // Customise the EditTexts
        /*
        editKg.setBackgroundResource(R.drawable.edittext_border);
        editQuantity.setBackgroundResource(R.drawable.edittext_border);

        editKg.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        editQuantity.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        */

        // Add the quantity to the second linear layout
        l2.addView(blankText);
        l2.addView(editQuantity);
        l2.addView(blankText2);

        // Add the EditTexts to the previously created linear layout.
        l1.addView(itemSpin);
        l1.addView(editKg);
        l1.addView(l2);

        // Add the EditTexts to our list of Spinner items and EditText Weights.
        listofItemSpin.add(itemSpin);
        listofKgSpin.add(editKg);
        listofEditQuantities.add(editQuantity);

        // Add the linearlayout to our activity to be visible.
        mLayout.addView(l1);
    }

    public void removeItem(View view) {

        // If there are more than one grocery items added.
        if ((listofItemSpin.isEmpty() == false) && (listofKgSpin.isEmpty() == false) &&
                (listofEditQuantities.isEmpty() == false)) {

            // Retrieve the last grocery items added.
            Spinner item = listofItemSpin.get(listofItemSpin.size() - 1);
            EditText weight = listofKgSpin.get(listofKgSpin.size() - 1);
            EditText quantity = listofEditQuantities.get(listofEditQuantities.size() - 1);

            // Set them to be not visible and remove them from our lists of EditTexts.
            item.setVisibility(View.GONE);
            weight.setVisibility(View.GONE);
            quantity.setVisibility(View.GONE);
            listofItemSpin.remove(item);
            listofKgSpin.remove(weight);
            listofEditQuantities.remove(quantity);
        }
    }
}
