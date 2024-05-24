package com.sabanciuniv.sureviewapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabanciuniv.sureviewapp.databinding.FragmentSignInBinding;


public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(getActivity().getLayoutInflater(),container,false);

        binding.btnSignIn.setOnClickListener(v -> {// This function runs when sign in is presses


        });

        binding.btnRegister.setOnClickListener(v -> {//This func goes to next fragment
            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainerView);

            navController.navigate(R.id.action_signInFragment_to_registerFragment);
        });

        UserViewModel userMode = new ViewModelProvider(getActivity()).get(UserViewModel.class);


        userMode.getUserData().observe(getActivity(),user -> { //This func runs whenever a new user presses the register button
            Toast.makeText(getActivity().getApplicationContext(), "You can now sign in!",Toast.LENGTH_SHORT);
        });

        return binding.getRoot();


    }
}