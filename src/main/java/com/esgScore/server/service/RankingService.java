package com.esgScore.server.service;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.rank.RankDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {
  private final EsgRatingService esgRatingService;
  private final OrganizationService organizationService;

  public List<RankDTO> organizationRankByScore() {
    List<EsgRatingDTO> esgRatingDTOList = esgRatingService.getAllEsgRatingListByYear(2023);

    esgRatingDTOList = esgRatingDTOList.stream()
      .sorted((o1, o2) -> o1.getNo().compareTo(o2.getNo()))   // ----> score 로 나중에 변경
      .toList()
      .subList(0, 10);

    return esgRatingDTOList.stream()
      .map(esgRatingDTO ->
        RankDTO.toDTO(organizationService.getById(esgRatingDTO.getCorporationId()),esgRatingDTO))
      .toList();
  }
}
