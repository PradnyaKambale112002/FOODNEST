package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.Review;
import com.foodnest.foodnest.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review updateReview(int id, Review review) {

        Review existingReview = reviewRepository.findById(id).orElse(null);

        if (existingReview != null) {

            existingReview.setUser(review.getUser());
            existingReview.setMenuItem(review.getMenuItem());
            existingReview.setRating(review.getRating());
            existingReview.setComment(review.getComment());

            return reviewRepository.save(existingReview);
        }

        return null;
    }

    public String deleteReview(int id) {

        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return "Review Deleted Successfully";
        }

        return "Review Not Found";
    }
}