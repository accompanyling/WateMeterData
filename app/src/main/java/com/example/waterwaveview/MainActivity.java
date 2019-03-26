package com.example.waterwaveview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waterwaveview.view.WaterWaveView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WaterWaveView bar=(WaterWaveView)findViewById(R.id.water_wave_view);
        //use json change the data
        bar.setMax(500);
        bar.setProgressSync(278.8f);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Water Meter Data");
        tv_title.setGravity(Gravity.CENTER);

        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
        exit_app.setImageDrawable(getResources().getDrawable(R.drawable.list));

        final DialogUtil dialogUtil=new DialogUtil();
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUtil.showLeftDialog(MainActivity.this);
            }
        });
    }
}
