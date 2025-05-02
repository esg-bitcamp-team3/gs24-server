package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.EsgRating;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EsgRatingDTO {
    private String id;

    private String organizationID;

    private Integer no;

    private String esgGrade;

    private String environment;

    private String social;

    private String governance;

    private Integer year;

    public static EsgRatingDTO toDTO(EsgRating esgRating) {
        return EsgRatingDTO.builder()
                .id(esgRating.getId())
                .organizationID(esgRating.getOrganizationId())
                .no(esgRating.getNo())
                .esgGrade(esgRating.getEsgGrade())
                .environment(esgRating.getEnvironment())
                .social(esgRating.getSocial())
                .governance(esgRating.getGovernance())
                .year(esgRating.getYear())
                .build();
    }
}
