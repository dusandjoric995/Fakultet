package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;

import java.util.ArrayList;

public class CommentsListViewAdapter extends ArrayAdapter<Pair<User, String>> {
    private ArrayList<Pair<User, String>> dataSet;
    Activity thisActivity;

    public CommentsListViewAdapter(Activity thisActivity, ArrayList<Pair<User, String>> dataSet) {
        super(thisActivity, R.layout.list_view_item_comment, dataSet);
        this.dataSet = dataSet;
        this.thisActivity = thisActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pair<User, String> comment = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) thisActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item_comment, null);
        }

        TextView tvFirstAndLastName = convertView.findViewById(R.id.tvCFirstAndLastName);
        TextView tvComment = convertView.findViewById(R.id.tvCComment);

        tvFirstAndLastName.setText(comment.first.getFirstAndLastName());
        tvComment.setText(comment.second);

        return convertView;
    }
}
