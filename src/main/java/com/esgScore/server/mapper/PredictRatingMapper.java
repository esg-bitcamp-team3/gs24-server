package com.esgScore.server.mapper;

import com.esgScore.server.domain.PredictRating;
import com.esgScore.server.domain.dto.PredictRatingDTO;

public class PredictRatingMapper {
    public static PredictRatingDTO toDTO(PredictRating predictRating) {
        if (predictRating == null) return null;

        return PredictRatingDTO.builder()
                .id(predictRating.getId())
                .companyName(predictRating.getCompanyName())
                .date(predictRating.getDate())
                .eScore(predictRating.getEScore())
                .sScore(predictRating.getSScore())
                .gScore(predictRating.getGScore())
                .build();
    }



}
