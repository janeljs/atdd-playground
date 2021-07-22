package com.hellowings.web.dto.response;

import com.hellowings.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponse {

    private Long reviewId;
    private UserResponse userResponse;
    private double averageRating;
    private String content;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .userResponse(UserResponse.of(review.getAuthor()))
                .averageRating(review.getAverageRating())
                .content(review.getContent())
                .build();
    }
}
