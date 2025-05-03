package com.esgScore.server.domain;

import com.esgScore.server.domain.dto.OrganizationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "InterestOrganization")
public class InterestOrganization {
  @Id
  private String id;

  private String organizationId;
  private String userId;
  private LocalDateTime checkTime;
}
