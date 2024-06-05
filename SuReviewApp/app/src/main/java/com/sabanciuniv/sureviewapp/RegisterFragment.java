package com.sabanciuniv.sureviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sabanciuniv.sureviewapp.databinding.FragmentRegisterBinding;

import java.util.concurrent.ExecutorService;


public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            String response = msg.obj.toString();

            if(response.startsWith("WRONG")){
                Toast.makeText(getContext(),"You need to use your SUmail to sign in",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(),"You have registered successfully! \n You can go back and sign in ",Toast.LENGTH_SHORT).show();
            }

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(getLayoutInflater());


        binding.btnSignUp.setOnClickListener(v -> {
            String Username = binding.txtUsername.getText().toString();
            String SuMailRegister = binding.txtSuMailRegister.getText().toString();

            StartScreenRepository repo = new StartScreenRepository();
            ExecutorService srv = ((SuReviewApp)getActivity().getApplication()).srv;
            repo.register(srv,handler,SuMailRegister,Username);
        });



        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}