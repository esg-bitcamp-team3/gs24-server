package com.esgScore.server.domain;


import com.esgScore.server.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

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

  private String loginId;
  private String password;

  private String name;
  private String email;
  private String phone;

  private boolean isAuthorized;

  public User hashPassword(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
    return this;
  }

//  @OneToMany(mappedBy = "user")
//  @Builder.Default
//  private List<Organization> interestOrganization = new ArrayList<>();

  public UserDTO toDTO() { return new UserDTO(name, email, phone); }
}
