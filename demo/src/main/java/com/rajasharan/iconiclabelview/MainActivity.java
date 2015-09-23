package com.rajasharan.iconiclabelview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rajasharan.widget.IconicLabelView;

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
    }
}
