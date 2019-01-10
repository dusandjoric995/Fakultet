package com.example.milan.mojmajstor.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.milan.mojmajstor.LoginActivity;
import com.example.milan.mojmajstor.R;

public class MainFragmentController {

    private static FragmentManager fragmentManager;
    public static UserRequestFragment userRequestFragment;
    public static CraftsmanRequestFragment craftsmanRequestFragment;

    public static void initialize(FragmentManager fm){
        fragmentManager = fm;
        userRequestFragment = new UserRequestFragment();
        craftsmanRequestFragment = new CraftsmanRequestFragment();
    }

    public static void setMainFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}
