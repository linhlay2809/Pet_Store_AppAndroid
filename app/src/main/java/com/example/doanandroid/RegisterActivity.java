package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText et_userName1, et_pass, et_confirmPass;
    Button btn_register, btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Đăng Ký");
        et_userName1 = (EditText)findViewById(R.id.etusername1);
        et_pass = (EditText)findViewById(R.id.etpassword1);
        et_confirmPass = (EditText)findViewById(R.id.etconfirmPassword);
        db = new DatabaseHelper(this);
        btn_back = (Button)findViewById(R.id.btnquaylai) ;
        btn_register = (Button)findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_userName1.getText().toString();
                String pass = et_pass.getText().toString();
                String confirmPass = et_confirmPass.getText().toString();
                if (userName.equals("") || pass.equals("") || confirmPass.equals("")){
                    Toast.makeText(getApplicationContext(),"Phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(confirmPass)){
                        Boolean checkUser = db.checkName(userName);
                        if (checkUser==true){
                            Boolean insert = db.insert(userName,pass);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Đã đăng ký thành công",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Trùng tên, hãy nhập tên khác",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}