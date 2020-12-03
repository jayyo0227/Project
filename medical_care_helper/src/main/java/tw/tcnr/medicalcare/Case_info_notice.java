package tw.tcnr.medicalcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Case_info_notice extends AppCompatActivity {
    private Intent intent = new Intent();
    BottomNavigationView bottomNav1, bottomNav2, bottomNav3, bottomNav4;
    private GridView b04_gv001;
    private HashMap<String, Object> item;
    private List<Map<String, Object>> mList = new ArrayList<>();  //建立一個可以新增內容的Array
    private LinearLayout b04_ll001;
    private Spinner b04_s001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_info_notice);
        setupViewComponent();
    }

    private void setupViewComponent() {
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

        b04_gv001 = (GridView) findViewById(R.id.b04_gridview01);
//        b04_ll001 = (LinearLayout) findViewById(R.id.case_info_notice_list);

        for (int i = 0; i < 10; i++) {
            item = new HashMap<String, Object>();// TODO 要注意物件new在for迴圈內, 不然會出錯變重複
            item.put("b04_t001", getString(R.string.b04_t001 + i));
            item.put("b04_t002", getString(R.string.b04_t001 + i)+": 請選擇項目");
            mList.add(item);
        }
        //==========設定gridView============
        //因為自定義Adapter，圖片網址已經寫在那邊，這裡只要載入文字就好
        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(),
                mList,
                R.layout.case_info_notice_list,
                new String[]{"b04_t001", "b04_t002"},
                new int[]{R.id.b04_t001, R.id.b04_t002}
        );

        b04_gv001.setAdapter(adapter);//將抓取的資料設定到表格視窗
        b04_gv001.setOnItemClickListener(onClickGridView);//建立表格視窗按鈕監聽

//        b04_ll001 = (LinearLayout) findViewById(R.id.case_info_notice_list);
//        b04_s001 = (Spinner) findViewById(R.id.case_info_notice_list).findViewById(R.id.b04_s001);
//        b04_s001 = (Spinner) b04_gv001.findViewById(R.id.b04_s001);
//        ArrayAdapter<CharSequence> //設定ArrayAdapter的變數
//                adapsexlist01 = ArrayAdapter.createFromResource(this, R.array.b04_a001,   //設定資料來源
//                android.R.layout.simple_spinner_item); //textViewResId
//        adapsexlist01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //設定Spinner下拉的選單類型
//        b04_s001.setAdapter(adapsexlist01);
    }

    private final GridView.OnItemClickListener onClickGridView = new GridView.OnItemClickListener() {
//        private TextView t001;

        //建立表格視窗按鈕監聽方法
        @Override
        public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
            final String[] ListStr = {"張三", "李四", "王五", "陳六", "呂七", "宋八"};

            final TextView t002 = (TextView) parent.findViewById(R.id.b04_t002);


            AlertDialog.Builder altDlgBldr = new AlertDialog.Builder(parent.getContext());
            altDlgBldr.setTitle("請選擇");
            altDlgBldr.setIcon(android.R.drawable.ic_dialog_info);
//            altDlgBldr.setCancelable(false);

            switch (position){
                case 0:
//                    TextView t001 = new TextView(parent.getContext());
//                    t001.setText("請選擇");
//                    android:drawable/btn_dropdown

//                    t001.setBackground(android.app.);
//                    parent.addView(t001);

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
            }

            altDlgBldr.setItems(ListStr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //--可進行不同的處理方法

                    t002.setText(ListStr[position]);

//                    t001.setText(ListStr[position]);
                }
            });
//
            altDlgBldr.show();
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
                            return true;
                        case R.id.schedule:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Schedule.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.statiscs:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Inquire.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.care:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Carry_info.class));
                            overridePendingTransition(0, 0);
                            return true;
//=================================================================================================================
                        case R.id.nav_home:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.nav_news:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), News_info.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.nav_book:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.VISIBLE);
                            startActivity(new Intent(getApplicationContext(), Record_main.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.nav_cloud:
                            bottomNav4.setVisibility(View.GONE);
                            bottomNav3.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), Fans.class));
                            overridePendingTransition(0, 0);
                            return true;
//=================================================================================================================
                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(), Record_main.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.bloodpressure:
                            startActivity(new Intent(getApplicationContext(), Blood_pressure.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.bodyweight:
                            startActivity(new Intent(getApplicationContext(), Weight.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.heartbeat:
                            startActivity(new Intent(getApplicationContext(), Heart.class));
                            overridePendingTransition(0, 0);
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
        Case_info_notice.this.finish();
//        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Case_info_notice.this.finish();
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
        inflater.inflate(R.menu.bottom_navigation4, menu);  //選layout ,menu
        return true;
    }

    //====================================================================================
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: //menu那邊+一個 結束 string也要+
                Case_info_notice.this.finish();
//                onBackPressed();  // 關掉
                break;
            case R.id.basic:
                startActivity(new Intent(getApplicationContext(), Case_info_basic.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.visit:
                startActivity(new Intent(getApplicationContext(), Case_info_visit.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.notice:
//                            startActivity(new Intent(getApplicationContext(), Case_info_notice.class));
//                            overridePendingTransition(0, 0);
                return true;
            case R.id.contract:
                startActivity(new Intent(getApplicationContext(), Case_info_contract.class));
                overridePendingTransition(0, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //================================ 下面是次類別之類的東西 ==============================================

}
