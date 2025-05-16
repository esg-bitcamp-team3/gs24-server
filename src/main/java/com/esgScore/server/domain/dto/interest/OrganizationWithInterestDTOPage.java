package com.esgScore.server.domain.dto.interest;

import com.esgScore.server.domain.dto.OrganizationWithInterestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrganizationWithInterestDTOPage {
  private List<OrganizationWithInterestDTO> organizationWithInterestDTOList;
  private boolean hasMore;
}
