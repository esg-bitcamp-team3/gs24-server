package com.esgScore.server.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationWithInterestDTO {
  private OrganizationDTO organization;
  private boolean isInterested;
}
