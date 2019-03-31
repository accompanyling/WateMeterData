package com.example.waterwaveview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
public class DialogUtil
{
    private ImageView imageView1;
    private Dialog dialog;
    private View inflate;
    public void showLeftDialog(final Context context){
        inflate= LayoutInflater.from(context).inflate(R.layout.dialog_left,null);
        dialog=new Dialog(context,R.style.DialogLeft);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window window=dialog.getWindow();
        WindowManager.LayoutParams wlp=window.getAttributes();
        wlp.gravity= Gravity.LEFT;
//        wlp.width=WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width=650;
        wlp.height=WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        dialog.show();
        dialog.getWindow().findViewById(R.id.dlg_image1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent to_tip=new Intent();
//                to_tip.setClass(getApplicationContext(),Consults.class);
//                startActivity(to_tip);
                back(context);
                Log.d("dlgmiamge","dlgimage");
            }
        });
    }



    public void colse(){
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }

    public void back(Context context){
        Intent intent=new Intent();
        intent.setClass(context,Consults.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
