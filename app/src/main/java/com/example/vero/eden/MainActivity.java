package com.example.vero.eden;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayList<TextView> listofBlankTexts = new ArrayList<>();

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

        // Create a new linear layout containing the edittexts of the assignments and weights in
        // this IndividualCourseActivity
        LinearLayout l1 = new LinearLayout(MainActivity.this);
        l1.setOrientation(LinearLayout.HORIZONTAL);

        // Create EditTexts for grocery item and kg.
        Spinner itemSpin = new Spinner(MainActivity.this);
        EditText editKg = new EditText(MainActivity.this);
        EditText editQuantity = new EditText(MainActivity.this);

        editKg.setPadding(0, 0, 0, 0);
        editQuantity.setPadding(0, 0, 0, 0);

        // Create the placeholder textviews just for nicer UI.
        TextView blankText = new TextView(MainActivity.this);

        // Set the EditTexts so that they are centered.
        //editWeight.setGravity(Gravity.END);
        itemSpin.setLayoutParams(p);
        editKg.setLayoutParams(p);
        editQuantity.setLayoutParams(p);
        blankText.setWidth(50);

        // Programmatically adding the spinner and edittext now as a simple dropdown list.
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sitem);
        itemSpin.setAdapter(spinnerArrayAdapter);

        // Customise the EditTexts
        editKg.setBackgroundResource(R.drawable.edittext_border);
        editQuantity.setBackgroundResource(R.drawable.edittext_border);

        // Set the colour of the spinner to green
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.grocery_list, R.layout.spinner_layout);
        itemSpin.setAdapter(adapter);

        // Add the EditTexts to the previously created linear layout.
        l1.addView(itemSpin);
        l1.addView(editKg);
        l1.addView(blankText);
        l1.addView(editQuantity);

        // Add the EditTexts to our list of Spinner items and EditText Weights.
        listofItemSpin.add(itemSpin);
        listofKgSpin.add(editKg);
        listofEditQuantities.add(editQuantity);
        listofBlankTexts.add(blankText);

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
            TextView blank = listofBlankTexts.get(listofBlankTexts.size() - 1);

            // Set them to be not visible and remove them from our lists of EditTexts.
            item.setVisibility(View.GONE);
            weight.setVisibility(View.GONE);
            quantity.setVisibility(View.GONE);
            blank.setVisibility(View.GONE);
            listofItemSpin.remove(item);
            listofKgSpin.remove(weight);
            listofEditQuantities.remove(quantity);
            listofBlankTexts.remove(blank);
        }
    }
}
