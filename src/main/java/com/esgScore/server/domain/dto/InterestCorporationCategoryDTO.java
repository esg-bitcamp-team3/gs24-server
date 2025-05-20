package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.dto.interest.InterestCorporationDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class InterestCorporationCategoryDTO {
  private String id;
  private InterestCorporationDetailDTO interestCorporationDetailDTO;
  private CategoryDTO categoryDTO;
}
