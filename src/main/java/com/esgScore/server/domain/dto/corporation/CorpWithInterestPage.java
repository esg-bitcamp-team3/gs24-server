package com.esgScore.server.domain.dto.corporation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CorpWithInterestPage {
  private List<CorpWithInterestDTO> corpWithInterestDTOList;
  private boolean hasMore;
}
