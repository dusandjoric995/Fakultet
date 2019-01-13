package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.ToastWriter;
import com.example.milan.mojmajstor.utils.UserRequest;

import java.util.ArrayList;

public class CommentDialog extends Dialog {

    private UserRequest userRequest;
    private Button btCancel;
    private Button btComment;
    private Activity thisActivity;
    private EditText etComment;

    public CommentDialog(Activity activity, UserRequest userRequest){
        super(activity);
        this.userRequest = userRequest;
        thisActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);

        btCancel = findViewById(R.id.btCDCancel);
        btComment = findViewById(R.id.btCDComment);
        etComment = findViewById(R.id.etCDComment);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRequest.getCraftsman().addComent(etComment.getText().toString());
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.comment_added));
                dismiss();
            }
        });


    }
}
