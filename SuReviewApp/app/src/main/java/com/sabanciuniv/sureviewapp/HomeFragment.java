package com.sabanciuniv.sureviewapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sabanciuniv.sureviewapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    View view;

    private RecyclerView recyclerViewReviews;
    private ReviewsAdapter reviewsAdapter;
    private List<Review> reviewList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        view = binding.getRoot();

        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(getContext()));

        User test = new User("tolga.tektunali@sabanciuniv.edu","tolga.tektunali");
        reviewList = new ArrayList<>();
        reviewList.add(new Review(test,"Review 1: This app is great!",5));
        reviewList.add(new Review(test,"Review 2: I love using this app.",4));
        reviewList.add(new Review(test,"Review 3: Needs some improvements.",4));

        reviewsAdapter = new ReviewsAdapter(reviewList);
        recyclerViewReviews.setAdapter(reviewsAdapter);

        return binding.getRoot();
    }
}