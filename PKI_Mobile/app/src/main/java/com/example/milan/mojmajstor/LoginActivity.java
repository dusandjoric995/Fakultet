package com.example.milan.mojmajstor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milan.mojmajstor.MainActivity;
import com.example.milan.mojmajstor.fragments.LoginFragment;
import com.example.milan.mojmajstor.fragments.MainFragmentController;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.User;


public class LoginActivity extends AppCompatActivity {

    private Data data;

    public static enum UserType {
        CRAFTSMAN,
        USER,
        GUEST
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        data = Data.getInstance();
        Data.setCurrentActivity(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.flLogin, new LoginFragment()).commit();

    }

    /*@Override
    public void onBackPressed() {
        if(MainFragmentController.fragmentBackStackEmpty()){
            setResult(RESULT_OK);
            finish();
        }
        else {
            super.onBackPressed();
        }
    }*/
}
