package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.model.Pet;


public class PetDetailActivity extends AppCompatActivity {
    Button btn_addCart, btn_themSoLuong, btn_botSoLuong;
    TextView tv_name, tv_gia, tv_soluong, tv_chitiet;
    ImageView img_hinh;
    DatabaseHelper db;
    int soLuong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        setTitle("Detail");

        img_hinh =(ImageView)findViewById(R.id.img_hinh_detail);
        tv_gia = (TextView)findViewById(R.id.tv_gia_detail);
        tv_name = (TextView)findViewById(R.id.tv_name_detail);
        tv_soluong = (TextView)findViewById(R.id.textview_soluong);
        tv_chitiet = (TextView)findViewById(R.id.tv_chitiet_detail);
        btn_themSoLuong = (Button)findViewById(R.id.btn_themsoluong);
        btn_botSoLuong = (Button)findViewById(R.id.btn_botsoluong);
        btn_addCart = (Button)findViewById(R.id.btn_add_cart);
        db = new DatabaseHelper(this);

        String name = getIntent().getStringExtra("name");
        int image =getIntent().getIntExtra("image",0);
        String gia = getIntent().getStringExtra("gia");
        String chitiet = getIntent().getStringExtra("chitiet");
        tv_name.setText(name);
        img_hinh.setImageResource(image);
        tv_gia.setText(gia);
        tv_chitiet.setText(chitiet);

        btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = tv_name.getText().toString();
                Pet pet = new Pet();
                Boolean checkCart= db.checkCart(name);
                if (checkCart==true)
                {
                    pet.setGia(tv_gia.getText().toString());
                    pet.setSoLuong(tv_soluong.getText().toString());
                    if (db.updateAuthor(pet,name)>0){
                        Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Thêm giỏ hàng không thành công",Toast.LENGTH_SHORT).show();
                }
                else{

                    pet.setName(name);
                    pet.setGia(tv_gia.getText().toString());
                    pet.setSoLuong(tv_soluong.getText().toString());
                    if (db.insertAuthor(pet)>0){
                        Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Thêm giỏ hàng không thành công",Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        btn_themSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                displaySoLuong();
            }


        });
        btn_botSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soLuong == 0) {
                    Toast.makeText(PetDetailActivity.this, "Không thể bé hơn 0",Toast.LENGTH_SHORT).show();
                }else {
                    soLuong--;
                    displaySoLuong();
                }
            }
        });

    }


    private void displaySoLuong() {
        tv_soluong.setText(String.valueOf(soLuong));
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}