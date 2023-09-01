package com.example.calculatorpractice;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView displayTextView;
    private StringBuilder currentInput = new StringBuilder();
    private double firstNumber = 0.0;
    private char operator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        displayTextView = findViewById(R.id.displayTextView);

        setButtonClickListener(R.id.button0);
        setButtonClickListener(R.id.button1);
        setButtonClickListener(R.id.button2);
        setButtonClickListener(R.id.button3);
        setButtonClickListener(R.id.button4);
        setButtonClickListener(R.id.button5);
        setButtonClickListener(R.id.button6);
        setButtonClickListener(R.id.button7);
        setButtonClickListener(R.id.button8);
        setButtonClickListener(R.id.button9);
        // Add more button click listeners for numbers

        setButtonClickListener(R.id.buttonPlus);
        setButtonClickListener(R.id.buttonMinus);
        setButtonClickListener(R.id.buttonMultiply);
        setButtonClickListener(R.id.buttonDivide);

        setButtonClickListener(R.id.buttonEquals);
        setButtonClickListener(R.id.buttonClear);
    }

    private void setButtonClickListener(int buttonId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(button.getText().toString());
            }
        });
    }

    private void handleButtonClick(String buttonValue) {
        if (buttonValue.equals("=")) {
            if (currentInput.length() > 0) {
                double secondNumber = Double.parseDouble(currentInput.toString());
                double result = performCalculation(firstNumber, secondNumber, operator);
                displayTextView.setText(String.valueOf(result));
                currentInput.setLength(0);
                firstNumber = result;
            }
        } else if (buttonValue.equals("C")) {
            currentInput.setLength(0);
            firstNumber = 0.0;
            operator = ' ';
            displayTextView.setText("");
        } else if (buttonValue.matches("[+\\-*/]")) {
            if (currentInput.length() > 0) {
                firstNumber = Double.parseDouble(currentInput.toString());
                operator = buttonValue.charAt(0);
                currentInput.setLength(0);
            }
        } else {
            currentInput.append(buttonValue);
            displayTextView.setText(currentInput.toString());
        }
    }

    private double performCalculation(double num1, double num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    // Division by zero error
                    return Double.NaN;
                }
            default:
                return num2;
        }
    }
}

