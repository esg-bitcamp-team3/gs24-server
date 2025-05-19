package com.esgScore.server.domain.dto.interest;

import com.esgScore.server.domain.InterestCorporation;
import com.esgScore.server.domain.InterestOrganization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestCorporationDTO {
  private String id;
  private String userId;
  private String corporationId;
  private LocalDateTime checkTime;

  public static InterestCorporationDTO toDTO(InterestCorporation interestOrganization) {
    return InterestCorporationDTO.builder()
      .id(interestOrganization.getId())
      .corporationId(interestOrganization.getCorporationId())
      .userId(interestOrganization.getUserId())
      .checkTime(interestOrganization.getCheckTime())
      .build();
  }
}
