package com.esgScore.server.domain.dto.interest;

import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestCorporationDetailDTO {
  private String id;
  private String userId;
  private CorporationDTO corporation;

}
