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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingPassword extends AppCompatActivity {
    String phoneNumber;
    EditText ed1;
    EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_password);
        Intent getintent=getIntent();
        phoneNumber=getintent.getStringExtra("phone_number");
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Setting Password");
        tv_title.setGravity(Gravity.CENTER);


//        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
//        exit_app.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent return_last_page=new Intent(SettingPassword.this,Register_Input_VerificationCode.class);
//                startActivity(return_last_page);
//                finish();
//            }
//        });

        TextView tv_input_phone_number1=(TextView)findViewById(R.id.tv_settingpassword3);
        tv_input_phone_number1.setTextColor(Color.parseColor("#F9E79F"));

        Button btn_confirm_to_submit=(Button)findViewById(R.id.btn_confirm_to_submit);
        btn_confirm_to_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1=(EditText)findViewById(R.id.register_input_password);
                String password1=ed1.getText().toString().trim().replaceAll("/s","");
                Log.d("compare",password1);
                ed2=(EditText)findViewById(R.id.register_input_password_confirm);
                String password2=ed2.getText().toString().trim().replaceAll("/s","");
                Log.d("compare",password2);
                if(!TextUtils.isEmpty(password1)&&!TextUtils.isEmpty(password2)){
                    boolean temp=false;
                    int i=password1.compareTo(password2);
                    if (i==0){
                        temp=true;
                    }else {
                        temp=false;
                    }
                    if(temp){
                        toast("Password set successfully");
                        SharedPreferences user=getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=user.edit();
                        editor.putString("phonenumber",phoneNumber);
                        editor.putString("password",password1);
                        editor.commit();
                        Intent return_to_login=new Intent(SettingPassword.this,Login.class);
                        startActivity(return_to_login);
                        finish();
                    }else{
                        toast("The passwords don't match");
                    }

                }else{
                    toast("Please enter your password before submitting");
                }
            }
        });
    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SettingPassword.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
