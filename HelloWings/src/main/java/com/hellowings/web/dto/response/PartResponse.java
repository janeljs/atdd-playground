package com.hellowings.web.dto.response;

import com.hellowings.domain.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PartResponse {
    private final Long partId;
    private final String partName;

    public static PartResponse of(Part part) {
        return PartResponse.builder()
                .partId(part.getId())
                .partName(part.getName())
                .build();
    }
}
