package com.esgScore.server.domain.dto.rank;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.OrganizationDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankDTO {
  private String organizationName;
  private String esgGrade;
  private int esgScore;

  public static RankDTO toDTO(OrganizationDTO organizationDTO, EsgRatingDTO esgRatingDTO) {
    return RankDTO.builder()
      .organizationName(organizationDTO.getCompanyName())
      .esgGrade(esgRatingDTO.getEsgGrade()).build();
  }
}
