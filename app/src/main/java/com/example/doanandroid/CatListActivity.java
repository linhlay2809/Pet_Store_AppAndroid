package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CatListActivity extends AppCompatActivity {

    ListView list;
    String ten[] = {"Mèo Anh", "Mèo Ba Tư", "Mèo Munchkin","IMOGEN Nhập Nga – HELEN"};
    int img[] = {R.drawable.meo_anh,R.drawable.meo_ba_tu,R.drawable.munchkin,R.drawable.meo_imogen};
    String gia[] = {"15.000.000đ","12.000.000đ","18.000.000đ","16.000.000đ"};
    String chitiet[] = {"Mèo Anh lông ngắn hay còn được gọi tắt là mèo Aln. Có nguồn gốc từ vương quốc Anh với tên gọi quốc tế là British Shorthair."
            ,"Mèo Ba Tư là một trong những giống mèo cảnh được đông đảo người yêu mèo yêu mến, lựa chọn làm thú cưng trong gia đình."
            ,"Munchkin được mệnh danh là những chú mèo chân ngắn lùn nhất trong thế giới các loài mèo. Munchkin có nguồn gốc bắt nguồn từ Hoa Kỳ (Mỹ)."
            ,"Helen đực 10 tháng tuổi, thuộc giống mèo Imogen  Khỏe mạnh, đã được tẩy giun và tiêm 1 mũi vaccine phòng bệnh cho bé trước khi về nhà mới"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);
        setTitle("CAT");

        list = (ListView)findViewById(R.id.listview_catlist);
        MyAdapter myAdapter = new MyAdapter(this, ten, gia, img, chitiet);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(CatListActivity.this, PetDetailActivity.class);
                intent.putExtra("name", ten[position]);
                intent.putExtra("image", img[position]);
                intent.putExtra("gia", gia[position]);
                intent.putExtra("chitiet", chitiet[position]);
                startActivity(intent);

            }
        });
    }
    class MyAdapter extends ArrayAdapter<String> {
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
        menuInflater.inflate(R.menu.cart,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_cart:
            {
                overridePendingTransition(0, 0);
                MainActivity.instance.finish();
                Intent intent = new Intent(CatListActivity.this, CartFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}