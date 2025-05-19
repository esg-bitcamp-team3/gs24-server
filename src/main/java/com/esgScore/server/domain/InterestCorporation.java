package com.esgScore.server.domain;

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
@Document(collection = "InterestCorporation")
public class InterestCorporation {
  @Id
  private String id;

  private String userId;
  private String corporationId;

  private LocalDateTime checkTime;
}
