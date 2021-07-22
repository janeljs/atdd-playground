package com.hellowings.web.dto.request;

import lombok.Getter;

@Getter
public class ReviewRequest {
    private double averageRating;
    private String content;
}
