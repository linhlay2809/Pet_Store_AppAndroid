package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.model.Pet;
import com.example.doanandroid.model.Pet1;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchFragment extends AppCompatActivity {
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    ListView list;
    String ten[] = {"Mèo Anh", "Mèo Ba Tư", "Mèo Munchkin","Alaska", "Beagle", "Corgi", "Phốc Sóc", "Bull","Smartheart treat shiny & healthy", "Vegebrand Meat Beef Bone Large", "Sữa bột Dr.Kyan Precaten", "MEC Wild Taste Limited", "Xúc xích khô Tellme", "Gà sấy khô Tellme", "Thức ăn hạt Iskhan", "ROYAL CANIN MEDIUM PUPPY", "NATURAL CORE","Lược chải lông có nút bấm Bobo", "Máy mài móng Codos CP3300", "Sữa tắm cho mèo Joyce & Dolls ", "Cổ dù thêu 7 màu cỡ vừa", "Chuông cho chó mèo nhiều sắc màu", "Nhà cây cho mèo MS:091", "Dụng cụ lăn lông chó mèo", "Vòng cổ mạ inox 0.4×65 cm", "Ferplast Ergofluo"};
    int img[] = {R.drawable.meo_anh,R.drawable.meo_ba_tu,R.drawable.munchkin,R.drawable.alaska,R.drawable.beagle,R.drawable.corgi,R.drawable.phoc_soc,R.drawable.bull,R.drawable.food1,R.drawable.food2,R.drawable.food3,R.drawable.food4,R.drawable.food5,R.drawable.food6,R.drawable.food7,R.drawable.food8,R.drawable.food9,R.drawable.more1,R.drawable.more2,R.drawable.more3,R.drawable.more4,R.drawable.more5,R.drawable.more6,R.drawable.more7,R.drawable.more8,R.drawable.more9};
    String gia[] = {"15.000.000đ","12.000.000đ","18.000.000đ","13.000.000đ","9.500.000đ","25.000.000đ","9.500.000đ","13.000.000đ","90.000đ","80.000đ","45.000đ","210.000đ","90.000đ","80.000đ","120.000đ","190.000đ","250.000đ","140.000đ","520.000đ","120.000đ","60.000đ","10.000đ - 50.000đ","3.000.000đ","45.000đ","45.000đ","514.000đ"};
    String chitiet[] = {"Mèo Anh lông ngắn hay còn được gọi tắt là mèo Aln. Có nguồn gốc từ vương quốc Anh với tên gọi quốc tế là British Shorthair."
            ,"Mèo Ba Tư là một trong những giống mèo cảnh được đông đảo người yêu mèo yêu mến, lựa chọn làm thú cưng trong gia đình."
            ,"Munchkin được mệnh danh là những chú mèo chân ngắn lùn nhất trong thế giới các loài mèo. Munchkin có nguồn gốc bắt nguồn từ Hoa Kỳ (Mỹ)."
            ,"Chó Alaska đang là loại chó được ưa chuộng nhất tại Việt Nam hiện nay. Với ngoại hình oai vệ, bộ lông tuyệt đẹp, rất tình cảm và thân thiện."
            ,"Nó là giống chó săn nhỏ được lai tạo giữa chó săn thỏ Talbot và giống chó Anh bản địa."
            ,"Corgi nổi tiếng thế giới là cún cưng của Nữ hoàng Anh. Có nguồn gốc là giống chó chăn cừu ở xứ Wales."
            ,"Chó Pomeranian có nguồn gốc từ con German spitzen. Đây là con chó nhỏ nhất của gia đình chó kéo xe."
            ,"Tổ tiên của con chó Bulldog là con Molussus, tên của một con chó chiến đấu từ một bộ tộc cổ Moloosi của Hy Lạp."
            ,"Bánh xương được sử dụng để thưởng cho cún nhằm khích lệ chú cún của bạn được năng động và thông minh hơn."
            ,"Xương cho chó vị thịt bò Vegebrand Meat Beef Bone Large là loại thức ăn dinh dưỡng dành cho mọi kích cỡ cùng lứa tuổi chó cưng."
            ,"Dr. Kyan cung cấp không chỉ các sản phẩm tốt mà chất lượng vượt trội. Cũng như đề cao việc cung cấp hàm lượng dinh dưỡng cân bằng cho vật nuôi."
            ,"Thức ăn cho mèo MEC Wild Taste được chế biến từ thịt với cá thật. Các nguyên liệu tuyển chọn nghiêm ngặt, đáp ứng các nhu cầu dinh dưỡng cho mèo ở mọi lứa tuổi."
            ,"Được làm hoàn toàn từ thủ công, không chứa hóa chất và sấy khô ở nhiệt độ thích hợp. Giúp sản phẩm được giữ nguyên dưỡng chất."
            ,"Làm hoàn toàn từ thủ công, không sử dụng hóa chất và sấy khô ở nhiệt độ thích hợp giúp giữ nguyên dưỡng chất của sản phẩm."
            ,"Iskhan là dòng thức ăn hạt dành cho thú cưng mới. Với công thức vượt trội không chứa ngũ cốc, chất bảo quản hay sản phẩm làm thay đổi gen."
            ,"ROYAL CANIN MEDIUM PUPPY sẽ giúp nó duy trì khả năng miễn dịch tự nhiên tốt hơn, đồng thời cung cấp nguồn năng lượng cân bằng và giúp duy trì cân nặng khỏe mạnh."
            ,"Thức ăn hữu cơ Natural Core Eco7 cho chó có dạ dày nhạy cảm được chế biến từ thịt cừu và các loại rau củ hạt được chứng nhận hữu cơ ECOCERT."
            ,"Lược chải lông chó mèo có nút bấm Bobo phù hợp với tất cả các giống chó mèo. Bao gồm cả các giống chó mèo nhỏ và lớn như Poodle, Phốc sóc, Samoyel, Alaska…"
            ,"Máy mài móng cho chó mèo Codos CP3300 là sản phẩm không thể thiếu dành cho thú cưng sợ cắt móng hay chủ nhân chưa tự tin trong vấn đề cắt móng cho chúng. "
            ,"Sữa tắm cho mèo Joyce & Dolls 102 Anti-bacterium Formula hạn chế sự sinh sản của vi khuẩn bề mặt da với làm dịu phần da ngứa trên cơ thể."
            ,"Chất liệu may cổ dù được may từ chất liệu vải dù dày dặn. Bền chắc và mềm mại luôn tạo cảm giác êm ái trên cổ thú cưng. "
            ,"Chuông cho chó mèo nhiều sắc màu hay còn được gọi là lục lạc. Với đầy đủ các sắc màu và kích cỡ khác nhau. Phù hợp với tất cả các loại giống chó mèo."
            ,"Nhà cây hay tên tiếng anh cat tree được hiểu là  một ngôi nhà một sân chơi,khu giải trí thu nhỏ dành  cho Mèo  ."
            ,"Lăn lông chó mèo dính vào quần áo BIOLINE Pet Roller là loại dụng cụ giúp loại bỏ lông thú cưng trên quần áo hay bất cứ chỗ nào."
            ,"Chiếc vòng cổ mạ inox dạng dây xích được thiết kế đẹp mắt, luôn là sự lựa chọn hàng đầu của các chủ nhân dành cho những chú chó cỡ trung và lớn."
            ,"Yếm Ferplast Ergofluo là dòng sản phẩm với thiết kế độc quyền. Sở hữu hệ thống điều chỉnh chi tiết, hệ thống khóa mới Ergofluo 1 – 2, vừa vặn hoàn hảo."};
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fragment);
        setTitle("SEARCH");


        EditText et_search = (EditText)findViewById(R.id.etSearch);
        // Add Text Change Listener to EditText

        list = (ListView)findViewById(R.id.listview_search_fragment);
        myAdapter = new MyAdapter(this, ten, gia, img, chitiet);
        list.setAdapter(myAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SearchFragment.this, PetDetailActivity.class);
                intent.putExtra("name", ten[position]);
                intent.putExtra("image", img[position]);
                intent.putExtra("gia", gia[position]);
                intent.putExtra("chitiet", chitiet[position]);
                startActivity(intent);

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigration);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
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
                Intent intent = new Intent(SearchFragment.this, CartFragment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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