package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.UserRequest;

import java.text.DecimalFormat;

public class PaymentDialog extends Dialog implements View.OnClickListener {

    private UserRequest userRequest;
    private Button btCancel;
    private Button btPay;
    private EditText etAmount;
    private TextView tvPaid;

    public PaymentDialog(Activity activity, UserRequest userRequest, TextView tvPaid){
        super(activity);
        this.userRequest = userRequest;
        this.tvPaid = tvPaid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_payment);

        etAmount = findViewById(R.id.etAmount);
        btCancel = findViewById(R.id.btPDCancel);
        btPay = findViewById(R.id.btPDPay);

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("")){
                    btPay.setEnabled(false);
                }
                else{
                    btPay.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btPay.setEnabled(false);
        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRequest.setPaid(userRequest.getPaid() + Double.parseDouble(etAmount.getText().toString()));
                tvPaid.setText(String.format("%.2f", userRequest.getPaid()) + " RSD");
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btPDCancel:{
                dismiss();
                break;
            }
        }

    }
}
