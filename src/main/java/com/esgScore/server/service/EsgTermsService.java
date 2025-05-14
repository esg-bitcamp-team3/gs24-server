package com.esgScore.server.service;

import com.esgScore.server.domain.EsgRating;
import com.esgScore.server.domain.EsgTerm;
import com.esgScore.server.domain.Organization;
import com.esgScore.server.domain.dto.EsgTermDTO;
import com.esgScore.server.repository.main.EsgTermsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EsgTermsService {
  private final EsgTermsRepository esgTermsRepository;

  public void importEsgTermsData (MultipartFile file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    List<EsgTermDTO> esgTerms = objectMapper.readValue(file.getInputStream(), new TypeReference<List<EsgTermDTO>>() {});

    for(EsgTermDTO dto : esgTerms) {
      EsgTerm esgTerm = EsgTerm.builder()
        .term(dto.getTerm())
        .description(dto.getDescription())
        .tags(dto.getTags())
        .build();

      esgTermsRepository.save(esgTerm);
    }
  }
  public List<EsgTermDTO> getAllEsgTerms() {
    return esgTermsRepository.findAll().stream().map(EsgTermDTO::toDTO).toList();
  }
}
