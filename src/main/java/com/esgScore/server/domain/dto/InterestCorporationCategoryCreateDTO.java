package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class InterestCorporationCategoryCreateDTO {
  private String id;
  private String interestCorporationId;
  private String categoryId;
}
