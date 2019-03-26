package com.example.waterwaveview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
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

import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Register_Input_VerificationCode extends AppCompatActivity {
    private String phoneNumber;
    private String show_info;
    private String country="86";
    private Handler mhandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__input__verification_code);

        Config config=new Config();
        MobSDK.init(this,config.getAppkey(),config.getAppSecret());
        SMSSDK.registerEventHandler(eh);

        Intent getintent=getIntent();
        phoneNumber=getintent.getStringExtra("phone_number");
        Log.d("phoneNumber",phoneNumber);
        show_info="the verification code has sent to:"+phoneNumber;
        TextView tv_info_show=(TextView)findViewById(R.id.tv_verification_code_info);
        tv_info_show.setText(show_info);
        SMSSDK.getVerificationCode(country,phoneNumber);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Register");
        tv_title.setGravity(Gravity.CENTER);

        TextView tv_input_phone_number1=(TextView)findViewById(R.id.tv_inputverificationcode2);
        tv_input_phone_number1.setTextColor(Color.parseColor("#F9E79F"));

        final EditText input_verificationcode=(EditText)findViewById(R.id.ed_input_verificationcode);

        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_last_page=new Intent(Register_Input_VerificationCode.this,Register_Input_PhoneNumber.class);
                startActivity(return_last_page);
                finish();
            }
        });

        Button btn_submit_verification_code=(Button)findViewById(R.id.btn_submit_verification_code);
        btn_submit_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code=input_verificationcode.getText().toString().replaceAll("/s","");
                if(!TextUtils.isEmpty(code)){
                    SMSSDK.submitVerificationCode(country,phoneNumber,code);
                    toast("verify successfully");
                    SMSSDK.unregisterEventHandler(eh);
                    Intent to_setting_password=new Intent(Register_Input_VerificationCode.this,SettingPassword.class);
                    to_setting_password.putExtra("phone_number",phoneNumber);
                    startActivity(to_setting_password);
                    finish();
                }else{
                    toast("Please enter the verification code before submitting");
                }

            }
        });

    }
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event,int result,Object data){
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    toast("verify successfully");

                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    toast("The verification code was successfully obtained");
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    // if you call the get country area code class table, it will be called back here
                    // returns a list of countries that support sending captchas
                }
            }else{
                ((Throwable)data).printStackTrace();
                String str = data.toString();
                toast(str);

            }
        }
    };
    private void toast(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Register_Input_VerificationCode.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
