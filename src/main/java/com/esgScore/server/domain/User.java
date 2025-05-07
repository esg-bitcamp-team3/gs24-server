package com.esgScore.server.domain;


import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
  @Id
  private String id;
  private String username;
  private String password;

  private String name;
  private String email;
  private String phone;

  private boolean isAuthorized;

  public User hashPassword(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
    return this;
  }

  public UserDTO toDTO() {
    return UserDTO.builder()
      .id(id)
      .username(username)
      .name(name)
      .email(email)
      .phone(phone)
      .build();
  }
}
