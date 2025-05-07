package com.esgScore.server.domain;

import com.esgScore.server.domain.dto.OrganizationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "organization")
public class Organization {
  @Id
  private String id;

  @Field("기업명")
  private String companyName;

  @Field("기업코드")
  private String companyCode;


}
