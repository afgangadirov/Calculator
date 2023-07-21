package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_result, textView_solution;
    private MaterialButton button_ac, button_c;
    private MaterialButton button_percent, button_divide,button_multiply,button_minus,button_plus,button_equals;
    private MaterialButton button_9,button_8,button_7,button_6,button_5,button_4,button_3,button_2,button_1,button_0,button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_result = findViewById(R.id.textView_result);
        textView_solution = findViewById(R.id.textView_solution);

        assignId(button_ac,R.id.button_ac);
        assignId(button_c,R.id.button_c);
        assignId(button_percent,R.id.button_percent);
        assignId(button_divide,R.id.button_divide);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_minus,R.id.button_minus);
        assignId(button_plus,R.id.button_plus);
        assignId(button_equals,R.id.button_equals);
        assignId(button_9,R.id.button_9);
        assignId(button_8,R.id.button_8);
        assignId(button_7,R.id.button_7);
        assignId(button_6,R.id.button_6);
        assignId(button_5,R.id.button_5);
        assignId(button_4,R.id.button_4);
        assignId(button_3,R.id.button_3);
        assignId(button_2,R.id.button_2);
        assignId(button_1,R.id.button_1);
        assignId(button_0,R.id.button_0);
        assignId(button_dot,R.id.button_dot);
    }



    public void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = textView_solution.getText().toString();

        if (buttonText.equals("AC")){
            textView_solution.setText("");
            textView_result.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            textView_solution.setText(textView_result.getText());
            return;
        }
        if (buttonText.equals("C")){
            if (dataCalculate.length()>0){
                textView_solution.setText(dataCalculate.substring(0,dataCalculate.length()-1));
                return;
            }

        }
        else {
            dataCalculate = dataCalculate + buttonText;
        }


        textView_solution.setText(dataCalculate);

        String finalResult = getResult(dataCalculate);

        if (!finalResult.equals("Error")){
            textView_result.setText(finalResult);
        }

    }

    String getResult(String data){
        data = data.replaceAll("×","*");
        data = data.replaceAll("÷","/");
        data = data.replaceAll("%","/100");
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }

    }



}