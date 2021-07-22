package com.hellowings.web.dto.response;

import com.hellowings.domain.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubCategoryResponse {
    private final Long subCategoryId;
    private final String title;

    public static SubCategoryResponse of(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .subCategoryId(subCategory.getId())
                .title(subCategory.getTitle())
                .build();
    }
}
