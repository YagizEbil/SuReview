package com.sabanciuniv.sureviewapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sabanciuniv.sureviewapp.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    private TextView emailTextView, usernameTextView, reviewCountTextView;
    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;
    private List<Review> reviewList;

    View view;
    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        emailTextView = view.findViewById(R.id.emailTextView);
        usernameTextView = view.findViewById(R.id.usernameTextView);
        reviewCountTextView = view.findViewById(R.id.reviewCountTextView);
        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);

        User test = new User("tolga.tektunali@sabanciuniv.edu","tolga.tektunali");


        reviewList = new ArrayList<>();
        reviewList.add(new Review(test, "I love this app!", 5));
        reviewList.add(new Review(test, "It works well.", 4));

        emailTextView.setText("Email: " + test.getEmail());
        usernameTextView.setText("Username: " + test.getDisplayName());
        reviewCountTextView.setText("Number of Reviews: " + reviewList.size());

        reviewsAdapter = new ReviewsAdapter(reviewList);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        return binding.getRoot();
    }
}