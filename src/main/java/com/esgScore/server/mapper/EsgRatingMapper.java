package com.esgScore.server.mapper;

import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.domain.dto.EsgRatingDTO;

public class EsgRatingMapper {
    public static EsgRatingDTO toDTO(EsgRating esgRating) {
        if (esgRating == null) return null;

        return EsgRatingDTO.builder()
                .id(esgRating.getId())
                .corporationId(esgRating.getCorporationId())
                .no(esgRating.getNo())
                .esgGrade(esgRating.getEsgGrade())
                .environment(esgRating.getEnvironment())
                .social(esgRating.getSocial())
                .governance(esgRating.getGovernance())
                .year(esgRating.getYear())
                .build();
    }

    public static EsgRating fromDTO(EsgRatingDTO esgRatingDTO) {
        if (esgRatingDTO == null) return null;

        return EsgRating.builder()
                .id(esgRatingDTO.getId())
                .corporationId(esgRatingDTO.getCorporationId())
                .no(esgRatingDTO.getNo())
                .esgGrade(esgRatingDTO.getEsgGrade())
                .environment(esgRatingDTO.getEnvironment())
                .social(esgRatingDTO.getSocial())
                .governance(esgRatingDTO.getGovernance())
                .year(esgRatingDTO.getYear())
                .build();
    }


}
