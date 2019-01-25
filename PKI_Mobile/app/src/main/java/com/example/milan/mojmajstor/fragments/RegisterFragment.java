package com.example.milan.mojmajstor.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.animation.AnimationUtils;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.MainActivity;
import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.User;

public class RegisterFragment extends Fragment {

    private ImageButton btBack;
    private Button btRegister;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etProfession;
    private EditText etUsername;
    private EditText etPassword;
    private RadioButton rbUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState){

        etFirstName = view.findViewById(R.id.etRFirstName);
        etLastName = view.findViewById(R.id.etRLastName);
        etEmail = view.findViewById(R.id.etREmail);
        etProfession = view.findViewById(R.id.etRProfession);
        etUsername = view.findViewById(R.id.etRUsername);
        etPassword = view.findViewById(R.id.etRPassword);
        rbUser = view.findViewById(R.id.rbRUser);

        rbUser.setChecked(true);

        btBack = view.findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.flLogin, new LoginFragment()).commit();
            }
        });
        btBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS){
                    btBack.setImageResource(R.drawable.ic_back_arrow_white);
                }
                else if(event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                    btBack.setImageResource(R.drawable.ic_back_arrow_primary);
                }
                return false;
            }
        });

        btRegister = view.findViewById(R.id.btRRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.getInstance().users.add(new User(etFirstName.getText().toString(), etLastName.getText().toString(), etEmail.getText().toString(), etProfession.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), rbUser.isChecked() ? LoginActivity.UserType.USER : LoginActivity.UserType.CRAFTSMAN));
                getFragmentManager().beginTransaction().replace(R.id.flLogin, new LoginFragment()).commit();
            }
        });
    }
}
