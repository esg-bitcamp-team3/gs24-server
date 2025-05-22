package com.esgScore.server.service;

import com.esgScore.server.domain.PredictRating;
import com.esgScore.server.domain.dto.CorporationEsgRatingListDTO;
import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.PredictRatingDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.EsgRatingMapper;
import com.esgScore.server.mapper.PredictRatingMapper;
import com.esgScore.server.repository.main.EsgRatingRepository;
import com.esgScore.server.repository.main.PredictRatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ESG 등급 데이터를 조회하는 비즈니스 로직 담당 서비스 클래스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PredictRatingService {
    private final PredictRatingRepository predictRatingRepository;

    public PredictRatingDTO getPredictRating(String companyName) {
        PredictRating predictRating = predictRatingRepository.findByCompanyName(companyName).orElseThrow(() ->  new NotFoundException("No predicted ESG ratings"));

        return PredictRatingMapper.toDTO(predictRating);

    }

    public List<PredictRatingDTO> getAll() {

        return predictRatingRepository.findAll().stream().map(PredictRatingMapper::toDTO).toList();
    }
}
