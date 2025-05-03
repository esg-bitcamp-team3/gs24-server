package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserOrganizationListDTO {
  private String userId;
  private String username;
  private List<OrganizationInfoDTO> organizationList;
}
