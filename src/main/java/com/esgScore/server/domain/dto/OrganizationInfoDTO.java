package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.InterestOrganization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class OrganizationInfoDTO {
  private String id;
  private OrganizationDTO organization;
  private LocalDateTime checkTime;

  public static OrganizationInfoDTO toDTO(InterestOrganization interestOrganization, OrganizationDTO organization) {
    return OrganizationInfoDTO.builder()
      .id(interestOrganization.getId())
      .organization(organization)
      .checkTime(interestOrganization.getCheckTime())
      .build();
  }
}
