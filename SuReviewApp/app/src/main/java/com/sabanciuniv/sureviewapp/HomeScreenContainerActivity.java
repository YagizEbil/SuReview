package com.sabanciuniv.sureviewapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeScreenContainerActivity extends AppCompatActivity {

    private static final String TAG = "HomeScreenContainer";

    ImageButton btnHome;
    ImageButton btnProfile;
    ImageButton btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_screen_container_activity);

        btnHome = findViewById(R.id.btnHome);
        btnSettings = findViewById(R.id.btnSettings);
        btnProfile = findViewById(R.id.btnProfile);

        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        NavController navController = navHost.getNavController();
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = findViewById(R.id.toolbar3);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        btnHome.setOnClickListener(v -> {
            Log.d(TAG, "Home button clicked");
            navigateToFragment(navController, R.id.action_settingsFragment_to_homeFragment, R.id.action_profileFragment_to_homeFragment, R.id.homeFragment);
        });

        btnSettings.setOnClickListener(v -> {
            Log.d(TAG, "Settings button clicked");
            navigateToFragment(navController, R.id.action_homeFragment_to_settingsFragment, R.id.action_profileFragment_to_settingsFragment, R.id.settingsFragment);
        });

        btnProfile.setOnClickListener(v -> {
            Log.d(TAG, "Profile button clicked");
            navigateToFragment(navController, R.id.action_settingsFragment_to_profileFragment, R.id.action_homeFragment_to_profileFragment, R.id.profileFragment);
        });
    }

    private void navigateToFragment(NavController navController, int actionFromFirst, int actionFromSecond, int targetFragmentId) {
        int currentDestinationId = navController.getCurrentDestination().getId();
        Log.d(TAG, "Current destination: " + getResources().getResourceEntryName(currentDestinationId));

        if (currentDestinationId == targetFragmentId) {
            Log.d(TAG, "Already showing " + getResources().getResourceEntryName(targetFragmentId));
            return;
        }

        if (currentDestinationId == R.id.settingsFragment) {
            navController.navigate(actionFromFirst);
        } else if (currentDestinationId == R.id.profileFragment) {
            navController.navigate(actionFromSecond);
        } else if (currentDestinationId == R.id.homeFragment) {
            if (targetFragmentId == R.id.settingsFragment) {
                navController.navigate(actionFromFirst);
            } else if (targetFragmentId == R.id.profileFragment) {
                navController.navigate(actionFromSecond);
            }
        }
    }
}
