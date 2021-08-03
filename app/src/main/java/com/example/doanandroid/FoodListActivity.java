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

public class FoodListActivity extends AppCompatActivity {

    ListView list;
    String ten[] = {"Smartheart treat shiny & healthy", "Vegebrand Meat Beef Bone Large", "Sữa bột Dr.Kyan Precaten", "MEC Wild Taste Limited", "Xúc xích khô Tellme", "Gà sấy khô Tellme", "Thức ăn hạt Iskhan", "ROYAL CANIN MEDIUM PUPPY", "NATURAL CORE"};
    int img[] = {R.drawable.food1,R.drawable.food2,R.drawable.food3,R.drawable.food4,R.drawable.food5,R.drawable.food6,R.drawable.food7,R.drawable.food8,R.drawable.food9};
    String gia[] = {"90.000đ","80.000đ","45.000đ","210.000đ","90.000đ","80.000đ","120.000đ","190.000đ","250.000đ"};
    String chitiet[] = {"Bánh xương được sử dụng để thưởng cho cún nhằm khích lệ chú cún của bạn được năng động và thông minh hơn."
            ,"Xương cho chó vị thịt bò Vegebrand Meat Beef Bone Large là loại thức ăn dinh dưỡng dành cho mọi kích cỡ cùng lứa tuổi chó cưng."
            ,"Dr. Kyan cung cấp không chỉ các sản phẩm tốt mà chất lượng vượt trội. Cũng như đề cao việc cung cấp hàm lượng dinh dưỡng cân bằng cho vật nuôi."
            ,"Thức ăn cho mèo MEC Wild Taste được chế biến từ thịt với cá thật. Các nguyên liệu tuyển chọn nghiêm ngặt, đáp ứng các nhu cầu dinh dưỡng cho mèo ở mọi lứa tuổi."
            ,"Được làm hoàn toàn từ thủ công, không chứa hóa chất và sấy khô ở nhiệt độ thích hợp. Giúp sản phẩm được giữ nguyên dưỡng chất."
            ,"Làm hoàn toàn từ thủ công, không sử dụng hóa chất và sấy khô ở nhiệt độ thích hợp giúp giữ nguyên dưỡng chất của sản phẩm."
            ,"Iskhan là dòng thức ăn hạt dành cho thú cưng mới. Với công thức vượt trội không chứa ngũ cốc, chất bảo quản hay sản phẩm làm thay đổi gen."
            ,"ROYAL CANIN MEDIUM PUPPY sẽ giúp nó duy trì khả năng miễn dịch tự nhiên tốt hơn, đồng thời cung cấp nguồn năng lượng cân bằng và giúp duy trì cân nặng khỏe mạnh."
            ,"Thức ăn hữu cơ Natural Core Eco7 cho chó có dạ dày nhạy cảm được chế biến từ thịt cừu và các loại rau củ hạt được chứng nhận hữu cơ ECOCERT."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        setTitle("FOOD");

        list = (ListView)findViewById(R.id.listview_foodlist);
        MyAdapter myAdapter = new MyAdapter(this, ten, gia, img, chitiet);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(FoodListActivity.this, PetDetailActivity.class);
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
                Intent intent = new Intent(FoodListActivity.this, CartFragment.class);
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