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
import com.example.milan.mojmajstor.utils.ToastWriter;
import com.example.milan.mojmajstor.utils.UserRequest;

public class PaymentDialog extends Dialog {

    private UserRequest userRequest;
    private Button btCancel;
    private Button btPay;
    private Button btNewPaymenn;
    private EditText etAmount;
    private TextView tvPaid;
    private TextView tvStatus;
    private Activity thisActivity;

    public PaymentDialog(Activity activity, UserRequest userRequest, TextView tvPaid, TextView tvStatus, Button btNewPayment){
        super(activity);
        thisActivity = activity;
        this.userRequest = userRequest;
        this.tvPaid = tvPaid;
        this.tvStatus = tvStatus;
        this.btNewPaymenn = btNewPayment;
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
                if(s.toString().equals("")){
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
                double newPayment = Double.parseDouble(etAmount.getText().toString());
                if(userRequest.getPaid() + newPayment >= userRequest.getPrice()){
                    userRequest.setPaid(userRequest.getPrice());
                    userRequest.setStatus(thisActivity.getResources().getString(R.string.repair_status_paid));
                    btNewPaymenn.setEnabled(false);
                }
                else{
                    userRequest.setPaid(userRequest.getPaid() + newPayment);
                }
                tvPaid.setText(String.format("%.2f", userRequest.getPaid()) + " RSD");
                tvStatus.setText(userRequest.getStatus());
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.payment_successful));
                dismiss();
            }
        });
    }
}
