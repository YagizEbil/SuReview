package com.sabanciuniv.sureviewapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewsAdapterHome extends RecyclerView.Adapter<ReviewsAdapterHome.ReviewViewHolder> {

    private List<Review> reviewList;

    public ReviewsAdapterHome(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_layout, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.textViewReviewerName.setText(review.getDisplayName());
        holder.textViewProfessorCourse.setText(review.getProfessor() + " - " + review.getCourse());
        holder.textViewContent.setText(review.getContent());
        // If you have an avatar URL or resource, set it here
        // holder.imageViewAvatar.setImageResource(R.drawable.avatar_placeholder); // Example
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView textViewReviewerName;
        TextView textViewProfessorCourse;
        TextView textViewContent;
        ImageView imageViewAvatar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewReviewerName = itemView.findViewById(R.id.textViewReviewerName);
            textViewProfessorCourse = itemView.findViewById(R.id.textViewProfessorCourse);
            textViewContent = itemView.findViewById(R.id.textViewContent);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
        }
    }
}
