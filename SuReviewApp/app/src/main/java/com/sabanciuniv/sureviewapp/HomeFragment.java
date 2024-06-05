package com.sabanciuniv.sureviewapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sabanciuniv.sureviewapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    View view;

    private RecyclerView recyclerViewReviews;
    private ReviewsAdapterHome reviewsAdapterHome;
    private List<Review> reviewList;

    private SharedViewModel sharedViewModel;

    private String token;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.obj instanceof List) {
                List<Review> reviews = (List<Review>) msg.obj;
                reviewsAdapterHome = new ReviewsAdapterHome(reviews);
                recyclerViewReviews.setAdapter(reviewsAdapterHome);
            } else if (msg.obj instanceof String) {
                String errorMessage = (String) msg.obj;
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
            return true;
        }
    });



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(getContext()));

        HomeScreenRepository repo = new HomeScreenRepository();
        ExecutorService srv = ((SuReviewApp)getActivity().getApplication()).srv;

        reviewList = new ArrayList<>();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Observe the token
        sharedViewModel.getToken().observe(getViewLifecycleOwner(), token -> {
            this.token = token;
        });
        repo.getReviews(srv,handler,"CO3543",token);


        reviewList.add(new Review("tolga.tektunali", "This app is great!", 5, "John Smith", "CS310", "R132"));
        reviewList.add(new Review("test.name", "I love using this app.", 4, "John Doe", "CS306","R455"));
        reviewList.add(new Review("test.name2", "Needs some improvements.", 4, "Jane Smith", "CS408","R355"));

        reviewsAdapterHome = new ReviewsAdapterHome(reviewList);
        recyclerViewReviews.setAdapter(reviewsAdapterHome);

        return binding.getRoot();
    }
}
