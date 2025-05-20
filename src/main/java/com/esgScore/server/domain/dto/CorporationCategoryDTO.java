package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CorporationCategoryDTO {
  private String id;
  private String corporationId;
  private String categoryId;
}
