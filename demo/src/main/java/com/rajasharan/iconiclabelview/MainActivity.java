package com.rajasharan.iconiclabelview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rajasharan.widget.IconicLabelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IconicLabelView label = (IconicLabelView) findViewById(R.id.label);
        label.setIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .setBackground(Color.argb(90, 128, 128, 128))
                .setCornerRadius(6.0f)
                .setTextColor(Color.argb(255, 200, 0, 0))
                .setTextSize(16)
                .setTexts(getString(R.string.short_text), getString(R.string.long_text));

        IconicLabelView luffy = (IconicLabelView) findViewById(R.id.luffy);
        luffy.setIcon(getResources().getDrawable(R.drawable.luffy))
                .setBackground(Color.argb(70, 10, 150, 200))
                .setCornerRadius(10.0f)
                .setTextColor(Color.BLACK)
                .setTextSize(32)
                .setTexts(getString(R.string.luffy_short), getString(R.string.luffy_long));

        Date date = new Date();
        DateFormat df1 = new SimpleDateFormat("M/dd");
        DateFormat df2 = new SimpleDateFormat("MMMM dd yyyy");
        IconicLabelView time = (IconicLabelView) findViewById(R.id.time);
        time.setIcon(getResources().getDrawable(R.drawable.ic_alarm_black))
                .setTextColor(Color.GREEN)
                .setTextSize(16)
                .setTexts(df1.format(date), df2.format(date));
    }
}
