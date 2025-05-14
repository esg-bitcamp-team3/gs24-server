package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.EsgTerm;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EsgTermDTO {
  private String id;
  private String term;
  @JsonProperty("english")
  private String description;
  private List<String> tags;

  public static EsgTermDTO toDTO(EsgTerm esgTerm) {
    return EsgTermDTO.builder()
      .term(esgTerm.getTerm())
      .description(esgTerm.getDescription())
      .tags(esgTerm.getTags())
      .build();
  }
}
