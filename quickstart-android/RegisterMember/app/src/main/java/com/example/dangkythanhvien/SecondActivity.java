package com.example.dangkythanhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txtHello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtHello = (TextView) findViewById(R.id.txtHello);
        String name = "";

        UserModel model = new UserModel();

        model = (UserModel) getIntent().getSerializableExtra("fullName");

        StringBuilder sb = new StringBuilder();
        sb.append("\nho va ten: " + model.getFullName());
        sb.append("\nUsername: " + model.getUserName());
        sb.append("\nPassword: " + model.getPassWord());
        sb.append("\nNgay Sinh: " + model.getBirthDay());
        sb.append("\nGioi Tinh: " + model.getSex());
        sb.append("\nQuoc Tich: " + model.getCountry());

        txtHello.setText(sb.toString());
    }


}