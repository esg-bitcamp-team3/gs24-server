package com.esgScore.server.service;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.main.EsgRatingRepository;
import com.esgScore.server.domain.dto.CorporationEsgRatingListDTO;
import com.esgScore.server.mapper.EsgRatingMapper;
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
public class EsgRatingService {
    private final EsgRatingRepository esgRatingRepository;
    private final CorporationService corporationService;

    public CorporationEsgRatingListDTO getEsgRatingListByCorporationId(String corporationId) {
        CorporationDTO corporation = corporationService.getById(corporationId);
        log.info("Get EsgRatingListByCorporationId: {}", corporationId);

        List<EsgRatingDTO> esgRatingDTOList = esgRatingRepository.findByCorporationId(corporationId).stream()
                .map(EsgRatingMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Get : {}", esgRatingDTOList);

        return CorporationEsgRatingListDTO.builder()
                .corporation(corporation)
                .ratings(esgRatingDTOList)
                .build();
    }

    public List<EsgRatingDTO> getEsgRatingListByCorporationCode(String corporationCode) {
        CorporationDTO corporation = corporationService.getByStockCode(corporationCode);

        return esgRatingRepository.findByCorporationId(corporation.getId()).stream()
                .map(EsgRatingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EsgRatingDTO> getAllEsgRatingListByYear(int year) {
        List<EsgRatingDTO> esgRatingDTOList = esgRatingRepository.findByYear(year).stream().map(EsgRatingMapper::toDTO).toList();
        if(esgRatingDTOList.isEmpty()) {
            throw new NotFoundException("데이터를 찾을 수 없습니다.");
        }
      return esgRatingDTOList;
    }
}
