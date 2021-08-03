package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.model.Pet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    public static MainActivity instance = null;
    ImageButton btn_cho, btn_meo, btn_khac, btn_thucan;
    ListView list;
    String ten[] = {"Alaska", "Beagle", "Corgi", "Phốc Sóc", "Bull", "Mèo Anh", "Mèo Ba Tư", "Mèo Munchkin"};
    int img[] = {R.drawable.alaska,R.drawable.beagle,R.drawable.corgi,R.drawable.phoc_soc,R.drawable.bull,R.drawable.meo_anh,R.drawable.meo_ba_tu,R.drawable.munchkin};
    String gia[] = {"13.000.000đ","9.500.000đ","25.000.000đ","9.500.000đ","13.000.000đ","15.000.000đ","12.000.000đ","18.000.000đ"};
    String chitiet[] = {"Chó Alaska đang là loại chó được ưa chuộng nhất tại Việt Nam hiện nay. Với ngoại hình oai vệ, bộ lông tuyệt đẹp, rất tình cảm và thân thiện."
            ,"Nó là giống chó săn nhỏ được lai tạo giữa chó săn thỏ Talbot và giống chó Anh bản địa."
            ,"Corgi nổi tiếng thế giới là cún cưng của Nữ hoàng Anh. Có nguồn gốc là giống chó chăn cừu ở xứ Wales."
            ,"Chó Pomeranian có nguồn gốc từ con German spitzen. Đây là con chó nhỏ nhất của gia đình chó kéo xe."
            ,"Tổ tiên của con chó Bulldog là con Molussus, tên của một con chó chiến đấu từ một bộ tộc cổ Moloosi của Hy Lạp."
            ,"Mèo Anh lông ngắn hay còn được gọi tắt là mèo Aln. Có nguồn gốc từ vương quốc Anh với tên gọi quốc tế là British Shorthair."
            ,"Mèo Ba Tư là một trong những giống mèo cảnh được đông đảo người yêu mèo yêu mến, lựa chọn làm thú cưng trong gia đình."
            ,"Munchkin được mệnh danh là những chú mèo chân ngắn lùn nhất trong thế giới các loài mèo. Munchkin có nguồn gốc bắt nguồn từ Hoa Kỳ (Mỹ)."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PET SHOP");
        btn_cho = (ImageButton) findViewById(R.id.imgbtnCho);
        btn_meo = (ImageButton) findViewById(R.id.imgbtnMeo);
        btn_thucan = (ImageButton) findViewById(R.id.imgbtnThucAn);
        btn_khac = (ImageButton) findViewById(R.id.imgbtnKhac);
        instance = this;

        list = (ListView)findViewById(R.id.listviewhome);
        MyAdapter myAdapter = new MyAdapter(this, ten, gia, img, chitiet);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, PetDetailActivity.class);
                intent.putExtra("name", ten[position]);
                intent.putExtra("image", img[position]);
                intent.putExtra("gia", gia[position]);
                intent.putExtra("chitiet", chitiet[position]);
                startActivity(intent);

            }
        });
        btn_cho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DogListActivity.class);
                startActivity(intent);
            }
        });
        btn_meo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CatListActivity.class);
                startActivity(intent);
            }
        });
        btn_thucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class);
                startActivity(intent);
            }
        });btn_khac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoreListActivity.class);
                startActivity(intent);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigration);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
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
                        startActivity(new Intent(getApplicationContext(),MoreFragment.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myten[];
        String mygia[];
        int[] img;
        String mychitiet[];

        MyAdapter(Context c, String[] ten, String[] gia, int[] img, String[] mychitiet){
            super(c,R.layout.pet_row_item, R.id.textview_name_list, ten);
            this.context = c;
            this.myten = ten;
            this.mygia = gia;
            this.img = img;
            this.mychitiet = mychitiet;
        }
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.pet_row_item, parent, false);
            ImageView image = row.findViewById(R.id.imageView_list);
            TextView tenpet = row.findViewById(R.id.textview_name_list);
            TextView giapet = row.findViewById(R.id.textview_gia_list);
            TextView chitietpet = row.findViewById(R.id.textview_chitiet_list);
            image.setImageResource(img[position]);
            tenpet.setText(ten[position]);
            giapet.setText(gia[position]);
            chitietpet.setText(chitiet[position]);
            return row;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home,menu);
        menuInflater.inflate(R.menu.cart,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
            {
                Intent intent = new Intent(MainActivity.this, CartFragment.class);
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
            return true;
            case R.id.menu_lienhe:
            {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
            return true;
            case R.id.thoat:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void finish() {
        super.finish();
        instance = null;
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