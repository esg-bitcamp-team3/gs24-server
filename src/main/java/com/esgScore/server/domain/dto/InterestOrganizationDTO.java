package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.InterestOrganization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestOrganizationDTO {
  private String id;
  private String userId;
  private String organizationId;
  private LocalDateTime checkTime;

  public static InterestOrganizationDTO toDTO(InterestOrganization interestOrganization) {
    return InterestOrganizationDTO.builder()
      .id(interestOrganization.getId())
      .organizationId(interestOrganization.getOrganizationId())
      .userId(interestOrganization.getUserId())
      .checkTime(interestOrganization.getCheckTime())
      .build();
  }
}
