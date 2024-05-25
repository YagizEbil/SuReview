package com.sabanciuniv.sureviewapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;

    View view;
    private EditText txtUserName;
    private EditText txtEmail;
    private Button btnSignIn;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        txtEmail = view.findViewById(R.id.txtSuMail);
        txtUserName = view.findViewById(R.id.txtUserName);
        btnSignIn = view.findViewById(R.id.btnSignIn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://your-backend-url/api/") // Replace with your backend URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        btnSignIn.setOnClickListener(v -> {
            String username = txtUserName.getText().toString();
            String email = txtEmail.getText().toString();
            signIn(username, email);
        });

        binding.btnRegister.setOnClickListener(v -> {//This func goes to next fragment
            NavController navController =
                    Navigation.findNavController(getActivity(),R.id.fragmentContainerView);

            navController.navigate(R.id.action_signInFragment_to_registerFragment);
        });

        UserViewModel userMode = new ViewModelProvider(getActivity()).get(UserViewModel.class);


        userMode.getUserData().observe(getActivity(),user -> { //This func runs whenever a new user presses the register button
            Toast.makeText(getActivity(), "You can now sign in!",Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();


    }

    private void signIn(String username, String email) {
        LoginRequest loginRequest = new LoginRequest(username, email);

        apiService.signIn(loginRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Sign-in successful", Toast.LENGTH_SHORT).show();
                    //TODO Need to start a Activity to actual page here
                    //Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_otherFragment);
                } else {
                    Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}