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

public class MoreListActivity extends AppCompatActivity {

    ListView list;
    String ten[] = {"Lược chải lông có nút bấm Bobo", "Máy mài móng Codos CP3300", "Sữa tắm cho mèo Joyce & Dolls ", "Cổ dù thêu 7 màu cỡ vừa", "Chuông cho chó mèo nhiều sắc màu", "Nhà cây cho mèo MS:091", "Dụng cụ lăn lông chó mèo", "Vòng cổ mạ inox 0.4×65 cm", "Ferplast Ergofluo"};
    int img[] = {R.drawable.more1,R.drawable.more2,R.drawable.more3,R.drawable.more4,R.drawable.more5,R.drawable.more6,R.drawable.more7,R.drawable.more8,R.drawable.more9};
    String gia[] = {"140.000đ","520.000đ","120.000đ","60.000đ","10.000đ - 50.000đ","3.000.000đ","45.000đ","45.000đ","514.000đ"};
    String chitiet[] = {"Lược chải lông chó mèo có nút bấm Bobo phù hợp với tất cả các giống chó mèo. Bao gồm cả các giống chó mèo nhỏ và lớn như Poodle, Phốc sóc, Samoyel, Alaska…"
            ,"Máy mài móng cho chó mèo Codos CP3300 là sản phẩm không thể thiếu dành cho thú cưng sợ cắt móng hay chủ nhân chưa tự tin trong vấn đề cắt móng cho chúng. "
            ,"Sữa tắm cho mèo Joyce & Dolls 102 Anti-bacterium Formula hạn chế sự sinh sản của vi khuẩn bề mặt da với làm dịu phần da ngứa trên cơ thể."
            ,"Chất liệu may cổ dù được may từ chất liệu vải dù dày dặn. Bền chắc và mềm mại luôn tạo cảm giác êm ái trên cổ thú cưng. "
            ,"Chuông cho chó mèo nhiều sắc màu hay còn được gọi là lục lạc. Với đầy đủ các sắc màu và kích cỡ khác nhau. Phù hợp với tất cả các loại giống chó mèo."
            ,"Nhà cây hay tên tiếng anh cat tree được hiểu là  một ngôi nhà một sân chơi,khu giải trí thu nhỏ dành  cho Mèo  ."
            ,"Lăn lông chó mèo dính vào quần áo BIOLINE Pet Roller là loại dụng cụ giúp loại bỏ lông thú cưng trên quần áo hay bất cứ chỗ nào."
            ,"Chiếc vòng cổ mạ inox dạng dây xích được thiết kế đẹp mắt, luôn là sự lựa chọn hàng đầu của các chủ nhân dành cho những chú chó cỡ trung và lớn."
            ,"Yếm Ferplast Ergofluo là dòng sản phẩm với thiết kế độc quyền. Sở hữu hệ thống điều chỉnh chi tiết, hệ thống khóa mới Ergofluo 1 – 2, vừa vặn hoàn hảo."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list);
        setTitle("MORE");

        list = (ListView)findViewById(R.id.listview_morelist);
        MyAdapter myAdapter = new MyAdapter(this, ten, gia, img, chitiet);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MoreListActivity.this, PetDetailActivity.class);
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

        MyAdapter(Context c, String[] ten, String[] gia, int[] img, String[] mychitiet) {
            super(c, R.layout.pet_row_item, R.id.textview_name_list, ten);
            this.context = c;
            this.myten = ten;
            this.mygia = gia;
            this.img = img;
            this.mychitiet = mychitiet;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.food_more_pet, parent, false);
            ImageView image = row.findViewById(R.id.imageView_list1);
            TextView tenpet = row.findViewById(R.id.textview_name_list1);
            TextView giapet = row.findViewById(R.id.textview_gia_list1);
            TextView chitietpet = row.findViewById(R.id.textview_chitiet_list1);
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
                Intent intent = new Intent(MoreListActivity.this, CartFragment.class);
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