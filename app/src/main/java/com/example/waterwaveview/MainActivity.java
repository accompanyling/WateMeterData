package com.example.waterwaveview;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waterwaveview.view.WaterWaveView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    WaterWaveView bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //use json change the data
        bar=(WaterWaveView)findViewById(R.id.water_wave_view);
        new DownloadDataUpdate().execute();


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
//        dialogUtil.findViewById(R.id.dlg_image1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent to_tip=new Intent(MainActivity.this,Consults.class);
//                startActivity(to_tip);
//            }
//        });

        SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date curDate=new Date(System.currentTimeMillis());
        String str_curDate=format.format(curDate);
        TextView  tv_update_time=(TextView)findViewById(R.id.tv_update_time);
        tv_update_time.setText(str_curDate);
        TextView  tv_update_time1=(TextView)findViewById(R.id.tv_update_time1);
        tv_update_time1.setText(str_curDate);
    }

    private class DownloadDataUpdate extends AsyncTask<String,Void,String>{
        String num;
        @Override
        protected String doInBackground(String... strings) {
            String stringUrl="http://120.78.94.0:8081/1.json";
            num="153.6";
            HttpURLConnection urlConnection=null;
            BufferedReader reader;
            Log.d("num","1");
            try{
                URL url=new URL(stringUrl);
                urlConnection=(HttpURLConnection)url.openConnection();
                Log.d("num","2");
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
                Log.d("num","3");
                InputStream inputStream=urlConnection.getInputStream();
                Log.d("num","4");
                StringBuffer buffer=new StringBuffer();
                if(inputStream==null){
                    return null;
                }
                reader=new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line=reader.readLine())!=null){
                    buffer.append(line+"\n");
                }
                if(buffer.length()==0){
                    return null;
                }
                try {
                    JSONArray arraylist=new JSONArray(buffer.toString());
                    JSONObject myneed=arraylist.getJSONObject(0);
                    String data=myneed.optString("data").toString();

                    JSONObject data_child=new JSONObject(data);
                    num=data_child.optString("num").toString();
                    Log.d("num",num);
                    return num;
                }catch (JSONException e){
                    e.printStackTrace();
                    return num;
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
                return num;
            }catch (ProtocolException e){
                e.printStackTrace();
                return num;
            }catch (IOException e){
                e.printStackTrace();
                return num;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            bar.setMax(1000);
            Float result=Float.parseFloat(s);
            bar.setProgressSync(result);
        }
    }
}
