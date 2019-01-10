package com.example.milan.mojmajstor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milan.mojmajstor.MainActivity;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.User;


public class LoginActivity extends AppCompatActivity {

    private TextView tvWrongUserOrPass;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private AppCompatActivity thisActivity;
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

        tvWrongUserOrPass = findViewById(R.id.tvWrongUserOrPass);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        thisActivity = this;
        data = Data.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();

        tvWrongUserOrPass.setText("");
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = data.containsUsername(password);
                if(user != null){
                    if(data.checkPassword(user, password)){
                        data.currentUser = user;
                        Intent mainActivityIntent = new Intent(thisActivity, MainActivity.class);
                        thisActivity.startActivity(mainActivityIntent);
                        thisActivity.setResult(RESULT_OK);
                        thisActivity.finish();
                    }
                    else{
                        tvWrongUserOrPass.setText("Wrong password!");
                    }
                }
                else{
                    tvWrongUserOrPass.setText("Wrong username!");
                }
            }
        });
    }
}
