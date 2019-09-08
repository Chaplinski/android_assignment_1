package com.example.scott.helloworld;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
//    private Button buttonConvert;
//    private Button buttonClear;
    private TextView textDegreesTop;
    private TextView textDegreesBottom;
    private int radioValue = 0;
    ArrayList<String> conversionHistory = new ArrayList<String>();
//    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        buttonConvert = findViewById(R.id.buttonConvert);
//        buttonClear = findViewById(R.id.buttonClear);

    }

    //radio methods
    public void radioFahrenheitClicked(View v) {
        textDegreesTop = (TextView) findViewById(R.id.textDegreesTop);
        textDegreesTop.setText("Fahrenheit Degrees: ");
        textDegreesBottom = (TextView) findViewById(R.id.textDegreesBottom);
        textDegreesBottom.setText("Celsius Degrees: ");
        radioValue = 0;

    }

    public void radioCelsiusClicked(View v) {
        textDegreesTop = (TextView) findViewById(R.id.textDegreesTop);
        textDegreesTop.setText("Celsius Degrees: ");
        textDegreesBottom = (TextView) findViewById(R.id.textDegreesBottom);
        textDegreesBottom.setText("Fahrenheit Degrees: ");
        radioValue = 1;

    }

    //button methods
    public void convertButtonClicked(View v) {
        EditText inputText = (EditText)findViewById(R.id.textInputTop);
        String stringResult = inputText.getText().toString();
        float floatResult = Float.parseFloat(stringResult);
        //Log.d(TAG, "Converting " + floatResult);
        String finalResult = "";
        if (radioValue == 0){
            double celsiusValue = (floatResult - 32.0)/1.8;
            DecimalFormat df = new DecimalFormat("#.#");
            String formattedValue = df.format(celsiusValue);
            finalResult = floatResult + " F ==> " + formattedValue + " C";
            Log.d(TAG, finalResult);
        } else if(radioValue == 1){
            double fahrenheitValue = (floatResult * 1.8) + 32;
            DecimalFormat df = new DecimalFormat("#.#");
            String formattedValue = df.format(fahrenheitValue);
            finalResult = floatResult + " C ==> " + formattedValue + " F";
            Log.d(TAG, finalResult);
        }

        conversionHistory.add(finalResult);

        int i = 0;
        for (String s: conversionHistory){
            Log.d(TAG, "Numero " + i + " is " + s);
            i++;
        }

    }

    public void clearButtonClicked(View v) {
        Log.d(TAG, "clearButtonClicked: THOU HAST BEEN CLICK-ED!");

    }








}
