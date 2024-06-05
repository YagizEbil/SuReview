package com.sabanciuniv.sureviewapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabanciuniv.sureviewapp.databinding.FragmentSignInBinding;

import java.util.concurrent.ExecutorService;




public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;

    View view;
    private EditText txtUserName;
    private EditText txtEmail;
    private Button btnSignIn;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            String response = msg.obj.toString();

            if(response.startsWith("WRONG")){//TODO change to token

                Toast.makeText(getContext(),"You need to sing in with your SUmail!",Toast.LENGTH_SHORT).show();
            }
            else if(response.startsWith("EXIST")){
                Toast.makeText(getContext(),"This email does not have an account! \n You need to register",Toast.LENGTH_SHORT).show();
            }

            else{
                String token = response;
                Toast.makeText(getContext(),"You signed in successfully!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), HomeScreenContainerActivity.class);
                i.putExtra("TOKEN",token);
                startActivity(i);
            }
            //No user found
            return true;
        }
    });
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        txtEmail = view.findViewById(R.id.txtSuMail);
        txtUserName = view.findViewById(R.id.txtDisplayName);

        btnSignIn = view.findViewById(R.id.btnSignIn);



        binding.btnSignIn.setOnClickListener(v -> {

            String email = txtEmail.getText().toString();
            String displayName = txtUserName.getText().toString();
            StartScreenRepository repo = new StartScreenRepository();
            ExecutorService srv = ((SuReviewApp)getActivity().getApplication()).srv;

            repo.singIn(srv,handler,email,displayName);
        });

        binding.btnRegister.setOnClickListener(v -> {//This func goes to next fragment
            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainerView);

            navController.navigate(R.id.action_signInFragment_to_registerFragment);
        });



        return binding.getRoot();


    }




}