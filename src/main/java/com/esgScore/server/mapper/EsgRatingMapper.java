package com.esgScore.server.mapper;

import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.domain.dto.EsgRatingDTO;

public class EsgRatingMapper {
    public static EsgRatingDTO toDTO(EsgRating esgRating) {
        if (esgRating == null) return null;

        return EsgRatingDTO.builder()
                .id(esgRating.getId())
                .organizationId(esgRating.getOrganizationId())
                .no(esgRating.getNo())
                .esgGrade(esgRating.getEsgGrade())
                .environment(esgRating.getEnvironment())
                .social(esgRating.getSocial())
                .governance(esgRating.getGovernance())
                .year(esgRating.getYear())
                .build();
    }

}
