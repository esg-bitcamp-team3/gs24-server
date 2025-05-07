package com.esgScore.server.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
  private String id;
  private String username;
  private String name;
  private String email;
  private String phone;
}
