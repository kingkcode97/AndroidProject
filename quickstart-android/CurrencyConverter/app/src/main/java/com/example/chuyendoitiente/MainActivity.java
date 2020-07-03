package com.example.chuyendoitiente;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final double USD_VND = 23211;
    private static final double EUR_VND = 26038.22;
    private static final double JPY_VND = 216.47;

    private TextView txtHeader,txtConvertFrom,txtConvertTo,txtFooter1,txtFooter2,txtUpdate;
    private EditText editMoney;
    private Spinner sprinnerFrom, sprinnerTo;
    private Button btnSwitch,btnConvert;
    private Map<String,Integer> arrayFrom = new HashMap<>();
    private Map<String,Integer> arrayTo = new HashMap<>();
    private Map<String,Double> changeMoney = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = sprinnerFrom.getSelectedItem().toString();
                String z = sprinnerTo.getSelectedItem().toString();
                sprinnerFrom.setSelection(arrayFrom.get(z));
                sprinnerTo.setSelection(arrayTo.get(s));

            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String money = editMoney.getText().toString();
                String from = sprinnerFrom.getSelectedItem().toString();
                String to = sprinnerTo.getSelectedItem().toString();
                String convertFrom = "";
                String convertTo = "";
                String footer1 = "";
                String footer2 = "";
                double moneyRes = 0.0;
                if(TextUtils.isDigitsOnly(money) && !TextUtils.isEmpty(money) && !from.equals(to)){
                    double moneyChange = Double.valueOf(money);
                    if(to.equals("VND")){
                        moneyRes = moneyChange * changeMoney.get(from);
                    }
                    else if(from.equals("VND")) {
                        moneyRes = moneyChange / changeMoney.get(to);
                    }
                    else{
                        moneyRes = changeMoney1(from,to,moneyChange);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Không hợp lệ", Toast.LENGTH_LONG).show();
                }
                DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
                String moneyResFormat = decimalFormat.format(moneyRes);
                convertFrom = money + " " + from + "=";
                convertTo = moneyResFormat + " " + to;
                if(from.equals("VND")){
                    footer1 = "1 VND = " + decimalFormat.format(1/changeMoney.get(to)) + " " + to;
                    footer2 = "1 " + to + " = " + decimalFormat.format(changeMoney.get(to)) + " VND" ;
                }else if(to.equals("VND")){
                    footer1 = "1 " + from + " = " + changeMoney.get(from) + " " + " VND";
                    footer2 = "1 VND = " + decimalFormat.format(1/changeMoney.get(from)) + " " + from ;
                }
                else{
                    footer1 = "1 " + from + " = " + decimalFormat.format(changeMoney.get(from)/changeMoney.get(to)) + " " + to;
                    footer2 = "1 " + to + " = " + decimalFormat.format(changeMoney.get(to)/changeMoney.get(from)) + " " + from;
                }

                txtConvertFrom.setText((convertFrom));
                txtConvertTo.setText(convertTo);
                txtFooter1.setText(footer1);
                txtFooter2.setText(footer2);

            }
        });

    }


    private void init(){
        txtHeader = (TextView) findViewById(R.id.txtHeader);
        txtConvertFrom = (TextView) findViewById(R.id.txtConvertFrom);
        txtConvertTo = (TextView) findViewById(R.id.txtConvertTo);
        txtFooter1 = (TextView) findViewById(R.id.txtFooter1);
        txtFooter2 = (TextView) findViewById(R.id.txtFooter2);
        txtUpdate = (TextView) findViewById(R.id.txtUpdate);

        editMoney = (EditText) findViewById(R.id.editMoney);

        sprinnerFrom = (Spinner) findViewById(R.id.sprinnerFrom);
        sprinnerTo = (Spinner) findViewById(R.id.sprinnerTo);

        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        btnConvert = (Button) findViewById(R.id.btnConvert);

        arrayFrom.put("USD",0);
        arrayFrom.put("EUR",1);
        arrayFrom.put("VND",2);
        arrayFrom.put("JPY",3);

        arrayTo.put("EUR",0);
        arrayTo.put("JPY",1);
        arrayTo.put("VND",2);
        arrayTo.put("USD",3);


        changeMoney.put("USD",23211.0);
        changeMoney.put("EUR",26038.22);
        changeMoney.put("JPY",216.47);
    }

    private double changeMoney1(String moneyFrom, String moneyTo, double money){
        double res = 0.0;
        res = (money * changeMoney.get(moneyFrom)) / changeMoney.get(moneyTo);
        return res;
    }
}