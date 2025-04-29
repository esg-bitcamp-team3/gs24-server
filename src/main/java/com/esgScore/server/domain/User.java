package com.esgScore.server.domain;


import com.esgScore.server.domain.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id
  private ObjectId id;

  private String loginId;
  private String password;

  private String name;
  private String email;

//  @OneToMany(mappedBy = "user")
//  @Builder.Default
//  private List<Organization> interestOrganization = new ArrayList<>();

  public UserDTO toDTO() { return new UserDTO(name, email); }
}
