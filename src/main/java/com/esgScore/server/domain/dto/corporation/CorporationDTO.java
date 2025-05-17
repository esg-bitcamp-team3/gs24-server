package com.esgScore.server.domain.dto.corporation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CorporationDTO {
  private String id;
  private String corpCode;
  private String corpName;
}
