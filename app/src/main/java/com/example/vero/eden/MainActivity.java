package com.example.vero.eden;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.List;

import backend.Carbon;

public class MainActivity extends AppCompatActivity {
    public final static String CO2_KEY = "co2Key";
    private List<String> sitem = new ArrayList<String>();
    private LinearLayout mLayout;
    private ArrayList<Spinner> listofItemSpin = new ArrayList<>();
    private ArrayList<EditText> listofKgSpin = new ArrayList<>();
    private Spinner groceryItem;
    private EditText kilogram;
    private Button enterCarbonbtn;
    private ArrayList<Double> listofCo2 = new ArrayList<>();

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

        groceryItem = (Spinner) findViewById(R.id.groceryItemSpinner);
        String product = getIntent().getStringExtra(CameraActivity.PRODUCT_KEY);
        if (product!= null){
            if (product.equals("Beef")){
                groceryItem.setSelection(1);
            }
            else if (product.equals("Cheese")){
                groceryItem.setSelection(2);
            }
            else if (product.equals("Pork")){
                groceryItem.setSelection(3);
            }
        }
    }

    public void cameraactivity(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void splashscreen(View view) {
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
    }

    public void graphscreen(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
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

        editKg.setPadding(0, 0, 0, 0);

        // Set the EditTexts so that they are centered.
        //editWeight.setGravity(Gravity.END);
        itemSpin.setLayoutParams(p);
        editKg.setLayoutParams(p);

        // Programmatically adding the spinner and edittext now as a simple dropdown list.
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sitem);
        itemSpin.setAdapter(spinnerArrayAdapter);

        // Customise the EditTexts
        editKg.setBackgroundResource(R.drawable.edittext_border);
        editKg.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Set the colour of the spinner to green
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.grocery_list, R.layout.spinner_layout);
        itemSpin.setAdapter(adapter);

        // Add the EditTexts to the previously created linear layout.
        l1.addView(itemSpin);
        l1.addView(editKg);

        // Add the EditTexts to our list of Spinner items and EditText Weights.
        listofItemSpin.add(itemSpin);
        listofKgSpin.add(editKg);

        // Add the linearlayout to our activity to be visible.
        mLayout.addView(l1);
    }

    public void removeItem(View view) {
        // If there are more than one grocery items added.
        if ((listofItemSpin.isEmpty() == false) && (listofKgSpin.isEmpty() == false)) {

            // Retrieve the last grocery items added.
            Spinner item = listofItemSpin.get(listofItemSpin.size() - 1);
            EditText weight = listofKgSpin.get(listofKgSpin.size() - 1);

            // Set them to be not visible and remove them from our lists of EditTexts.
            item.setVisibility(View.GONE);
            weight.setVisibility(View.GONE);
            listofItemSpin.remove(item);
            listofKgSpin.remove(weight);
        }
    }

    public void enterCarbon(View view) {
        groceryItem = (Spinner) findViewById(R.id.groceryItemSpinner);
        kilogram = (EditText) findViewById(R.id.kgEditText);
        enterCarbonbtn = (Button) findViewById(R.id.enterBtn);
        final Carbon carbonfp = new Carbon();
        final Intent intent = new Intent(this, Res.class);

        enterCarbonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When clicked
                String firstg = String.valueOf(groceryItem.getSelectedItem());
                String firstk = kilogram.getText().toString();

                double firstvalue;
                if (firstk.indexOf('.') < 0){
                    firstk += ".0";
                }

                firstvalue = Double.parseDouble(firstk);


                listofCo2.add(carbonfp.getCarbonEmissions(firstg, firstvalue));
                for (int i = 0; i < listofItemSpin.size(); i++){

                    // Get the spinner and edittexts
                    Spinner item = listofItemSpin.get(i);
                    EditText kg = listofKgSpin.get(i);

                    String itemString = String.valueOf(item.getSelectedItem());
                    double value;
                    String text = kg.getText().toString();
                    if (text.indexOf('.') < 0){
                        text += ".0";
                    }

                    value = Double.parseDouble(text);

                    listofCo2.add(carbonfp.getCarbonEmissions(itemString, value));
                }
                Double totalcarbonFootprint = carbonfp.getTotalCarbon(listofCo2);
                Double newKB = Math.round(totalcarbonFootprint*100.0)/100.0;
                String s = newKB.toString();
                intent.putExtra(CO2_KEY, s);
                listofCo2.clear();
                totalcarbonFootprint = 0.0;
                startActivity(intent);
            }
        });
    }
}
