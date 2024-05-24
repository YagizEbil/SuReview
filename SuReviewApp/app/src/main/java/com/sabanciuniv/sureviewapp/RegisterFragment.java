package com.sabanciuniv.sureviewapp;

import android.app.Person;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sabanciuniv.sureviewapp.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(getLayoutInflater());


        String Username = binding.txtUsername.getText().toString();
        String SuMailRegister = binding.txtSuMailRegister.getText().toString();
        String PassRegister = binding.txtPassRegister.getText().toString();

        binding.btnSignUp.setOnClickListener(v -> {
            User u = new User(binding.txtSuMailRegister.getText().toString(),binding.txtUsername.getText().toString(),binding.txtPassRegister.getText().toString());

            //UserViewModel userModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

            //userModel.setUserData(u);

            NavController navController = Navigation.findNavController(getActivity(),R.id.fragmentContainerView);

            navController.popBackStack();

        });



        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}