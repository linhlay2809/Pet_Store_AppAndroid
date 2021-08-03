package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoreFragment extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    Button btn_logout, btn_thongTin, btn_troGiup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_fragment);
        setTitle("MORE");
        btn_logout = (Button)findViewById(R.id.btn_dangxuat);
        btn_thongTin = (Button)findViewById(R.id.btn_thongtin);
        btn_troGiup = (Button)findViewById(R.id.btn_trogiup);
        btn_troGiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this, HelpActivity.class);
                startActivity(intent);
            }
        });
        btn_thongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(MoreFragment.this);
                b.setTitle("Logout");
                b.setMessage("Bạn có chắc muốn đăng xuất ?");
                b.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                        Intent intent = new Intent(MoreFragment.this, LoginActivity.class);
                        startActivity(intent);
                    }});
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }

                });

                b.create().show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigration);
        bottomNavigationView.setSelectedItemId(R.id.nav_more);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),SearchFragment.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(),CartFragment.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.nav_more:
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else {
            Toast toast = Toast.makeText(getBaseContext(), "Nhấn back lần nữa để thoát", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }

        mBackPressed = System.currentTimeMillis();
    }
}