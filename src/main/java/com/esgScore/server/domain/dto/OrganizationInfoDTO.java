package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class OrganizationInfoDTO {
  private String organizationId;
  private String name;
  private LocalDateTime checkTime;
}
