package com.example.milan.mojmajstor.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.milan.mojmajstor.MainActivity;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.RegisterActivity;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.User;

public class LoginFragment extends Fragment {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Activity thisActivity;
    private Data data;
    private TextView tvRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btLogin = view.findViewById(R.id.btLogin);
        tvRegister = view.findViewById(R.id.tvRegister);
        data = Data.getInstance();
        thisActivity = getActivity();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etPassword.clearFocus();
                etUsername.clearFocus();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = data.containsUsername(username);
                if(user != null){
                    if(data.checkPassword(user, password)){
                        data.currentUser = user;
                        Intent mainActivityIntent = new Intent(thisActivity, MainActivity.class);
                        thisActivity.startActivity(mainActivityIntent);
                        thisActivity.setResult(Activity.RESULT_OK);
                        thisActivity.finish();
                    }
                    else{
                        etPassword.setTransformationMethod(null);
                        etPassword.setTextColor(getResources().getColor(R.color.colorAccent, null));
                        etPassword.setText("Wrong password!");
                    }
                }
                else{
                    etUsername.setTextColor(getResources().getColor(R.color.colorAccent, null));
                    etUsername.setText("Wrong username!");
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.flLogin, new RegisterFragment()).commit();
            }
        });

        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(etUsername.getCurrentTextColor() == getResources().getColor(R.color.colorAccent, null)){
                        etUsername.setText("");
                        etUsername.setTextColor(getResources().getColor(android.R.color.black, null));
                    }
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(etPassword.getCurrentTextColor() == getResources().getColor(R.color.colorAccent, null)){
                        etPassword.setTransformationMethod(new PasswordTransformationMethod());
                        etPassword.setText("");
                        etPassword.setTextColor(getResources().getColor(android.R.color.black, null));
                    }
                }
            }
        });

        /*Animation animation = android.view.animation.AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
        view.startAnimation(animation);*/
    }
}
