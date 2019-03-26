package com.example.waterwaveview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RetrievePasswordRight extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password_right);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Retrieve Password");
        tv_title.setGravity(Gravity.CENTER);

//        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
//        exit_app.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent return_last_page=new Intent(RetrievePasswordRight.this,RetrievePassword.class);
//                startActivity(return_last_page);
//                finish();
//            }
//        });

        Button btn_return_login=(Button)findViewById(R.id.btn_return_login);
        btn_return_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_login=new Intent(RetrievePasswordRight.this,Login.class);
                startActivity(return_login);
                finish();
            }
        });

        SharedPreferences get=getSharedPreferences("user", Context.MODE_PRIVATE);
        String password=get.getString("password", "0");
        String showpassword="your login password is:"+password;
        TextView showinfo=(TextView)findViewById(R.id.tv_retrieve_password_sent);
        showinfo.setText(showpassword);
    }
}
