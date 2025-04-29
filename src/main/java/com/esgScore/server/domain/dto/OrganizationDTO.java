package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationDTO {
  private String name;
  private String address;
}
