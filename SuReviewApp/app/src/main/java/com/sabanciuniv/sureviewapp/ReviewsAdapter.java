package com.sabanciuniv.sureviewapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<Review> reviewList;

    public ReviewsAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_layout, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.contentTextView.setText(review.getContent());
        holder.ratingTextView.setText(String.valueOf(review.getRating()));
        holder.professorTextView.setText(review.getProfessor());
        holder.courseTextView.setText(review.getCourse());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, ratingTextView,courseTextView,professorTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            contentTextView = itemView.findViewById(R.id.reviewContentTextView);
            ratingTextView = itemView.findViewById(R.id.reviewRatingTextView);
            courseTextView = itemView.findViewById(R.id.courseNameTextView);
            professorTextView = itemView.findViewById(R.id.professorNameTextView);

        }
    }
}
