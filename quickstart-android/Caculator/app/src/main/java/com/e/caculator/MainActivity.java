package com.e.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Khoi tao bien can dung
    private static final String TAG_CALCULATOR = "CALCULATOR";
    private TextView resultTextView = null;
    private double firstNumber = Double.MIN_VALUE;
    private double secondNumber = Double.MIN_VALUE;

    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result);
        TableLayout rootView = (TableLayout) findViewById(R.id.root_view);
        int rowCount = rootView.getChildCount();

        // Vô hiệu hóa bàn phím mềm của thành phần xem đầu vào edittext.
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // For loop de duyet qua tung button trong tung TableRow va hien thi no len View
        for (int i = 0; i<rowCount;i++){
            View childView = rootView.getChildAt(i);

            if (childView instanceof TableRow){
                TableRow tableRow = (TableRow)childView;
                int childCount = tableRow.getChildCount();

                for (int j = 0; j < childCount; j++){
                    childView = tableRow.getChildAt(j);

                    if(childView instanceof Button)
                        childView.setOnClickListener(this);
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button button = (Button) v;
            String buttonValue = button.getText().toString();

            // Kiem tra button la so hay khong?
            int buttonNumber = isInteger(buttonValue);

            if (buttonNumber != -1) {
                // Lay gia tri trong TextView
                String currentValue = resultTextView.getText().toString();

                if (isInteger(currentValue) > -1) {
                    // Neu button nhap vao la so thi them chu so do vao cuoi cua so
                    currentValue += buttonValue;
                    resultTextView.setText(currentValue);
                } else {
                    // Neu khong la so thi hien thi button do
                    resultTextView.setText(buttonValue);

                }
            }else{

                // Neu button la 1 toan tu
                if("+".equals(buttonValue) || "-".equals(buttonValue) || "*".equals(buttonValue) || "÷".equals(buttonValue) || "^".equals(buttonValue) || "%".equals(buttonValue)) {
                    // Lay gia tri so hien tai
                    String currentValue = resultTextView.getText().toString();
                    if (isInteger(currentValue) > -1) {
                        // Luu gia tri so dau tien
                        firstNumber = Double.parseDouble(currentValue);
                    } else {
                        // neu khong la so thi reset ve gia tri default
                        firstNumber = Double.MIN_VALUE;
                    }

                    // luu toan tu
                    operator = buttonValue;
                    // hien thi toan tu ra man hinh
                    resultTextView.setText(buttonValue);

                }else if("C".equals(buttonValue)) {
                    // Neu la button C thi reset all gia tri
                    firstNumber = Double.MIN_VALUE;
                    secondNumber = Double.MIN_VALUE;
                    // Hien thi gia tri trong tren man hinh
                    resultTextView.setText("");

                }else if("=".equals(buttonValue))
                {
                    // Lay gia tri so hien tai
                    String currentValue = resultTextView.getText().toString();
                    if(isInteger(currentValue) > -1)
                    {
                        // Luu gia tri so dau tien
                        secondNumber = Double.parseDouble(currentValue);
                    }else
                    {
                        // neu khong la so thi reset ve gia tri default
                        secondNumber = Double.MIN_VALUE;
                    }

                    // Neu la button  =
                    if(firstNumber > Long.MIN_VALUE && secondNumber > Long.MIN_VALUE) {

                        double resultNumber = 0;

                        // tinh toan ket qua
                        if ("+".equals(operator)) {
                            resultNumber = firstNumber + secondNumber;
                        }else if("-".equals(operator))
                        {
                            resultNumber = firstNumber - secondNumber;
                        }else if("*".equals(operator))
                        {
                            resultNumber = firstNumber * secondNumber;
                        }else if("%".equals(operator))
                        {
                            resultNumber = firstNumber % secondNumber;
                        }
                        else if("^".equals(operator))
                        {
                            resultNumber = Math.pow(firstNumber, secondNumber);
                        }
                        else if("÷".equals(operator))
                        {
                            resultNumber = firstNumber / secondNumber;
                        }

                        // Hien thi ket qua
                        resultTextView.setText(String.valueOf(resultNumber));
                        // Luu ket qua vao so thu 2
                        secondNumber = resultNumber;
                        // Reset lai so thu 1
                        firstNumber = Double.MIN_VALUE;
                    }
                }
            }
        }
    }

    // Kiem tra gia tri button co phai la so hay khong?
    private int isInteger(String value) {
        int ret = -1;
        try {
            ret = Integer.parseInt(value);
        }catch(NumberFormatException ex)
        {
            Log.e(TAG_CALCULATOR, ex.getMessage());
        }finally {
            return ret;
        }
    }
}