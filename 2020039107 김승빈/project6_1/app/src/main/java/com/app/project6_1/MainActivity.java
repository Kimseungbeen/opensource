/* **********************************************
 * 프로젝트명 :  project6_1
 * 작성자 : 2020039107 김승빈
 * 작성일 : 2022.04.09
 *프로그램 설명 :  날짜/시간 앱 
 ************************************************/

package com.app.project6_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        chrono = (Chronometer) findViewById(R.id.chronometer1);

        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);

        calView = (CalendarView) findViewById(R.id.calView);
        tPicker = (TimePicker) findViewById(R.id.tPicker);

        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);

        calView.setVisibility(View.INVISIBLE);
        tPicker.setVisibility(View.INVISIBLE);

        // 캘린더 체크 이벤트 (클릭시 VISIBLE)
        rdoCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
            }
        });
        // 타임피커 체크 이벤트 (클릭시 VISIBLE)
        rdoTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
            }
        });
        // 예약 시작 버튼 이벤트 (클릭시 크로노미터 시작)
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });
        // 예약 완료 버튼 이벤트 (클릭시 크로노미터 정지)

        btnEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);
                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));
            }
        });
        // 날짜 변경 이벤트
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month+1; // 날짜를 +1 안하면 한달씩 낮춰서 나옴 그래서 +1을 함
                selectDay = dayOfMonth;
            }
        });
    }
}