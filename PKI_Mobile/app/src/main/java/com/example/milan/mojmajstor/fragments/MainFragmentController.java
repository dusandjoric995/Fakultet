package com.example.milan.mojmajstor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.R;

public class MainFragmentController {

    private static FragmentManager fragmentManager;
    public static UserRequestFragment userRequestFragment;
    public static CraftsmanRequestFragment craftsmanRequestFragment;
    public static RepairRequestFragment repairRequestFragment;

    public static void initialize(FragmentManager fm){
        fragmentManager = fm;
        userRequestFragment = new UserRequestFragment();
        craftsmanRequestFragment = new CraftsmanRequestFragment();
        repairRequestFragment = new RepairRequestFragment();
    }

    public static void setMainFragment(Fragment fragment, Bundle bundle){
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}
