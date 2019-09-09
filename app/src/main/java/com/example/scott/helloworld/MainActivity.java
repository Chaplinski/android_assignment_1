package com.example.scott.helloworld;

import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

    private TextView textDegreesTop;
    private TextView inputText;
    private TextView textDegreesBottom;
    private TextView textResult;
    private TextView textConversionHistory;
    private int radioValue = 0;
    ArrayList<String> conversionHistory = new ArrayList<String>();
    private String TAG = "BUNDLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDegreesTop = (TextView) findViewById(R.id.textDegreesTop);
        inputText = (EditText)findViewById(R.id.textInputTop);
        textDegreesBottom = (TextView) findViewById(R.id.textDegreesBottom);
        textResult = (TextView) findViewById(R.id.textResult);
        textConversionHistory = (TextView) findViewById(R.id.textInputHistory);

        textConversionHistory.setMovementMethod(new ScrollingMovementMethod());
    }

    //radio methods
    public void radioFahrenheitClicked(View v) {
        textDegreesTop.setText("Fahrenheit Degrees: ");
        textDegreesBottom.setText("Celsius Degrees: ");
        radioValue = 0;
    }

    public void radioCelsiusClicked(View v) {
        textDegreesTop.setText("Celsius Degrees: ");
        textDegreesBottom.setText("Fahrenheit Degrees: ");
        radioValue = 1;
    }

    //button methods

    //method for when convert button is pressed
    public void convertButtonClicked(View v) {
        //this if statement prevents the app from crashing if there is no value input
        if (inputText.length() > 0) {
            String stringInput = inputText.getText().toString();
            float floatInput = Float.parseFloat(stringInput);
            double inputRoundOff = Math.round(floatInput * 10.0)/10.0;
            String stringFinalResult = "";
            double conversionResult = 0.0;
            String formattedValue = "";

            if (radioValue == 0) {
                //if converting F to C
                conversionResult = Math.round(((floatInput - 32.0) / 1.8) * 10.0)/10.0;
                stringFinalResult = inputRoundOff + " F ==> " + conversionResult + " C";
            } else if (radioValue == 1) {
                //if converting C to F
                conversionResult = Math.round(((floatInput * 1.8) + 32) * 10.0)/10.0;
                stringFinalResult = inputRoundOff + " C ==> " + conversionResult + " F";
            }

            String stringConversionResult = Double.toString(conversionResult);

            textResult.setText(stringConversionResult);

            //add string value to conversion history string array
            conversionHistory.add(0, stringFinalResult);

            //create final string to be added to conversion history
            String stringConversionHistory = "";
            for (String s : conversionHistory) {
                stringConversionHistory += s + System.getProperty("line.separator");
            }

            //set conversion history
            textConversionHistory.setText(stringConversionHistory);


        }

    }

    public void clearButtonClicked(View v) {
        //clear conversionHistory array and set conversion history text to empty string
        conversionHistory.clear();
        textConversionHistory.setText("");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        Log.d(TAG, "onSaveInstanceState: " + textDegreesTop.getText().toString());
        outState.putString("TEXTDEGREESTOP", textDegreesTop.getText().toString());
        outState.putString("TEXTDEGREESBOTTOM", textDegreesBottom.getText().toString());
        outState.putString("TEXTRESULT", textResult.getText().toString());
        outState.putString("TEXTHISTORY", textConversionHistory.getText().toString());
        outState.putStringArrayList("ARRAYHISTORY", conversionHistory);
        outState.putInt("RADIOVALUE", radioValue);

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);

        textDegreesTop.setText(savedInstanceState.getString("TEXTDEGREESTOP"));
        textDegreesBottom.setText(savedInstanceState.getString("TEXTDEGREESBOTTOM"));
        textResult.setText(savedInstanceState.getString("TEXTRESULT"));
        textConversionHistory.setText(savedInstanceState.getString("TEXTHISTORY"));

        conversionHistory = savedInstanceState.getStringArrayList("ARRAYHISTORY");

        radioValue = savedInstanceState.getInt("RADIOVALUE");
    }





}
