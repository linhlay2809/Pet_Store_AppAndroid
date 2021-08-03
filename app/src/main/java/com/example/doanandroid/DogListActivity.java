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

public class DogListActivity extends AppCompatActivity {

    ListView list;
    String ten[] = {"Alaska", "Beagle", "Corgi", "Phốc Sóc", "Bull"};
    int img[] = {R.drawable.alaska,R.drawable.beagle,R.drawable.corgi,R.drawable.phoc_soc,R.drawable.bull};
    String gia[] = {"13.000.000đ","9.500.000đ","25.000.000đ","9.500.000đ","13.000.000đ"};
    String chitiet[] = {"Chó Alaska đang là loại chó được ưa chuộng nhất tại Việt Nam hiện nay. Với ngoại hình oai vệ, bộ lông tuyệt đẹp, rất tình cảm và thân thiện."
            ,"Nó là giống chó săn nhỏ được lai tạo giữa chó săn thỏ Talbot và giống chó Anh bản địa."
            ,"Corgi nổi tiếng thế giới là cún cưng của Nữ hoàng Anh. Có nguồn gốc là giống chó chăn cừu ở xứ Wales."
            ,"Chó Pomeranian có nguồn gốc từ con German spitzen. Đây là con chó nhỏ nhất của gia đình chó kéo xe."
            ,"Tổ tiên của con chó Bulldog là con Molussus, tên của một con chó chiến đấu từ một bộ tộc cổ Moloosi của Hy Lạp."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);
        setTitle("DOG");

        list = (ListView)findViewById(R.id.listview_doglist);
        MyAdapter myAdapter = new MyAdapter(this, ten, gia, img, chitiet);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DogListActivity.this, PetDetailActivity.class);
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
                Intent intent = new Intent(DogListActivity.this, CartFragment.class);
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