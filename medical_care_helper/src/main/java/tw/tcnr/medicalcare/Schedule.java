package tw.tcnr.medicalcare;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DAY_OF_WEEK;

public class Schedule extends AppCompatActivity {

    private TextView tv ;
    TextView t001, t002;
    Button b001, b002;
    private int year;
    private int month;
    private int monthday;
    private int wek;
    private int day;
    private Uri uri;
    BottomNavigationView bottomNav1, bottomNav2, bottomNav3, bottomNav4;



    LinearLayout l001,l002;
    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    TableRow.LayoutParams params3=new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    TableRow.LayoutParams params4=new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

    Calendar calendar = Calendar.getInstance();
    private Dialog calender_dlg,manage_dlg;
    private DatePickerDialog picker;

    private String[] weekday = {"日", "一", "二", "三", "四", "五", "六", "七"};
    private String[][] member1 = {
            { "1", "小張三", "20200501", "20200901","2", "11,12,13,14","0972078902" },
            { "2", "中張三", "20200401", "20201001","2", "10" ,"0972078902"},
            { "3", "大張三", "20200501", "20200901","2", "20,21" ,"0972078902"},
            { "4", "小李四", "20200701", "20200901","3", "12,13" ,"0972078902"},
            { "5", "中李四", "20200801", "20201201","4", "9" ,"0972078902"},
            { "6", "大李四", "20200501", "20201201","4", "10,11","0972078902" },
            { "7", "小王五", "20200401", "20200901","4", "11,12","0972078902" },
            { "8", "中王五", "20200201", "20200901","4", "14" ,"0972078902"},
            { "9", "大王五", "20200201", "20201201","5", "12","0972078902"},
            { "10", "小阿陸", "20200501", "20200901","5", "14","0972078902" },
            { "11", "中阿陸", "20200501", "20201101","5", "18" ,"0972078902"},
            { "12", "大阿陸", "20200501", "20201101","5", "20" ,"0972078902"}
    };

    private String[][] member_case = {
            { "1", "小一", "0972078901", "台中市西屯區中工一路","吃飯" },
            { "2", "陳二", "0972078902", "台中市西屯區中工一路","吃飯" },
            { "3", "張三", "0972078903", "台中市西屯區中工一路","吃飯" },
            { "4", "李四", "0972078904", "台中市西屯區中工一路","吃飯" },
            { "5", "王五", "0972078905", "台中市西屯區中工一路","吃飯" },
            { "6", "阿六", "0972078906", "台中市西屯區中工一路","吃飯" },
            { "7", "黃七", "0972078907", "台中市西屯區中工一路","吃飯" },
            { "8", "周八", "0972078908", "台中市西屯區中工一路","吃飯" },
            { "9", "潘九", "0972078909", "台中市西屯區中工一路","吃飯" },

    };



    private Spinner s001,s002,s003;

    String nowyear,nowmonth,nowday,nowtime,nowweek;
    String[][][] final_arr = new String[21][32][10];
    private CheckBox c001,c002,c003,c004,c005,c006,c000;

    int  calender_id;
    String   schedule_id;
    private schedule_dbhelper dbhelper;
    private String[]   allid=new String[1];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//抓取系統時間
        calendar.setTime(new Date());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) +1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        wek=calendar.get(DAY_OF_WEEK);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        monthday = calendar.get(Calendar.DATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

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
        bottomNav1.setSelectedItemId(R.id.schedule);
        bottomNav3.setVisibility(View.GONE);
        bottomNav4.setVisibility(View.GONE);

        l001 = (LinearLayout) findViewById(R.id.g01_l001);
        l002 = (LinearLayout) findViewById(R.id.g01_l002);
        b001=(Button)findViewById(R.id.g01_b001);
        b002=(Button)findViewById(R.id.g01_b002);
        t001=(TextView) findViewById(R.id.g01_t001);
        t002=(TextView) findViewById(R.id.g01_t002);

//        b001.setOnClickListener(b001_on);
//        b002.setOnClickListener(b001_on);

        params1.setMargins(0,0,1,1);
        params1.width=199;
        params1.height=149;

        params2.setMargins(0,1,1,1);
        params2.width=199;
        params2.height=98;
        params2.gravity=1;

        params3.setMargins(1,0,1,1);
        params3.width=98;
        params3.height=149;
        params3.gravity=1;

        params4.setMargins(1,1,1,1);
        params4.width=98;
        params4.height=98;
        params4.gravity=1;

        t002.setText(Integer.toString(month)+getString(R.string.g01_t002));

        //抓取圖形字--------------------------------------
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(params1);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setImageResource(R.drawable.word);
        l001.addView(iv);
        //-------------------------------------------------


        //產生時間表頭-------------------------------
        for (int i = 6; i <= 21; i++)
        {
            String mm = String.format("%02d", i)+":00";
            TextView   tv = new TextView(this); // tv 繼承TextView
            tv.setLayoutParams(params2);
            tv.setText(mm); // 將產生的物件填入文字.
            tv.setGravity(1);
            tv.setTextSize(12);
            tv.setBackgroundColor(Color.parseColor("#A5D6E8"));
            l001.addView(tv);// 顯示textview物件

        }
        dbhelper =new schedule_dbhelper(this, "schedule.db", null, 1);
        setupviewconponent();
    }


    private  Button.OnClickListener on_calender= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            calender_id=v.getId();
            schedule_id=(String)v.getTag();

            String  schedule_id=(String) v.getTag();
            if (schedule_id=="-1"){
                show_insert();
            }
            else {

                show_select();

            }
        }
    };

    ///行事曆排程設定
    private  Button.OnClickListener on_insert= new Button.OnClickListener() {


        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.g01_b001:

                    picker = new DatePickerDialog(Schedule.this,

                            new DatePickerDialog.OnDateSetListener() {
                                TextView t007 = (TextView) manage_dlg.findViewById(R.id.g01_t007);

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    t007.setText(year+"年"+String.format("%02d", monthOfYear + 1)+"月"+String.format("%02d", dayOfMonth)+"日"  );

                                }

                            }, Integer.parseInt(nowyear), Integer.parseInt(nowmonth)-1, Integer.parseInt(nowday));

//
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(nowyear));
                    calendar.set(Calendar.MONTH,Integer.parseInt(nowmonth)-1);
                    calendar.set(Calendar.DATE,Integer.parseInt(nowday));


                    picker.getDatePicker().setMinDate(calendar.getTimeInMillis());
                    picker.show();
                    break;

                case R.id.g01_b002:


                    String week="";
                    if (c000.isChecked())
                        week += ",0";
                    if (c001.isChecked())
                        week += ",1";
                    if (c002.isChecked())
                        week  += ",2";
                    if (c003.isChecked())
                        week += ",3";
                    if (c004.isChecked())
                        week += ",4";
                    if (c005.isChecked())
                        week += ",5";
                    if (c006.isChecked())
                        week += ",6";
                    week=week.substring(1);
                    TextView t005 = (TextView) manage_dlg.findViewById(R.id.g01_t005);

                    String  tmp=(t005.getText()).toString();

                    TextView t007 = (TextView) manage_dlg.findViewById(R.id.g01_t007);
                    CharSequence tmp3=t007.getText();
                    //  CharSequence tmp4=tmp3.toString();

                    TextView t009 = (TextView) manage_dlg.findViewById(R.id.g01_t009);
                    CharSequence tmp5=t009.getText().subSequence(0, 2);



                    String   name= ((SpinnerItem)s001.getSelectedItem()).getValue();
                    String   name_id= ((SpinnerItem)s001.getSelectedItem()).getID();
                    String   service = (String) s002.getSelectedItem();
                    String   time_start = (t009.getText()).toString();
                    time_start  =time_start .substring(0, 2);
                    time_start=Integer.toString(Integer.parseInt(time_start));
                    String   time_end = (String) s003.getSelectedItem();
                    time_end  =time_end .substring(0, 2);
                    time_end=Integer.toString(Integer.parseInt(time_end));
                    String hour=Integer.toString(Integer.parseInt(time_end)-Integer.parseInt(time_start)+1);
                    String date1= (t005.getText()).toString();
                    String date_start=date1.substring(0,4) +"-"+date1.substring(5,7) +"-"+ date1.substring(8,10);
                    String date2= (t007.getText()).toString();
                    String date_end=date2.substring(0,4) +"-"+date2.substring(5,7) +"-"+ date2.substring(8,10);
                    String phone=member_case[Integer.parseInt(name_id)][2];
                    String address=member_case[Integer.parseInt(name_id)][3];

                    Toast.makeText(getApplicationContext(), name_id, Toast.LENGTH_LONG).show();

                    List<String> aListData = new ArrayList<String>();

                    ContentValues newrow=new ContentValues();
                    newrow.put("case_id","1");
                    newrow.put("date_start",date_start);
                    newrow.put("date_end",date_end);
                    newrow.put("time_start",time_start);
                    newrow.put("time_end",time_end);
                    newrow.put("hour",hour);
                    newrow.put("week",week);
                    newrow.put("name",name);
                    newrow.put("phone",phone);
                    newrow.put("address",address);
                    newrow.put("service",service);


                    SQLiteDatabase db = dbhelper.getWritableDatabase();
                    db.insert("schedule", null, newrow);


                    db.close();
                    manage_dlg.cancel();
                    setupviewconponent();

                    break;


                case R.id.g01_b003:
                    manage_dlg.cancel();
                    break;
            }
        }
    };



    private  Button.OnClickListener on_select= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.g01_b001:
                    calender_dlg.cancel();
                    show_insert();
                    break;
                case R.id.g01_b002:
                    //   Toast.makeText(getApplicationContext(), schedule_id, Toast.LENGTH_LONG).show();
                    SQLiteDatabase db = dbhelper.getWritableDatabase();
                    db.delete("schedule"," _id="+schedule_id, null);
                    // db.delete(schedule, _id + "=" + schedule_id, null);
                    db.close();
                    calender_dlg.cancel();
                    setupviewconponent();

                    break;
                case R.id.g01_b003:
                    //   uri = Uri.parse("tel:"+schedule_list.get(schedule_Id).get(8));
                    Intent it = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(it);
                    break;
                case R.id.g01_b004:
                    calender_dlg.cancel();
                    break;
                case R.id.g01_b005:
                    SQLiteDatabase db1 = dbhelper.getWritableDatabase();
                    String sql="select _id,case_id,date_start,date_end,time_start,time_end,week,name,phone,address,service,hour from schedule where  _id="+schedule_id;
                    Cursor rs = db1.rawQuery(sql, null);//
                    rs.moveToFirst();





                    calender_dlg.cancel();
                    List<String> aListData = new ArrayList<String>();

                    ContentValues newrow=new ContentValues();
                    newrow.put("calender_id",calender_id);
                    newrow.put("schedule_id",schedule_id);
                    newrow.put("date","");
                    newrow.put("year",Integer.toString(calender_id).substring(0,4));
                    newrow.put("month",Integer.toString(calender_id).substring(4,6));
                    newrow.put("day",Integer.toString(calender_id).substring(6,8));
                    newrow.put("time_start",rs.getString(4));
                    newrow.put("time_end",rs.getString(5));
                    newrow.put("name",rs.getString(7));
                    newrow.put("phone",rs.getString(8));
                    newrow.put("address",rs.getString(9));
                    newrow.put("service",rs.getString(10));
                    newrow.put("hour",rs.getString(11));

                    rs.close();
                    db1.close();
                    SQLiteDatabase db2 = dbhelper.getWritableDatabase();
                    db2.insert("record", null, newrow);

                    db2.close();
                    calender_dlg.cancel();
                    setupviewconponent();

                    break;
                case R.id.g01_b006:
                    calender_dlg.cancel();
                    break;
                case R.id.g01_b007:
                    Toast.makeText(getApplicationContext(), allid[1], Toast.LENGTH_LONG).show();
                    SQLiteDatabase db3 = dbhelper.getWritableDatabase();
                    db3.delete("record ","_id="+allid[1],null);
                    db3.close();
                    calender_dlg.cancel();
                    setupviewconponent();
                    break;
            }      }
    };
    private  Button.OnClickListener b001_on= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.g01_b001:
                    calendar.add(Calendar.MONTH, -1);
                    break;
                case R.id.g01_b002:
                    calendar.add(Calendar.MONTH, +1);
                    break;
            }
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) +1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            t001.setText(Integer.toString(year)+getString(R.string.g01_t001));
            t002.setText(Integer.toString(month)+getString(R.string.g01_t002));

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            wek=calendar.get(DAY_OF_WEEK);
            calendar.set(Calendar.DATE, 1);
            calendar.roll(Calendar.DATE, -1);
            monthday = calendar.get(Calendar.DATE);

            //   String[][][] arr = new String[22][monthday+1][10];
            //-----------------------------------------
            setupviewconponent();

        }
    };
    private void setupviewconponent( ) {


        String[][][] arr = new String[22][32][10];
        int gap;

        dbhelper =new schedule_dbhelper(this, "schedule.db", null, 1);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        // { "0","1", "2020-05-01", "2020-09-01", "11","12","1,3,5", "張三","0972078902","台中市北屯區松竹路10號","洗澡服務" },
        String sql="select _id,case_id,date_start,date_end,time_start,time_end,week,name,phone,address,service from schedule";
        Cursor rs = db.rawQuery(sql, null);//
        rs.moveToFirst();

        while(!rs.isAfterLast()){
            //for(int i = 0;i<schedule_list.size();i++){

            ;
            String[]   allweek=rs.getString(6).split(",");//循環取出list中的所有用戶名的值
            int time_start = Integer.parseInt(rs.getString(4));
            int time_end = Integer.parseInt(rs.getString(5));



            for (int m = 0; m < allweek.length; m = m + 1) {
                int week= Integer.parseInt(allweek[m]) ;
                if (week > wek) {
                    gap = week - wek+2;
                } else if (week < wek) {
                    gap = week - wek + 7+2;
                } else {
                    gap = 0+2;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date_start=rs.getString(2);
                String date_end=rs.getString(3);



                for (int day = gap; day < monthday; day = day + 7) {

                    String date_now=String.format("%04d", year) +"-"+String.format("%02d", month) +"-"+ String.format("%02d", day );
                    try {
                        Date date_start1 = df.parse(date_start);
                        Date date_end1 = df.parse(date_end);
                        Date date_now1 = df.parse(date_now);
                        if ((date_start1.getTime() <= date_now1 .getTime()) &&(date_end1.getTime() >= date_now1 .getTime())) {
                            for (int time = time_start; time < time_end+1; time = time + 1) {

                                String tv_id1 = String.format("%04d", year) + String.format("%02d", month) + String.format("%02d", day ) + String.format("%02d", time);
                                arr[time][day][1] = tv_id1;
                                arr[time][day][2] = rs.getString(7);
                                if (time==time_start){
                                    arr[time][day][3] = "#FAACcC";
                                }else{
                                    arr[time][day][3] = "#FAACAC";
                                }

                                arr[time][day][4] = rs.getString(0);
                            }
                        }
                    } catch (Exception exception) {
                        //   exception.printStackTrace();
                    }

                }
            }
            rs.moveToNext();
        }
        rs.close();
        db.close();
//                { "12", "阿六", "2020050120","5","1", "20","2" },
//                { "10", "阿六", "2020050414","5","4", "14","2" },
//                { "11", "阿六", "2020050418","5","4", "18","2" },
//                { "12", "阿六", "2020050420","5","4", "20","2" }
//rs.getString(8)
        SQLiteDatabase db1 = dbhelper.getWritableDatabase();
        // { "0","1", "2020-05-01", "2020-09-01", "11","12","1,3,5", "張三","0972078902","台中市北屯區松竹路10號","洗澡服務" },
        String sql1="select _id,name,calender_id,month,day,time_start,hour,schedule_id from record";
        Cursor rs1 = db1.rawQuery(sql1, null);//
        rs1.moveToFirst();
//
        while(!rs1.isAfterLast()) {
            Integer mon = Integer.parseInt(rs1.getString(3));
            Integer day = Integer.parseInt(rs1.getString(4));
            Integer time = Integer.parseInt(rs1.getString(5));
            Integer section = Integer.parseInt(rs1.getString(6));
            //      Toast.makeText(getApplicationContext(), rs1.getString(0)+","+rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6), Toast.LENGTH_LONG).show();
            if (mon.equals(month)) {

                for (int h=0 ;h<section;h++){
                    String calender_id=Integer.toString(Integer.parseInt(rs1.getString(2))+h);
                    arr[time+h][day][1] =calender_id;
                    arr[time+h][day][2] = rs1.getString(1);
                    arr[time+h][day][3] = "#E6F67C";
                    arr[time+h][day][4] = rs1.getString(7)+","+rs1.getString(0);
                }

            }
            rs1.moveToNext();
        }
        rs1.close();
        db1.close();
//        for (int i = 0; i < record.length; i++) {
//            Integer mon = Integer.parseInt(record[i][3]);
//            Integer day = Integer.parseInt(record[i][4]);
//            Integer time = Integer.parseInt(record[i][5]);
//            Integer section = Integer.parseInt(record[i][6]);
//
//            if (mon.equals(month)) {
//
//                for (int h = 0; h < section; h++) {
//                    String schedule_id = Integer.toString(Integer.parseInt(record[i][2]) + h);
//                    arr[time + h][day][1] = schedule_id;
//                    // arr[time+h][day][1] = record[i][2];
//                    arr[time + h][day][2] = record[i][1];
//                    arr[time + h][day][3] = "#E6F67C";
//                }
//
//            }
//
//        }

        l002.removeAllViews();//清除所以行事
        int m;
        //自動產生日期和星期--------------------------------
        TableRow tr1 = new TableRow(this);
        for (int i = 1; i <= monthday; i++) // 產 生日期和星期
        {
            int ii = (i + wek - 2) % 7;//計算當月的1號是星期幾
            String mm = String.format("%02d", i) + "\n" + weekday[ii];

            tv = new TextView(this); // tv 繼承TextView
            tv.setLayoutParams(params3);
            tv.setText(mm);
            tv.setGravity(1);
            tv.setTextSize(12);
            tv.setWidth(100);
            if ((ii==0)||(ii==6)){
                tv.setBackgroundColor(Color.parseColor("#A5C8E8"));
            }else{
                tv.setBackgroundColor(Color.parseColor("#A5D6E8"));
            }


            tr1.addView(tv);
        }
        l002.addView(tr1);
//---------------------
        //自動產生行事--------------------------------
        for ( int time = 6; time <= 21; time++) {//跑出6點到21點.共16個TABLEROW
//
            TableRow tr = new TableRow(this);
            for (int i = 1; i <= monthday; i++) // 依當時的月份天數,跑出行事曆
            {
                int ii = (i + wek - 2) % 7;//
                tv = new TextView(this); // tv 繼承TextView
                int tv_id = Integer.parseInt(String.format("%04d", year) + String.format("%02d", month) + String.format("%02d", i) + String.format("%02d", time));
                tv.setId(tv_id);
                tv.setLayoutParams(params4);
                tv.setGravity(1);
                tv.setText(arr[time][i][2]);

                int strLen;
                if (arr[time][i][4] != null && (strLen = arr[time][i][3].length()) != 0){
                    //    tv.setTag(Integer.parseInt(arr[time][i][4]));
                    tv.setTag((arr[time][i][4]));
                }
                else{
                    tv.setTag("-1");
                }

                tv.setTextSize(8);
//                //如果是星期六和星期日.;則改變底色------------------------------------
                if ((ii == 0) || (ii == 6)) {
                    tv.setBackgroundColor(Color.parseColor("#AFFCC1"));
                } else {
                    tv.setBackgroundColor(Color.parseColor("#CAFCD6"));
                }
//
                int strLen1;
                if (arr[time][i][3] != null && (strLen1 = arr[time][i][3].length()) != 0){
                    tv.setBackgroundColor(Color.parseColor(arr[time][i][3]));
                }
                //---------------------------------------------------------------
                tr.addView(tv);
                tv.setOnClickListener(on_calender);
            }
            l002.addView(tr);
        }
        final_arr=arr;
        //   View v=findViewById(2020080109);


    }

    private void show_insert(){
        manage_dlg = new Dialog(Schedule.this);

        manage_dlg.setCancelable(false);
        manage_dlg.setContentView(R.layout.schedule_dlg1);
        manage_dlg.show();
        TextView t001 = (TextView) manage_dlg.findViewById(R.id.g01_t001);
        TextView t002 = (TextView) manage_dlg.findViewById(R.id.g01_t002);
        TextView t003 = (TextView) manage_dlg.findViewById(R.id.g01_t003);
        TextView t004 = (TextView) manage_dlg.findViewById(R.id.g01_t004);
        TextView t005 = (TextView) manage_dlg.findViewById(R.id.g01_t005);
        TextView t006 = (TextView) manage_dlg.findViewById(R.id.g01_t006);
        TextView t007 = (TextView) manage_dlg.findViewById(R.id.g01_t007);
        TextView t008 = (TextView) manage_dlg.findViewById(R.id.g01_t008);
        TextView t009 = (TextView) manage_dlg.findViewById(R.id.g01_t009);
        Button b001 = (Button) manage_dlg.findViewById(R.id.g01_b001);
        Button b002 = (Button) manage_dlg.findViewById(R.id.g01_b002);
        Button b003 = (Button) manage_dlg.findViewById(R.id.g01_b003);
        c000 = (CheckBox) manage_dlg.findViewById(R.id.c000);
        c001 = (CheckBox) manage_dlg.findViewById(R.id.c001);
        c002 = (CheckBox) manage_dlg.findViewById(R.id.c002);
        c003 = (CheckBox) manage_dlg.findViewById(R.id.c003);
        c004 = (CheckBox) manage_dlg.findViewById(R.id.c004);
        c005 = (CheckBox) manage_dlg.findViewById(R.id.c005);
        c006 = (CheckBox) manage_dlg.findViewById(R.id.c006);


        b001.setOnClickListener(on_insert);
        b002.setOnClickListener(on_insert);
        b003.setOnClickListener(on_insert);
        t001.setText("工作排程小幫手"+Integer.toString(calender_id));
        //   t003.setText(getString(R.string.g01_t003)+Integer.toString(calender_id));
        // 初始化控制元件
        nowyear= Integer.toString(calender_id).substring(0,4);
        nowmonth=Integer.toString(calender_id).substring(4,6);
        nowday = Integer.toString(calender_id).substring(6,8);
        nowtime = Integer.toString(calender_id).substring(8,10);;



        nowweek =Integer.toString(calendar.get(DAY_OF_WEEK));


        t005.setText(nowyear+"年"+nowmonth+"月"+nowday+"日");
        t007.setText(nowyear+"年"+nowmonth+"月"+nowday+"日");
        t009.setText(nowtime+"點");







        List<SpinnerItem> list = new ArrayList<SpinnerItem>();

        list.add(new SpinnerItem("0", "小一"));
        list.add(new SpinnerItem("1", "陳二"));
        list.add(new SpinnerItem("2", "張三"));
        list.add(new SpinnerItem("3", "李四"));
        list.add(new SpinnerItem("4", "王五"));
        list.add(new SpinnerItem("5", "阿六"));
        list.add(new SpinnerItem("6", "黃七"));
        list.add(new SpinnerItem("7", "周八"));
        list.add(new SpinnerItem("8", "潘九"));
        ArrayAdapter<SpinnerItem> adapter= new ArrayAdapter<SpinnerItem>(getApplicationContext(),  android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s001 = (Spinner) manage_dlg.findViewById(R.id.s001);
        s001.setAdapter(adapter);



        String[] mItems2 = {"健康檢查","洗澡服務","吃飯","逛街"};
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, mItems2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s002 = (Spinner) manage_dlg.findViewById(R.id.s002);
        s002.setAdapter(adapter2);



        ArrayList<String>mItems3=new ArrayList<String>();


        int iday=Integer.parseInt(nowday);
        int itime=Integer.parseInt(nowtime);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Integer.parseInt(nowyear),Integer.parseInt(nowmonth)-1,Integer.parseInt(nowday));
        int iweek=calendar.get(DAY_OF_WEEK)-1;
        Toast.makeText(getApplicationContext(), Integer.toString(iweek), Toast.LENGTH_LONG).show();
        switch(iweek){

            case 0:
                c000.setChecked(true);
                c000.setEnabled(false);
                break;
            case 1:
                c001.setChecked(true);
                c001.setEnabled(false);
                break;
            case 2:
                c002.setChecked(true);
                c002.setEnabled(false);
                break;
            case 3:
                c003.setChecked(true);
                c003.setEnabled(false);
                break;
            case 4:
                c004.setChecked(true);
                c004.setEnabled(false);
                break;
            case 5:
                c005.setChecked(true);
                c005.setEnabled(false);
                break;
            case 6:
                c006.setChecked(true);
                c006.setEnabled(false);
                break;
        }


        for(int i=itime;i<=21; i++){
            if (final_arr[i][iday][2]== null || (final_arr[i][iday][2].length()) == 0) {
                mItems3.add(String.format("%02d",i) + "點");

            }
            else{
                break;
            }
        }

        ArrayAdapter adapter3=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,mItems3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s003 = (Spinner) manage_dlg.findViewById(R.id.s003);
        s003.setAdapter(adapter3);


    }

    private void show_select(){
        //   Toast.makeText(Schedule.this, "schedule_Id"+Integer.toString(schedule_Id), Toast.LENGTH_SHORT).show();
        calender_dlg = new Dialog(Schedule.this);
        // mLoginDlg.setTitle(getString(R.string.app_name));
        calender_dlg.setCancelable(false);
        calender_dlg.setContentView(R.layout.schedule_dlg);
        calender_dlg.show();
        TextView t001 = (TextView) calender_dlg.findViewById(R.id.g01_t001);
        TextView t002 = (TextView) calender_dlg.findViewById(R.id.g01_t002);
        TextView t003 = (TextView) calender_dlg.findViewById(R.id.g01_t003);
        TextView t0031 = (TextView) calender_dlg.findViewById(R.id.g01_t0031);
        TextView t004 = (TextView)calender_dlg.findViewById(R.id.g01_t004);
        TextView t005 = (TextView) calender_dlg.findViewById(R.id.g01_t005);
        TextView t006 = (TextView) calender_dlg.findViewById(R.id.g01_t006);
        TextView t007 = (TextView) calender_dlg.findViewById(R.id.g01_t007);
        TextView t008 = (TextView) calender_dlg.findViewById(R.id.g01_t008);
        TextView t009 = (TextView) calender_dlg.findViewById(R.id.g01_t009);
        TextView t010 = (TextView) calender_dlg.findViewById(R.id.g01_t010);
        TextView t011 = (TextView) calender_dlg.findViewById(R.id.g01_t011);

        Button b001 = (Button) calender_dlg.findViewById(R.id.g01_b001);
        Button b002 = (Button) calender_dlg.findViewById(R.id.g01_b002);
        Button b003 = (Button) calender_dlg.findViewById(R.id.g01_b003);
        Button b004 = (Button) calender_dlg.findViewById(R.id.g01_b004);
        Button b005 = (Button) calender_dlg.findViewById(R.id.g01_b005);
        Button b006 = (Button) calender_dlg.findViewById(R.id.g01_b006);
        Button b007 = (Button) calender_dlg.findViewById(R.id.g01_b007);

        b001.setOnClickListener(on_select);
        b002.setOnClickListener(on_select);
        b003.setOnClickListener(on_select);
        b004.setOnClickListener(on_select);
        b005.setOnClickListener(on_select);
        b006.setOnClickListener(on_select);
        b007.setOnClickListener(on_select);

        allid=schedule_id.split(",");
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        // { "0","1", "2020-05-01", "2020-09-01", "11","12","1,3,5", "張三","0972078902","台中市北屯區松竹路10號","洗澡服務" },
        String sql="select _id,case_id,date_start,date_end,time_start,time_end,week,name,phone,address,service,hour from schedule where  _id="+allid[0];
        Cursor rs = db.rawQuery(sql, null);//
        rs.moveToFirst();
        //   Toast.makeText(getApplicationContext(), sql, Toast.LENGTH_LONG).show();
        t001.setText("排程起始日:"+rs.getString(2));
        t002.setText("排程截止日:"+rs.getString(3));
        t003.setText("服務時間:"+rs.getString(4)+"點~"+rs.getString(5)+"點");
        t0031.setText("時間長度:"+rs.getString(11)+"小時");
        String[]   allweek=rs.getString(6).split(",");//循環取出list中的所有用戶名的值
        String sweek="";
        for (int m = 0; m < allweek.length; m = m + 1) {
            if (m==0) {
                sweek="星期"+weekday[Integer.parseInt(allweek[m])];
            }else{
                sweek=sweek+",星期"+weekday[Integer.parseInt(allweek[m])];;
            }
        }
        t004.setText("每週排程:"+sweek);
        t005.setText("姓名:"+rs.getString(7));
        t006.setText("手機:"+rs.getString(8));
        t007.setText("地址:"+rs.getString(9));
        t008.setText("姓名:"+rs.getString(7));
        t009.setText("服務項目:"+rs.getString(10));
        String  today= Integer.toString(calender_id).substring(4,6)+"月"+Integer.toString(calender_id).substring(6,8)+"日"+rs.getString(4)+"時~"+rs.getString(5)+"時";
        t010.setText("服務時間:"+today);
        if (allid.length==2){
            b005.setVisibility(View.GONE);
            b007.setVisibility(View.VISIBLE);
            t011.setText("服務狀態:己完成");
        }else{

            t011.setText("服務狀態:未完成");
        }

        rs.close();
        db.close();

    }

    public class SpinnerItem {
        private String ID = "";
        private String Value = "";
        public SpinnerItem(String iD, String value) {
            ID = iD;
            Value = value;
        }
        @Override
        public String toString() { return Value;  }
        public String getID() {
            return ID;
        }
        public String getValue() {
            return Value;
        }
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
                            finish();
                            return true;
                        case R.id.schedule:
//                            bottomNav4.setVisibility(View.GONE);
//                            bottomNav3.setVisibility(View.GONE);
//                            startActivity(new Intent(getApplicationContext(), Schedule.class));
//                            overridePendingTransition(0, 0);
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
                        case R.id.basic:
                            startActivity(new Intent(getApplicationContext(), Case_info_basic.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.visit:
                            startActivity(new Intent(getApplicationContext(), Case_info_visit.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.notice:
                            startActivity(new Intent(getApplicationContext(), Case_info_notice.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.contract:
                            startActivity(new Intent(getApplicationContext(), Case_info_contract.class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
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
        Schedule.this.finish();
//        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Schedule.this.finish();
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);  //選layout ,menu
        return true;
    }
    //====================================================================================
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings: //menu那邊+一個 結束 string也要+
                this.finish();
                onBackPressed();  // 關掉
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //================================ 下面是次類別之類的東西 ==============================================

}
