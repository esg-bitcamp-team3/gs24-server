package com.esgScore.server.service;

import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.repository.EsgRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EsgRatingService {
    private final EsgRatingRepository esgRatingRepository;
    private final OrganizationService organizationService;

    public List<EsgRatingDTO> getEsgRatingList(String organizationId) {
        OrganizationDTO organization = organizationService.getById(organizationId);

        return esgRatingRepository.findByOrganizationId(organizationId).stream()
                .map(EsgRatingDTO::toDTO)
                .collect(Collectors.toList());
    }
}
