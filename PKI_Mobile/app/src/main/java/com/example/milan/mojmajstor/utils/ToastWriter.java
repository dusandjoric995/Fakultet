package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milan.mojmajstor.R;

import java.util.concurrent.Semaphore;

public class ToastWriter {

    public static void write(final Activity activity, String message){
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);
        View view = View.inflate(activity, R.layout.toast_layout, null);
        TextView tvToast = view.findViewById(R.id.tvToastMessage);
        tvToast.setText(message);
        toast.setView(view);
        toast.show();
    }
}
