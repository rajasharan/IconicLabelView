package com.rajasharan.iconiclabelview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.rajasharan.widget.IconicLabelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date = new Date();
        DateFormat df1 = new SimpleDateFormat("M/dd", Locale.getDefault());
        DateFormat df2 = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());

        IconicLabelView time = (IconicLabelView) findViewById(R.id.time);
        time.setTexts(df1.format(date), df2.format(date));
    }
}
