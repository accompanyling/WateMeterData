package com.example.waterwaveview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DialogUtil {
    private Dialog dialog;
    private View inflate;
    public void showLeftDialog(Context context){
        inflate= LayoutInflater.from(context).inflate(R.layout.dialog_left,null);
        dialog=new Dialog(context,R.style.DialogLeft);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        Window window=dialog.getWindow();
        WindowManager.LayoutParams wlp=window.getAttributes();
        wlp.gravity= Gravity.LEFT;
//        wlp.width=WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width=800;
        wlp.height=WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        dialog.show();
    }
    public void colse(){
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }
}
