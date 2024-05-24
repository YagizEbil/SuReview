package com.sabanciuniv.sureviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.sabanciuniv.sureviewapp.databinding.LayoutStartContainerActivityBinding;



public class StartContainerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_start_container_activity);

        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHost.getNavController();
        AppBarConfiguration appBarConfiguration=
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = findViewById(R.id.toolbar2);

        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);






    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i("DEV","-------------------ONSTART---------------");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("DEV","-------------------ONRESUME---------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DEV","-------------------ONPAUSE---------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DEV","-------------------ONSTOP---------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DEV","-------------------ONDESTROY---------------");
    }
}
