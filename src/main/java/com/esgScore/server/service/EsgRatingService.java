package com.esgScore.server.service;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.repository.EsgRatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EsgRatingService {
    private final EsgRatingRepository esgRatingRepository;
    private final OrganizationService organizationService;

    public List<EsgRatingDTO> getEsgRatingList(String organizationId) {
        OrganizationDTO organization = organizationService.getById(organizationId);

        return esgRatingRepository.findByOrganizationId(organizationId).stream()
                .map(EsgRatingDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<EsgRatingDTO> getAllEsgRatingListByYear(int year) {
        List<EsgRatingDTO> esgRatingDTOList = esgRatingRepository.findByYear(year).stream().map(EsgRatingDTO::toDTO).toList();
        if(esgRatingDTOList.isEmpty()) {
            throw new NotFoundException("데이터를 찾을 수 없습니다.");
        }
      return esgRatingDTOList;
    }
}
