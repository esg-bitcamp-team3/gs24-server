package com.esgScore.server.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrganizationEsgRatingListDTO {
    private OrganizationDTO organization;
    private List<EsgRatingDTO> ratings;
}
