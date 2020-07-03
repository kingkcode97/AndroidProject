package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btTinhToan;
    RadioButton rdb1, rdb2;
    EditText edtChieuCao, edtCanNang;
    TextView txtBMI, txtNhanXet1, txtNhanXet2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);

        txtBMI = findViewById(R.id.txtBMI);
        txtNhanXet1 = findViewById(R.id.txtNhanXet1);
        txtNhanXet2 = findViewById(R.id.txtNhanXet2);

        btTinhToan = findViewById(R.id.btTinhToan);
        rdb1 = findViewById(R.id.rdb1);
        rdb2 = findViewById(R.id.rdb2);

        start();
    }
    public void start() {
        btTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked();
            }
        });
    }
    public void Checked() {
        if (rdb1.isChecked() == true)
            Nam();
        else
            Nu();
    }
    public void Nam() {
        double bmi = BMI();
        bmi = Math.round(bmi*10)/10;

        if (bmi < 18.5)
        {
            txtNhanXet1.setText("Canh bao:");
            txtNhanXet2.setText("Ban co the trang thap gay, chi so BMI thap!");
        }
        else  if (bmi < 25) {
            txtNhanXet1.setText("Chuc mung ban:");
            txtNhanXet2.setText("Ban co the trang tuong doi, chi so BMI binh thuong!");
        }
        else  if (bmi == 25) {
            txtNhanXet1.setText("Ban co the trang hoi thua can, chi so BMI dat 25!");
            txtNhanXet2.setText("Ban nen van dong phu hop de giam can! ");
        }
        else  if (bmi < 30) {
            txtNhanXet1.setText("Ban co the trang tien beo phi, chi so BMI cao!");
            txtNhanXet2.setText("Loi khuyen: Nen tap the duc va an uong dieu do! ");
        }
        else  if (bmi < 35) {
            txtNhanXet1.setText("Ban co the trang beo phi do I, chi so BMI cao!");
            txtNhanXet2.setText("Loi khuyen: Nen tap the duc va can giam can! ");
        }
        else  if (bmi < 40) {
            txtNhanXet1.setText("Ban co the trang beo phi do II, chi so BMI qua cao!");
            txtNhanXet2.setText("Canh cao: Nen tap the duc va can giam can ngay! ");
        }
        else  if (bmi > 40) {
            txtNhanXet1.setText("Ban co the trang beo phi do III, chi so BMI cao, bao!");
            txtNhanXet2.setText("Canh cao: Nen tap the duc va can giam can gap! ");
        }
        txtBMI.setText(String.valueOf(bmi));
    }
    public void Nu() {
        double bmi = BMI();
        bmi = Math.round(bmi*10)/10;

        if (bmi < 18.5)
        {
            txtNhanXet1.setText("Canh bao:");
            txtNhanXet2.setText("Ban co the trang thap gay, chi so BMI thap!");
        }
        else  if (bmi < 25) {
            txtNhanXet1.setText("Chuc mung ban:");
            txtNhanXet2.setText("Ban co the trang tuong doi, chi so BMI binh thuong!");
        }
        else  if (bmi == 25) {
            txtNhanXet1.setText("Ban co the trang hoi thua can, chi so BMI dat 25!");
            txtNhanXet2.setText("Ban nen van dong phu hop de giam can! ");
        }
        else  if (bmi < 30) {
            txtNhanXet1.setText("Ban co the trang tien beo phi, chi so BMI cao!");
            txtNhanXet2.setText("Loi khuyen: Nen tap the duc va an uong dieu do! ");
        }
        else  if (bmi < 35) {
            txtNhanXet1.setText("Ban co the trang beo phi do I, chi so BMI cao!");
            txtNhanXet2.setText("Loi khuyen: Nen tap the duc va can giam can! ");
        }
        else  if (bmi < 40) {
            txtNhanXet1.setText("Ban co the trang beo phi do II, chi so BMI qua cao!");
            txtNhanXet2.setText("Canh cao: Nen tap the duc va can giam can ngay! ");
        }
        else  if (bmi > 40) {
            txtNhanXet1.setText("Ban co the trang beo phi do III, chi so BMI cao, bao!");
            txtNhanXet2.setText("Canh cao: Nen tap the duc va can giam can gap! ");
        }
        txtBMI.setText(String.valueOf(bmi));
    }
    public double BMI() {
        double bmi, cc, cn;
        cc = Double.parseDouble(edtChieuCao.getText().toString()) / 100;
        cn = Double.parseDouble(edtCanNang.getText().toString());
        return bmi = cn / (cc * cc);
    }
}

