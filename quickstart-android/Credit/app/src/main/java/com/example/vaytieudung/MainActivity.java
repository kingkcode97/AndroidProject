package com.example.vaytieudung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private EditText editThuNhap,editChiPhi,editTienVay;
    private Spinner editLaiSuat;
    private RadioButton radio12Thang, radio18Thang, radio24Thang, radio30Thang,radio36Thang,
            radio42Thang,radio48Thang,radio56Thang,radio60Thang;
    private Button btnTinh;
    private TextView txtKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNulll(editTienVay,editChiPhi,editThuNhap)){
                    double tnThang = Double.parseDouble(editThuNhap.getText().toString());
                    double tienVay = Double.parseDouble(editTienVay.getText().toString());
                    double chiPhi = Double.parseDouble(editChiPhi.getText().toString());

                    if(tienVay >= 20000000 && tienVay <= ((tnThang - chiPhi)*10)){
                        tinhLai(tienVay);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Khong thoa dieu kien vay",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Không được để trống",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void init(){
        editThuNhap = (EditText)findViewById(R.id.editThuNhap);
        editChiPhi = (EditText)findViewById(R.id.editChiPhi);
        editTienVay = (EditText)findViewById(R.id.editTienVay);
        editLaiSuat = (Spinner)findViewById(R.id.editLaiSuat);

        radio12Thang = (RadioButton)findViewById(R.id.edit12thang);
        radio18Thang = (RadioButton)findViewById(R.id.edit18thang);
        radio24Thang = (RadioButton)findViewById(R.id.edit24thang);
        radio30Thang = (RadioButton)findViewById(R.id.edit30thang);
        radio36Thang = (RadioButton)findViewById(R.id.edit36thang);
        radio42Thang = (RadioButton)findViewById(R.id.edit42thang);
        radio48Thang = (RadioButton)findViewById(R.id.edit48thang);
        radio56Thang = (RadioButton)findViewById(R.id.edit56thang);
        radio60Thang = (RadioButton)findViewById(R.id.edit60thang);

        btnTinh = (Button)findViewById(R.id.btnTinh);

        txtKQ = (TextView)findViewById(R.id.txtResult);
    }

    private void tinhLai(double tienVay)
    {
        String laiSuat = editLaiSuat.getSelectedItem().toString().substring(0,
                editLaiSuat.getSelectedItem().toString().indexOf("%"));
        String tgVay = kiemTraThoiGianVay(radio12Thang,radio18Thang,radio24Thang,radio30Thang,
                radio36Thang,radio42Thang,radio48Thang,radio56Thang,radio60Thang);

        double tienPhaiTra = tienVay * Math.pow((1 + Double.parseDouble(laiSuat)/(12 * 100)), Double.parseDouble(tgVay)) / Double.parseDouble(tgVay);

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        String KQ = decimalFormat.format(tienPhaiTra);

        txtKQ.setText(KQ);
    }
    private boolean checkNulll(EditText... editTexts){
        for(EditText editText: editTexts){
            String s = editText.getText().toString();
            if(TextUtils.isEmpty(s)){
                return false;
            }
        }
        return true;

    }
    private String kiemTraThoiGianVay(RadioButton... radioButtons){
        String s = null;
        for(RadioButton radio:radioButtons){
            if(radio.isChecked()){
                String radioText = radio.getText().toString();
                s = radioText.substring(0,radioText.indexOf(" "));
                break;
            }
        }

        return s;
    }

}