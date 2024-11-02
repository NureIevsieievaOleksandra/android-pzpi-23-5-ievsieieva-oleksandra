package ievsieieva.oleksandra.nure;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttonC;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonMultiply;
    Button buttonDiv;
    Button buttonEqual;
    Button buttonComa;
    EditText resultText;

    String v1 = "";
    String v2 = "";
    String operator = "";
    String result = "";


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String clicked = ((Button) v).getText().toString();
            switch (clicked) {
                case "1":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "1";
                    } else {
                        v2 += "1";
                    }
                    break;
                case "2":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "2";
                    } else {
                        v2 += "2";
                    }
                    break;
                case "3":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "3";
                    } else {
                        v2 += "3";
                    }
                    break;
                case "4":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "4";
                    } else {
                        v2 += "4";
                    }
                    break;
                case "5":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "5";
                    } else {
                        v2 += "5";
                    }
                    break;
                case "6":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "6";
                    } else {
                        v2 += "6";
                    }
                    break;
                case "7":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "7";
                    } else {
                        v2 += "7";
                    }
                    break;
                case "8":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "8";
                    } else {
                        v2 += "8";
                    }
                    break;
                case "9":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "9";
                    } else {
                        v2 += "9";
                    }
                    break;
                case "0":
                    if(TextUtils.isEmpty(operator)) {
                        v1 += "0";
                    } else {
                        v2 += "0";
                    }
                    break;
                case ",":
                    if(TextUtils.isEmpty(operator)) {
                        if (TextUtils.lastIndexOf((CharSequence) v1,',' ) < 0) {
                            if(TextUtils.isEmpty(v1)){
                                v1 = "0,";
                            } else {
                                v1 += ",";
                            }
                        }
                    } else {
                        if (TextUtils.lastIndexOf((CharSequence) v2,',' ) < 0) {
                            if(TextUtils.isEmpty(v2)){
                                v2 = "0,";
                            } else {
                                v2 += ",";
                            }
                        }
                    }
                    break;

                case "+":
                    if (TextUtils.isEmpty(operator)) {
                        operator = "+";
                    }
                    break;
                case "-":
                    if (TextUtils.isEmpty(operator)) {
                        operator = "-";
                    }
                    break;
                case "*":
                    if (TextUtils.isEmpty(operator)) {
                        operator = "*";
                    }
                    break;
                case ":":
                    if (TextUtils.isEmpty(operator)) {
                        operator = ":";
                    }
                    break;
                case "=":
                    if (!TextUtils.isEmpty(v1) && !TextUtils.isEmpty(v2) && !TextUtils.isEmpty(operator)) {
                        try {
                            v1 = v1.replace(',', '.');
                            v2 = v2.replace(',', '.');
                            Float operant1 = Float.parseFloat(v1);
                            Float operant2 = Float.parseFloat(v2);
                            switch (operator) {
                                case "+":
                                    result = String.valueOf(operant1 + operant2);
                                    break;
                                case "-":
                                    result = String.valueOf(operant1 - operant2);
                                    break;
                                case "*":
                                    result = String.valueOf(operant1 * operant2);
                                    break;
                                case ":":
                                    result = String.valueOf(operant1 / operant2);
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "C":
                    v1 = "";
                    v2 = "";
                    operator = "";
                    result = "";
                    resultText.setText("");
                    break;
            }
            if (TextUtils.equals(clicked, "=")) {
                resultText.setText(result.replace('.', ','));
                v1 = "";
                v2 = "";
                operator = "";
                result = "";
            } else {
                if (TextUtils.isEmpty(operator)) {
                    resultText.setText(v1);
                } else {
                    resultText.setText(v2);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);

        buttonC = findViewById(R.id.buttonCancel);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDiv = findViewById(R.id.buttonDivide);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonComa = findViewById(R.id.buttonComa);

        resultText = findViewById(R.id.result);

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        button0.setOnClickListener(listener);

        buttonPlus.setOnClickListener(listener);
        buttonMinus.setOnClickListener(listener);
        buttonMultiply.setOnClickListener(listener);
        buttonDiv.setOnClickListener(listener);
        buttonComa.setOnClickListener(listener);

        buttonC.setOnClickListener(listener);
        buttonEqual.setOnClickListener(listener);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("v1", v1);
        outState.putString("v2", v2);
        outState.putString("operator", operator);
        outState.putString("result", result);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        v1 = savedInstanceState.getString("v1", "");
        v2 = savedInstanceState.getString("v2", "");
        operator = savedInstanceState.getString("operator", "");
        result = savedInstanceState.getString("result", "");
    }
}