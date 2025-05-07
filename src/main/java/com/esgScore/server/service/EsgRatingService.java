package com.esgScore.server.service;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.EsgRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ESG 등급 데이터를 조회하는 비즈니스 로직 담당 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class EsgRatingService {
    private final EsgRatingRepository esgRatingRepository;
    private final OrganizationService organizationService;

    /**
     * 특정 조직 ID로 ESG 등급 데이터를 조회하여 DTO 목록으로 변환하여 반환
     * @param organizationId 조회할 조직의 고유 식별자(ID)
     * @return 조회된 ESG 등급 데이터를 변환한 EsgRatingDTO 리스트
     */
    public List<EsgRatingDTO> getEsgRatingList(String organizationId) {
        OrganizationDTO organization = organizationService.getById(organizationId);

        return esgRatingRepository.findByOrganizationId(organizationId).stream()
                .map(EsgRatingDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<EsgRatingDTO> getEsgRatingListByOrganizationCode(String organizationCode) {
        OrganizationDTO organization = organizationService.getByCode(organizationCode);

        return esgRatingRepository.findByOrganizationId(organization.getId()).stream()
                .map(EsgRatingDTO::toDTO)
                .collect(Collectors.toList());
    }
}
