package com.e.thuethunhapcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etThuNhap, etSoNguoiPhuThuoc;
    Spinner spinnerThang, spinnerNam;
    TextView tienTinhThue, tienThueThuNhap;
    Button btnTinhToan;
    DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNull()){
                    tinhThuNhapTinhThue();
                    tinhThueTNCN();
                }
                else
                    Toast.makeText(getApplicationContext(), "Bạn phải nhập chính xác thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /////// Khởi tạo các thuộc tính
    private void init(){
        etThuNhap = findViewById(R.id.etThuNhap);
        etSoNguoiPhuThuoc = findViewById(R.id.etSoNguoiPhuThuoc);
        spinnerThang = findViewById(R.id.spinnerThang);
        spinnerNam = findViewById(R.id.spinnerNam);
        tienTinhThue = findViewById(R.id.tienTinhThue);
        tienThueThuNhap = findViewById(R.id.tienThueThuNhap);
        btnTinhToan = findViewById(R.id.btnTinhToan);

        formatter  = new DecimalFormat("###");
        formatter.setGroupingUsed(true);
        formatter.setGroupingSize(3);
    }

    /////// Tính thu nhập tính thuế
    private void tinhThuNhapTinhThue(){
        int thuNhapChiuThue = Integer.parseInt(etThuNhap.getText().toString());
        int soNguoiPhuThuoc = Integer.parseInt(etSoNguoiPhuThuoc.getText().toString());

        int thuNhapTinhThue = thuNhapChiuThue - tinhSoTienGiamCaNhan() -tinhSoTienGiamPhuThuoc(soNguoiPhuThuoc);
        if (thuNhapTinhThue < 0)
            thuNhapTinhThue = 0;

        formatter.format(thuNhapTinhThue);
        tienTinhThue.setText(String.valueOf(thuNhapTinhThue));
    }

    private int tinhSoTienGiamCaNhan(){
        if (checkThangNam())
            return 11000000;
        else
            return 9000000;
    }

    private int tinhSoTienGiamPhuThuoc(int soNguoiPhuThuoc){
        if (checkThangNam())
            return 4400000 * soNguoiPhuThuoc;
        else
            return 3600000 * soNguoiPhuThuoc;
    }
    ///////////////////////////////

    ////// Tính thuế thu nhập cá nhân
    private void tinhThueTNCN(){
        int thuNhapTinhThue = Integer.parseInt(tienTinhThue.getText().toString());
        int bacThue = tinhBacThue(thuNhapTinhThue);
        int thuNhapCaNhan = 0;
        switch (bacThue) {
            case 1:
                thuNhapCaNhan = thuNhapTinhThue * 5 / 100;
                break;
            case 2:
                thuNhapCaNhan = thuNhapTinhThue * 10 / 100 - 250000;
                break;
            case 3:
                thuNhapCaNhan = thuNhapTinhThue * 15 / 100 - 750000;
                break;
            case 4:
                thuNhapCaNhan = thuNhapTinhThue * 20 / 100 - 1650000;
                break;
            case 5:
                thuNhapCaNhan = thuNhapTinhThue * 25 / 100 - 3250000;
                break;
            case 6:
                thuNhapCaNhan = thuNhapTinhThue * 30 / 100 - 5850000;
                break;
            case 7:
                thuNhapCaNhan = thuNhapTinhThue * 35 / 100 - 9850000;
                break;
        }

        formatter.format(thuNhapCaNhan);
        tienThueThuNhap.setText(String.valueOf(thuNhapCaNhan));
    }

    private int tinhBacThue(int thuNhapTinhThue){
        if (thuNhapTinhThue <= 5000000)
            return 1;
        else if (thuNhapTinhThue <= 10000000)
            return 2;
        else if (thuNhapTinhThue <= 18000000)
            return 3;
        else if (thuNhapTinhThue <= 32000000)
            return 4;
        else if (thuNhapTinhThue <= 52000000)
            return 5;
        else if (thuNhapTinhThue <= 80000000)
            return 6;
        else
            return 7;
    }
    ///////////////////////////////

    private boolean checkThangNam() {
        String thang = spinnerThang.getSelectedItem().toString();
        String nam = spinnerNam.getSelectedItem().toString();

        if (Integer.parseInt(nam) >= 2020){
            if(Integer.parseInt(thang) >= 7)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    private boolean checkNull(){
        String match = "\\d+";
        String thuNhap = etThuNhap.getText().toString();
        String soNguoiPhuThuoc = etSoNguoiPhuThuoc.getText().toString();

        if (thuNhap.matches(match) && soNguoiPhuThuoc.matches(match))
            return true;
        else
            return false;
    }
}