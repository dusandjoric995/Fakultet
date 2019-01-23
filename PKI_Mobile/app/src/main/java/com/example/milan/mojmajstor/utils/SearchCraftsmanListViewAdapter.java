package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.milan.mojmajstor.R;
import com.example.milan.mojmajstor.fragments.MainFragmentController;

import java.util.ArrayList;

public class SearchCraftsmanListViewAdapter extends ArrayAdapter<User> {
    private ArrayList<User> dataSet;
    Activity thisActivity;

    public SearchCraftsmanListViewAdapter(Activity thisActivity, ArrayList<User> dataSet) {
        super(thisActivity, R.layout.list_view_item_comment, dataSet);
        this.dataSet = dataSet;
        this.thisActivity = thisActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User craftsman = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) thisActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item_search_craftsman, null);
        }

        TextView tvCraftsman = convertView.findViewById(R.id.tvSCICraftsman);
        TextView tvProfession = convertView.findViewById(R.id.tvSCIProfession);

        tvCraftsman.setText(craftsman.getFirstAndLastName());
        tvProfession.setText(craftsman.getProfession());

        if(position % 2 == 0){
            convertView.setBackgroundResource(R.drawable.table_row_even);
        }
        else{
            convertView.setBackgroundResource(R.drawable.table_row_odd);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = new Bundle();
                arguments.putSerializable(thisActivity.getResources().getString(R.string.selected_user), craftsman);
                MainFragmentController.setMainFragment(MainFragmentController.profilePictureFragment, arguments);
            }
        });

        return convertView;
    }
}
