package com.example.milan.mojmajstor.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.utils.Data;
import com.example.milan.mojmajstor.utils.RepairRequest;
import com.example.milan.mojmajstor.utils.ToastWriter;
import com.example.milan.mojmajstor.utils.User;

public class CommentDialog extends Dialog {

    private RepairRequest repairRequest;
    private Button btCancel;
    private Button btComment;
    private Activity thisActivity;
    private EditText etComment;
    private Data data;

    public CommentDialog(Activity activity, RepairRequest repairRequest){
        super(activity);
        this.repairRequest = repairRequest;
        thisActivity = activity;
        data = Data.getInstance();
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
                repairRequest.getCraftsman().addComent(new Pair<>(data.currentUser, etComment.getText().toString()));
                ToastWriter.write(thisActivity, thisActivity.getResources().getString(R.string.comment_added));
                dismiss();
            }
        });


    }
}
