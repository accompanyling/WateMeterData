package com.example.waterwaveview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Consults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consults);
        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
        exit_app.setImageDrawable(getResources().getDrawable(R.drawable.list));

        final DialogUtil dialogUtil=new DialogUtil();
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_main=new Intent(Consults.this,MainActivity.class);
                startActivity(to_main);
                finish();
            }
        });
    }
}
