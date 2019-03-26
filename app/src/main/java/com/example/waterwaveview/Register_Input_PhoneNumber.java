package com.example.waterwaveview;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_Input_PhoneNumber extends AppCompatActivity {
    private EditText ed_input_phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__input__phone_number);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Register");
        tv_title.setGravity(Gravity.CENTER);

        TextView tv_input_phone_number1=(TextView)findViewById(R.id.tv_inputphonenumber1);
        tv_input_phone_number1.setTextColor(Color.parseColor("#F9E79F"));

        final ImageView exit_app=(ImageView)findViewById(R.id.ib_title_back);
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_loginpage=new Intent(Register_Input_PhoneNumber.this,Login.class);
                startActivity(return_loginpage);
                finish();
            }
        });

        Button btn_get_verification_code=(Button)findViewById(R.id.btn_get_verification_code);
        btn_get_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_input_phone_number=(EditText)findViewById(R.id.ed_input_phoneNumber1);
                String transfer_to_next_page=ed_input_phone_number.getText().toString();
                if(!TextUtils.isEmpty(transfer_to_next_page)){
                    String REGEX_MOBILE_SIMPLE="[1][3587]\\d{9}";
                    Pattern pattern=Pattern.compile(REGEX_MOBILE_SIMPLE);
                    Matcher matcher=pattern.matcher(transfer_to_next_page);
                    if (matcher.find()){
                        Intent to_input_verification_code=new Intent(Register_Input_PhoneNumber.this,Register_Input_VerificationCode.class);
                        to_input_verification_code.putExtra("phone_number",transfer_to_next_page);
                        startActivity(to_input_verification_code);
                    }else {
                        Toast.makeText(getApplicationContext(),"Wrong format of mobile phone number",Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Please enter your mobile phone number",Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
