package com.esgScore.server.domain.dto.corporation;

import com.esgScore.server.domain.dto.InterestOrganizationDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CorpWithInterestDTO {
  private CorporationDTO corporation;
  private boolean interested;
}
