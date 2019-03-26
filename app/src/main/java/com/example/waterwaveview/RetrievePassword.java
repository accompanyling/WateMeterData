package com.example.waterwaveview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RetrievePassword extends AppCompatActivity {
    EditText ed1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Retrieve Password");
        tv_title.setGravity(Gravity.CENTER);

        Button btn_retrieve_password_submit_phonenumber=(Button)findViewById(R.id.btn_retrieve_password_submit_phonenumber);
        btn_retrieve_password_submit_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1=(EditText)findViewById(R.id.ed_retrieve_password_input_phonenumber);
                SharedPreferences get=getSharedPreferences("user", Context.MODE_PRIVATE);
                String number=get.getString("phonenumber", "0");
                String ed1_str=ed1.getText().toString().trim().replaceAll("/s","");

                if(!TextUtils.isEmpty(ed1_str)){
                    int i=number.compareTo(ed1_str);
                    if(i==0){
                        Intent to_next=new Intent(RetrievePassword.this,RetrievePasswordRight.class);
                        startActivity(to_next);
                    }else{
                        toast("The user is not registered");
                    }
                }else{
                    toast("Please enter your mobile phone number");
                }

            }
        });

        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_last_page=new Intent(RetrievePassword.this,Login.class);
                startActivity(return_last_page);
                finish();
            }
        });
    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RetrievePassword.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
