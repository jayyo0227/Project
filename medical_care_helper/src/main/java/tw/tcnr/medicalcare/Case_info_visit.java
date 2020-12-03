package tw.tcnr.medicalcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class Case_info_visit extends AppCompatActivity {
    private Intent intent = new Intent();
    BottomNavigationView bottomNav1, bottomNav2, bottomNav3, bottomNav4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_info_visit);
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
    }
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
        Case_info_visit.this.finish();
//        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Case_info_visit.this.finish();
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
    public void onBackPressed()
    {
//        stop.performClick();// performClick 整個關掉
//        super.onBackPressed();
    }
    //================================ 下面是MENU ==========================================
    //  Menu下面這兩個最基本的要記起來 onCreateOptionsMenu & onOptionsItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation4,menu);  //選layout ,menu
        return true;
    }
    //====================================================================================
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: //menu那邊+一個 結束 string也要+
                Case_info_visit.this.finish();
//                onBackPressed();  // 關掉
                break;
            case R.id.basic:
                startActivity(new Intent(getApplicationContext(), Case_info_basic.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.visit:
//                            startActivity(new Intent(getApplicationContext(), Case_info_visit.class));
//                            overridePendingTransition(0, 0);
                return true;
            case R.id.notice:
                startActivity(new Intent(getApplicationContext(), Case_info_notice.class));
                overridePendingTransition(0, 0);
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
