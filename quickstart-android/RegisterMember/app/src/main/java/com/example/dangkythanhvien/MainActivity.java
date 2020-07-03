package com.example.dangkythanhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private EditText editFullName,editUsername,editPassword,editBirthday;
    private RadioButton radioBoy, radioGirl;
    private Spinner spinerCountry;
    private Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNull(editPassword,editUsername,editBirthday,editFullName)){
                    if(!checkUserName(editUsername)){
                        Toast.makeText(getApplicationContext(),"Username không hợp lệ",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(!checkPassword(editPassword)){
                        Toast.makeText(getApplicationContext(),"Password không hợp lệ",
                                Toast.LENGTH_LONG).show();
                    }else if(checkUserName(editUsername) && checkPassword(editPassword)){
                        Toast.makeText(getApplicationContext(),"Đăng Ký Thành Công",
                                Toast.LENGTH_LONG).show();
                        UserModel userModel = new UserModel();
                        String fullName = editFullName.getText().toString();
                        String username = editUsername.getText().toString();
                        String password = editPassword.getText().toString();
                        String birthday = editBirthday.getText().toString();
                        String sex;
                        if(radioBoy.isChecked()){
                            sex = "Nam";
                        }else{
                            sex = "Nu";
                        }
                        String country = spinerCountry.getSelectedItem().toString();

                        UserModel model = new UserModel(fullName,username,password,birthday,sex,country);

                        Intent secondActivate = new Intent(MainActivity.this,SecondActivity.class);
                        secondActivate.putExtra("fullName", model);
                        startActivity(secondActivate);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Không được để dữ liệu trống",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void init(){
        editFullName = (EditText) findViewById(R.id.editFullName);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editBirthday = (EditText) findViewById(R.id.editBirthday);

        radioBoy = (RadioButton) findViewById(R.id.radioBoy);
        radioGirl = (RadioButton) findViewById(R.id.radioGirl);

        spinerCountry = (Spinner) findViewById(R.id.spinerCountry);

        btnCreate = (Button) findViewById(R.id.btnCreate);

    }

    private boolean checkNull(EditText... editText){
        boolean isNull = false;
        for(EditText edit : editText){
            String s = edit.getText().toString();
            if(TextUtils.isEmpty(s)){
                isNull = true;
                break;
            }
        }
        return !isNull;

    }

    private boolean checkUserName(EditText editText){
        String text = editText.getText().toString();
        if(text.matches("[a-zA-Z0-9]*")
        &&(editText.getText().length() <= 128 && editText.getText().length() > 0)
        ){
            return true;
        }
        else{
            return false;
        }
    }

//    private boolean checkSpace(EditText editText){
//        if(editText.getText().toString().matches("[\\s]")){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    private boolean checkUserTextLength(EditText editText){
//        if(editText.getText().length() <= 128 && editText.getText().length() > 0){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

    private boolean checkPassword(EditText editText){
        String text = editText.getText().toString();
        if(text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
            return true;
        }
        else{
            return false;
        }
    }
}