package com.sabanciuniv.sureviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.concurrent.ExecutorService;


public class StartScreenContainerActivity extends AppCompatActivity {


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
