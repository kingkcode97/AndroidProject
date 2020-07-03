package com.example.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    Button btMinusPz, btPlusPz, btMinusH, btPlusH, btMinusBM, btPlusBM, btOrder, btReset;
    TextView txtCountPz, txtCountH, txtCountBM;
    TextView txtShowdiscount, txtPrice;
    CheckBox AddPM, Add2PM, Add3PM ,AddPMH, AddBeefH, AddEggH;
    EditText txtDiscount;
    RadioGroup rg1, rg2, rg3;
    RadioButton rb1, rb2, rb3, AddDeMong,AddDeDay,AddDeTT, CheckPM,CheckSS, AddEggBM,AddPMBM,AddFizzBM;
    int loaiP = 5000, topping = 5000;
    int priceP, priceH, priceBM = 25000;
    int countP, countH, countBM;
    int dc = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khai bao
        init();

        //goi su kien click
        CountPzClick();
        CountHClick();
        CountBMClick();
        OrderClick();
        RestartClick();

    }

    public void init(){
        btPlusPz = findViewById(R.id.btPlusPz);
        btPlusH = findViewById(R.id.btPlusH);
        btPlusBM = findViewById(R.id.btPlusBM);

        btMinusPz = findViewById(R.id.btMinusPz);
        btMinusH = findViewById(R.id.btMinusH);
        btMinusBM = findViewById(R.id.btMinusBM);

        btOrder = findViewById(R.id.btOrder);
        btReset = findViewById(R.id.btReset);

        txtCountPz = findViewById(R.id.txtCountPz);
        txtCountH = findViewById(R.id.txtCountH);
        txtCountBM = findViewById(R.id.txtCountBM);
        txtPrice = findViewById(R.id.txtPrice);

        txtShowdiscount = findViewById(R.id.txtShowDiscount);
        txtDiscount = findViewById(R.id.txtDiscount);

        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rg3 = findViewById(R.id.rg3);

        AddDeMong = findViewById(R.id.AddDeMong);
        AddDeDay = findViewById(R.id.AddDeDay);
        AddDeTT = findViewById(R.id.AddDeTT);
        CheckPM = findViewById(R.id.CheckPM);
        CheckSS = findViewById(R.id.CheckSS);
        AddEggBM = findViewById(R.id.AddEggBM);
        AddPMBM = findViewById(R.id.AddPMBM);
        AddFizzBM = findViewById(R.id.AddFizzBM);
        AddPMH = findViewById(R.id.AddPMH);
        AddBeefH = findViewById(R.id.AddBeefH);
        Add2PM = findViewById(R.id.Add2PM);
        Add3PM = findViewById(R.id.Add3PM);
        AddPM = findViewById(R.id.AddPM);
        AddEggH = findViewById(R.id.AddEggH);
    }

    //Lay so luong pizza
    public void CountPzClick() {
        btPlusPz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countP = Integer.parseInt(txtCountPz.getText().toString());
                countP++;
                txtCountPz.setText(String.valueOf(countP));
                Price();
            }
        });
        btMinusPz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countP = Integer.parseInt(txtCountPz.getText().toString());
                if (countP > 0)
                    countP--;
                txtCountPz.setText(String.valueOf(countP));
                Price();
            }
        });
    }

    //Lay so luong hambuger
    public void CountHClick() {
        btPlusH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countH = Integer.parseInt(txtCountH.getText().toString());
                countH++;
                txtCountH.setText(String.valueOf(countH));
                Price();
            }
        });
        btMinusH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countH = Integer.parseInt(txtCountH.getText().toString());
                if (countH > 0)
                    countH--;
                txtCountH.setText(String.valueOf(countH));
                Price();
            }
        });
    }

    //Lay so luong banh mi
    public void CountBMClick() {
        btPlusBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countBM = Integer.parseInt(txtCountBM.getText().toString());
                countBM++;
                txtCountBM.setText(String.valueOf(countBM));
                Price();
            }
        });
        btMinusBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int countBM = Integer.parseInt(txtCountBM.getText().toString());
                if (countBM > 0)
                    countBM--;
                txtCountBM.setText(String.valueOf(countBM));
                Price();
            }
        });
    }

    //xu li dat mon
    public void OrderClick() {
        btOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkQuantity()) {
                    Toast.makeText(getApplicationContext(), "Dat mon thanh cong!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Khong hoan tat duoc!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //xu li lam lai
    public void RestartClick() {
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshEditText(txtDiscount);
                refreshTextView(txtShowdiscount,txtPrice,txtCountPz,txtCountH,txtCountBM);
                refreshCheckBox(AddPM,Add2PM,Add3PM,AddPMH, AddBeefH,AddEggH);
                refreshRadioButton(AddDeMong,AddDeDay,AddDeTT);
                refreshRadioButton(CheckPM,CheckSS);
                refreshRadioButton(AddEggBM,AddPMBM,AddFizzBM);
                Toast.makeText(getApplicationContext(), "Da duoc dat lai!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //xu li radiobutton
    public void rbClick(View v) {

        int radiobuttonid1 = rg1.getCheckedRadioButtonId();
        int radiobuttonid2 = rg2.getCheckedRadioButtonId();
        int radiobuttonid3 = rg3.getCheckedRadioButtonId();
        rb1 = findViewById(radiobuttonid1);
        rb2 = findViewById(radiobuttonid2);
        rb3 = findViewById(radiobuttonid3);

        switch (rb1.getId()) {
            case R.id.AddDeMong:
                loaiP = 5000;
                Price();
                break;
            case R.id.AddDeDay:
                loaiP = 10000;
                Price();
                break;
            case R.id.AddDeTT:
                loaiP = 15000;
                Price();
                break;
        }
        switch (rb2.getId()) {
            case R.id.CheckPM:
                topping = 5000;
                Price();
                break;
            case R.id.CheckSS:
                topping = 10000;
                Price();
                break;
        }
        switch (rb3.getId()) {
            case R.id.AddEggBM:
                priceBM = 25000;
                Price();
                break;
            case R.id.AddPMBM:
                priceBM = 30000;
                Price();
                break;
            case R.id.AddFizzBM:
                priceBM = 35000;
                Price();
                break;
        }
    }

    //xu li checkbox
    public void Click(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.AddPM:
                if (checked)
                    priceP += 10000;
                else
                    priceP -= 10000;
                Price();
                break;
            case R.id.Add2PM:
                if (checked)
                    priceP += 20000;
                else
                    priceP -= 20000;
                Price();
                break;
            case R.id.Add3PM:
                if (checked)
                    priceP += 30000;
                else
                    priceP -= 30000;
                Price();
                break;
            case R.id.AddPMH:
                if (checked)
                    priceH += 15000;
                else
                    priceH -= 15000;
                Price();
                break;
            case R.id.AddBeefH:
                if (checked)
                    priceH += 20000;
                else
                    priceH -= 20000;
                Price();
                break;
            case R.id.AddEggH:
                if (checked)
                    priceH += 25000;
                else
                    priceH -= 25000;
                Price();
                break;
        }
    }

    //check discount
    public void discount() {
        String dis = txtDiscount.getText().toString();
        if (dis.equals("abc") || dis.equals("ABC"))
            dc = 80;
        else if (dis.equals("xyz") || dis.equals("XYZ"))
            dc = 90;
        else dc = 100;
    }

    //Thanh tien
    public void Price() {
        int price = 0;
        int totalPriceP = 0, totalPriceH = 0, totalPriceBM = 0;
        int price1 = 150000, price2 = 45000, price3 = 0;

        discount();
        if (countP > 0)
            totalPriceP = (price1 + priceP + loaiP + topping) * countP;
        if (countH > 0)
            totalPriceH = (price2 + priceH) * countH;
        if (countBM > 0)
            totalPriceBM = priceBM * countBM;
        if (countP > 0 && countH == 0 && countBM == 0)
            price = totalPriceP;
        else if (countP == 0 && countH > 0 && countBM == 0)
            price = totalPriceH;
        else if (countP == 0 && countH == 0 && countBM > 0)
            price = totalPriceBM;
        if (countP > 0 || countH > 0 || countBM > 0)
            price = (totalPriceP + totalPriceH + totalPriceBM) * dc / 100;
        else price = 0;
        txtPrice.setText(String.valueOf(price));
        txtShowdiscount.setText(String.valueOf(100 - dc) + "%");
    }

    private void refreshCheckBox(CheckBox... checkBoxes){
        for(CheckBox checkBox : checkBoxes){
            checkBox.setChecked(false);
        }
    }

    private void refreshRadioButton(RadioButton... radioButtons){
        for(RadioButton radioButton : radioButtons){
            radioButton.setChecked(false);
        }
    }

    private void refreshEditText(EditText... editTexts){
        for(EditText editText : editTexts){
            editText.setText("");
        }
    }

    private void refreshTextView(TextView... textViews){
        for(TextView textView : textViews){
            textView.setText("0");
        }
    }

    private boolean checkQuantity(){
        String price = txtPrice.getText().toString();
        String countPz = txtCountPz.getText().toString();
        String countH = txtCountH.getText().toString();
        String countBM = txtCountBM.getText().toString();

        if(price.equals("0") && countPz.equals("0") && countH.equals("0") && countBM.equals("0"))
            return false;
        else
            return true;
    }
}
