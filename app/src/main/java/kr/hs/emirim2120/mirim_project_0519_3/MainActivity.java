package kr.hs.emirim2120.mirim_project_0519_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    // field
    Chronometer timer;
    RadioGroup rg;
    TimePicker time;
    CalendarView date;
    TextView textResult;
    int selectedYear,selectedMonth,selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        rg = findViewById(R.id.rg);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        textResult = findViewById(R.id.text_result);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnDone = findViewById(R.id.btn_done);
        btnStart.setOnClickListener(btnListener);
        btnDone.setOnClickListener(btnListener);
        rg.setOnCheckedChangeListener(rgListener);
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedYear = year;
                selectedMonth = month;
                selectedDay = day;
            }
        });
        // 안 보이게
        time.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);
    }
    RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            time.setVisibility(View.INVISIBLE);
            date.setVisibility(View.INVISIBLE);
            switch (checkedId){
                case R.id.radio_date:
                    date.setVisibility(View.VISIBLE);
                    break;
                case  R.id.radio_time:
                    time.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    timer.setBase(SystemClock.elapsedRealtime()); // 시작할때 새로 초기화
                    timer.start();
                    timer.setTextColor(Color.RED);
                    break;
                case R.id.btn_done:
                    timer.stop();
                    timer.setTextColor(Color.BLUE);
                    textResult.setText(selectedYear+"년"+selectedMonth+"월"+selectedDay+"일");
                    textResult.append(time.getCurrentHour()+"시"+time.getCurrentMinute()+"분 예약 완료됨");
                    break;
            }
        }
    };
}