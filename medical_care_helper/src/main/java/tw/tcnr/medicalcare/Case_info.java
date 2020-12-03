package tw.tcnr.medicalcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Case_info extends AppCompatActivity {
    private Intent intent = new Intent();
    BottomNavigationView bottomNav1, bottomNav2, bottomNav3, bottomNav4;
    private HashMap<String, Object> item;
    private List<Map<String, Object>> mList = new ArrayList<>();  //建立一個可以新增內容的Array
    private GridView b01_gv001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_info);
        bottomNav1 = findViewById(R.id.nav_view1);
        bottomNav2 = findViewById(R.id.nav_view2);
        bottomNav3 = findViewById(R.id.nav_view3);
        bottomNav4 = findViewById(R.id.nav_view4);
        BottomNavigationHelper.removeShiftMode(bottomNav1);
        bottomNav1.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        BottomNavigationHelper.removeShiftMode(bottomNav2);
        bottomNav2.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        BottomNavigationHelper.removeShiftMode(bottomNav3);
        bottomNav3.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        BottomNavigationHelper.removeShiftMode(bottomNav4);
        bottomNav4.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNav1.setOnNavigationItemSelectedListener(navListener);
        bottomNav2.setOnNavigationItemSelectedListener(navListener);
        bottomNav3.setOnNavigationItemSelectedListener(navListener);
        bottomNav4.setOnNavigationItemSelectedListener(navListener);
        bottomNav3.setVisibility(View.GONE);
        bottomNav4.setVisibility(View.GONE);
        setupViewComponent();
    }

    private void setupViewComponent() {
        b01_gv001 = (GridView) findViewById(R.id.b01_gridview01);
//從資料庫取值
        for (int i = 0; i < 2; i++) {
            item = new HashMap<String, Object>();// TODO 要注意物件new在for迴圈內, 不然會出錯變重複
//            String[] fld = recSet.get(i).split("#");
            item.put("img", R.drawable.userconfig);
            item.put("name", "王小明");
//            item.put(TAG_IMAGE, imgArr[i%4]); //之前設定的陣列圖片，可以刪除，記得drawable要清
            mList.add(item);
        }
        //==========設定listView============
        //因為自定義Adapter，圖片網址已經寫在那邊，這裡只要載入文字就好
        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(),
                mList,
                R.layout.case_info_list,
                new String[]{"img", "name"},
                new int[]{R.id.case_info_gv_img, R.id.case_info_gv_name}
        );
        b01_gv001.setAdapter(adapter);//將抓取的資料設定到表格視窗
        b01_gv001.setOnItemClickListener(onClickGridView);//建立表格視窗按鈕監聽
    }

    private final GridView.OnItemClickListener onClickGridView = new GridView.OnItemClickListener() {
        //建立表格視窗按鈕監聽方法
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //方法2,記得上面要宣告new
            Toast.makeText(getApplicationContext(), "已按下索引: " + position, Toast.LENGTH_LONG).show();//測試索引用

            intent.setClass(getApplicationContext(), Case_info_basic.class);
            intent.putExtra("id", Integer.toString(position+1));
            startActivity(intent);
//            intent.setClass(getApplicationContext(), Case_info_basic.class);
            //--------舊方法-------------
//                .putExtra("site", site2)//改採用資料庫，這裡都用不到了
//                .putExtra("brief", brief2)//這裡的key是自己定義, key傳到Ast_Point要相同,注意!!!
//                .putExtra("latitude", latitude)
//                .putExtra("longitude", longitude)
//                .putExtra("phone", phone)
//                .putExtra("class_title", site2);//設定傳遞的title
//                    .putExtra("index", position);
            //傳遞按下的索引值， TODO 之後考慮改為傳遞按下物件的UID，就可以套用搜尋列表功能了
//            startActivity(intent);
        }
    };
    //=================================================================================================================
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.information:
                            bottomNav4.setVisibility(View.VISIBLE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Case_info.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.schedule:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Schedule.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.statiscs:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Inquire.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.care:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Carry_info.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
//=================================================================================================================
                        case R.id.nav_home:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.nav_news:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), News_info.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.nav_book:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.VISIBLE);
                            startActivity(new Intent(getApplicationContext(), Record_main.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.nav_cloud:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Fans.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
//=================================================================================================================
                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(), Record_main.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.bloodpressure:
                            startActivity(new Intent(getApplicationContext(), Blood_pressure.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.bodyweight:
                            startActivity(new Intent(getApplicationContext(), Weight.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.heartbeat:
                            startActivity(new Intent(getApplicationContext(), Heart.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
//=================================================================================================================
                    }
                    return false;
                }
            };

    //=================================================================================================================
//================================ 下面是生命週期 ===========================================
//    1.當Activity準備要產生時，先呼叫onCreate方法。
//    2.Activity產生後（還未出現在手機螢幕上），呼叫onStart方法。
//    3.當Activity出現手機上後，呼叫onResume方法。
//    4.當使用者按下返回鍵結束Activity時， 先呼叫onPause方法。
//    5.當Activity從螢幕上消失時，呼叫onStop方法。
//    6.最後完全結束Activity之前，呼叫onDestroy方法。
    @Override
    protected void onStop() {
        super.onStop();
        Case_info.this.finish();
//        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Case_info.this.finish();
//        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
//        stop.performClick();// performClick 整個關掉
//        super.onBackPressed();
    }

    //================================ 下面是MENU ==========================================
    //  Menu下面這兩個最基本的要記起來 onCreateOptionsMenu & onOptionsItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.case_info, menu);  //選layout ,menu
        return true;
    }

    //====================================================================================
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: //menu那邊+一個 結束 string也要+
                Case_info.this.finish();
//                onBackPressed();  // 關掉
                break;
            case R.id.b01_insert:
                intent.setClass(getApplicationContext(), Case_info_basic.class);
                intent.putExtra("flag01", "1");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //================================ 下面是次類別之類的東西 ==============================================

}
