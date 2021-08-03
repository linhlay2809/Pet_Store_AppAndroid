package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanandroid.Adapter.PetAdapter;
import com.example.doanandroid.model.Pet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CartFragment extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    DatabaseHelper db;
    ArrayList<Pet> petList = new ArrayList<>();
    PetAdapter petAdapter;
    Button btn_clearCart, btn_thanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_fragment);
        db = new DatabaseHelper(this);
        setTitle("CART");
        ListView listViewCart = (ListView)findViewById(R.id.listview_cartfragment);
        btn_clearCart = (Button)findViewById(R.id.btn_clearcart);
        if (db.checkExisst()==true){
            btn_clearCart.setVisibility(View.INVISIBLE);
        }
        else
            btn_clearCart.setVisibility(View.VISIBLE);
        btn_thanhToan = (Button)findViewById(R.id.btnThanhToan);

        petList = db.getPet();
        petAdapter = new PetAdapter(getApplicationContext(), petList);
        listViewCart.setAdapter(petAdapter);

        listViewCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pet pet = petList.get(position);
                String name = pet.getName();

                if (db.deleteAuthor(name)>0){
                    Toast.makeText(getApplicationContext(), "Đã xóa mặt hàng "+name,Toast.LENGTH_SHORT).show();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });

        btn_clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAllAuthor();
                Toast.makeText(getApplicationContext(), "Đã xóa cart",Toast.LENGTH_SHORT).show();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                finish();

            }
        });

        btn_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.checkExisst()==true){
                    Toast.makeText(getApplicationContext(),"Không có món hàng nào để thanh toán",Toast.LENGTH_SHORT).show();
                }
                else {
                    db.deleteAllAuthor();
                    Toast.makeText(getApplicationContext(),"Đã thanh toán thành công",Toast.LENGTH_SHORT).show();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                    finish();
                }


            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigration);
        bottomNavigationView.setSelectedItemId(R.id.nav_cart);
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
                        return true;
                    case R.id.nav_more:
                        startActivity(new Intent(getApplicationContext(),MoreFragment.class));
                        overridePendingTransition(0,0);
                        finish();
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