package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText et_userName, et_password;
    Button btn_login, btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Pet Store");
        et_userName = (EditText)findViewById(R.id.etusername);
        et_password = (EditText)findViewById(R.id.etpassword);
        db = new DatabaseHelper(this);
        btn_login = (Button)findViewById(R.id.btnLogin);
        btn_register = (Button)findViewById(R.id.btnRegister);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_userName.getText().toString();
                String pass = et_password.getText().toString();
                Boolean checkLogin = db.checklogin(userName,pass);
                if (checkLogin==true) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    et_password.setText("");
                    et_userName.requestFocus();
                }
            }
        });
    }
}