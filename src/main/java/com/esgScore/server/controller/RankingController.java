package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.rank.RankDTO;
import com.esgScore.server.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {
  private final RankingService rankingService;

  @GetMapping("/score")
  public List<RankDTO> getOrganizationRankByScore() {
    return rankingService.organizationRankByScore();
  }
}
