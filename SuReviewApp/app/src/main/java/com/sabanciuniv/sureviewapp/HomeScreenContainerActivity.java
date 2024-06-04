package com.sabanciuniv.sureviewapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeScreenContainerActivity extends AppCompatActivity {
    ImageButton btnHome;

    ImageButton btnProfile;
    ImageButton btnSettings;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_screen_container_activity);

        btnHome = findViewById(R.id.btnHome);
        btnSettings = findViewById(R.id.btnSettings);
        btnProfile = findViewById(R.id.btnProfile);


        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        NavController navController = navHost.getNavController();
        AppBarConfiguration appBarConfiguration=
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = findViewById(R.id.toolbar3);

        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);

        btnHome.setOnClickListener(v -> {

        });

        btnSettings.setOnClickListener(v -> {

        });

        btnProfile.setOnClickListener(v -> {

        });
    }
}