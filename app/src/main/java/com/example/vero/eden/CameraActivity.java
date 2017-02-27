package com.example.vero.eden;

import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CameraActivity extends Activity implements OnClickListener {
    public final static String PRODUCT_KEY = "product_key";
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }
    public void onClick(View v){
        //respond to clicks
        if(v.getId()==R.id.scan_button){
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            // display it on screen
            if (new String("041757011611").equals(scanContent)){
                Toast.makeText(getApplicationContext(), "Cheese added to your list", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra(PRODUCT_KEY, "Cheese");
                startActivity(intent1);
            }
            else if (new String("036000291452").equals(scanContent)){
                Toast.makeText(getApplicationContext(), "Beef added to your list", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra(PRODUCT_KEY, "Beef");
                startActivity(intent1);
            }
            else if (new String("188114771211").equals(scanContent)){
                Toast.makeText(getApplicationContext(), "Pork added to your list", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra(PRODUCT_KEY, "Pork");
                startActivity(intent1);
            }
            else if (new String("067800000418").equals(scanContent)){
                Toast.makeText(getApplicationContext(), "Tuna added to your list", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra(PRODUCT_KEY, "Tuna");
                startActivity(intent1);
            }

            //formatTxt.setText(scanFormat);
            //contentTxt.setText(scanContent);


        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void splashscreen(View view) {
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
    }

    public void graphscreen(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    public void mainactivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

