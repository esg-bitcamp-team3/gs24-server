package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserOrganizationListDTO {
  private UserDTO user;
  private List<OrganizationInfoDTO> organizationList;


  public static UserOrganizationListDTO toDTO(UserDTO user, List<OrganizationInfoDTO> organizationList) {
    return UserOrganizationListDTO.builder()
        .user(user)
        .organizationList(organizationList)
        .build();
  }
}
