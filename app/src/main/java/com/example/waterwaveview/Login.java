package com.example.waterwaveview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText ed1;
    EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Login");
        tv_title.setGravity(Gravity.CENTER);

        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
        exit_app.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                return false;
            }
        });
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"If you keep pressing it, the App will exit",Toast.LENGTH_LONG).show();
            }
        });

        Button btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences get=getSharedPreferences("user", Context.MODE_PRIVATE);
                String number=get.getString("phonenumber", "0");
                String password=get.getString("password", "0");
                Log.d("share",number);
                Log.d("share",password);

                ed1=(EditText)findViewById(R.id.ed_phoneNumber);
                ed2=(EditText)findViewById(R.id.ed_password);
                String ed1_str=ed1.getText().toString().trim().replaceAll("/s","");
                String ed2_str=ed2.getText().toString().trim().replaceAll("/s","");

                if(!TextUtils.isEmpty(ed1_str)&&!TextUtils.isEmpty(ed2_str)){
                    boolean temp=false;
                    int i=ed1_str.compareTo(number);
                    int j=ed2_str.compareTo(password);
                    if(i==0&&j==0){
                        toast("Login successfully");
                        Intent toDataView=new Intent(Login.this,MainActivity.class);
                        startActivity(toDataView);
                    }else if(i==0&&j!=0) {
                        toast("Wrong password");
                    }else{
                        toast("The user is not registered");
                    }
                }else {
                    toast("Please fill in the login information");
                }
            }
        });

        Button btn_phone_register=(Button)findViewById(R.id.btn_phone_register);
        btn_phone_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_input_phoneNumber=new Intent(Login.this,Register_Input_PhoneNumber.class);
                startActivity(to_input_phoneNumber);
            }
        });

        Button btn_forget_password=(Button)findViewById(R.id.btn_forget_password);
        btn_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_retrieve_password=new Intent(Login.this,RetrievePassword.class);
                startActivity(to_retrieve_password);
            }
        });
    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Login.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
