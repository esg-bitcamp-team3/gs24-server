package com.esgScore.server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "esgTerms")
public class EsgTerm {
  @Id
  private String id;
  private String term;
  @JsonProperty("english")
  private String description;
  private List<String> tags;
}
