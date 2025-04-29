package com.esgScore.server.domain.dto;


import com.esgScore.server.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
  private String name;
  private String email;
//  private List<Organization> interestOrganization;
}
