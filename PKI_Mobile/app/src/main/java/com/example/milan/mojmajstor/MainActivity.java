package com.example.milan.mojmajstor;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.milan.mojmajstor.fragments.MainFragmentController;
import com.example.milan.mojmajstor.utils.Data;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SmoothActionBarDrawerToggle toggle;
    private AppCompatActivity thisActivity;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragmentController.initialize(getSupportFragmentManager());
        thisActivity = this;
        data = Data.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.activity_main);
        toggle = new SmoothActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (menuItem.getItemId()){
                    case R.id.menu_item_drawer_repair_requests:{
                        toggle.setNextAction(MainFragmentController.userRequestFragment);
                        break;
                    }
                    case R.id.menu_item_drawer_search_craftsman:{
                        toggle.setNextAction(MainFragmentController.craftsmanRequestFragment);
                        break;
                    }
                    case R.id.menu_item_drawer_logout:{
                        toggle.setNextAction(new LoginActivity());
                        break;
                    }
                }
                return true;
            }
        });

        Menu navigationDrawerMenu = navigationView.getMenu();
        navigationDrawerMenu.clear();
        switch (data.currentUser.getUserType()){
            case CRAFTSMAN:{
                MainFragmentController.setMainFragment(MainFragmentController.craftsmanRequestFragment);
                break;
            }
            case USER:{
                navigationDrawerMenu.add(R.id.menu_item_drawer_group_top, R.id.menu_item_drawer_repair_requests, Menu.NONE, getString(R.string.user_requests)).setIcon(R.drawable.ic_repair_requests).setCheckable(true);
                navigationDrawerMenu.add(R.id.menu_item_drawer_group_top, R.id.menu_item_drawer_search_craftsman, Menu.NONE, getString(R.string.search_craftsman)).setIcon(R.drawable.ic_search_craftsman).setCheckable(true);
                navigationView.setCheckedItem(R.id.menu_item_drawer_repair_requests);
                MainFragmentController.setMainFragment(MainFragmentController.userRequestFragment);
                break;
            }
            case GUEST:{

                break;
            }
        }
        navigationDrawerMenu.add(R.id.menu_item_drawer_group_bottom, R.id.menu_item_drawer_logout, Menu.NONE, getString(R.string.logout)).setIcon(R.drawable.ic_logout);
        TextView tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileName.setText(data.currentUser.getName() + " " + data.currentUser.getSurname());
    }

    private class SmoothActionBarDrawerToggle extends ActionBarDrawerToggle {

        private Object nextAction;

        public SmoothActionBarDrawerToggle(AppCompatActivity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
            nextAction = null;
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            super.onDrawerStateChanged(newState);

            if (nextAction != null && newState == DrawerLayout.STATE_IDLE) {
                if(nextAction instanceof LoginActivity){
                    Intent loginActivityIntent = new Intent(thisActivity, LoginActivity.class);
                    thisActivity.startActivity(loginActivityIntent);
                    thisActivity.setResult(RESULT_OK);
                    thisActivity.finish();
                }
                else if (nextAction instanceof Fragment){
                    MainFragmentController.setMainFragment((Fragment) nextAction);
                }
                nextAction = null;
            }
        }

        public void setNextAction(Object nextAction){
            this.nextAction = nextAction;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
