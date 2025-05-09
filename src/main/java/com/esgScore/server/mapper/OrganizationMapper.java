package com.esgScore.server.mapper;

import com.esgScore.server.domain.Organization;
import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.OrganizationDTO;
import com.esgScore.server.domain.dto.OrganizationEsgRatingListDTO;

import java.util.List;

public class OrganizationMapper {
    public static OrganizationDTO toDTO(Organization organization) {
        return OrganizationDTO.builder()
                .id(organization.getId())
                .companyName(organization.getCompanyName())
                .companyCode(organization.getCompanyCode())
                .build();
    }

    public static Organization fromDTO(OrganizationDTO dto) {
        if (dto == null) {
            return null;
        }

        return Organization.builder()
                .id(dto.getId())
                .companyName(dto.getCompanyName())
                .companyCode(dto.getCompanyCode())
                .build();
    }

    public static OrganizationEsgRatingListDTO toEsgRatingListDTO(OrganizationDTO organizationDTO, List<EsgRatingDTO> ratings) {
        return OrganizationEsgRatingListDTO.builder()
                .organization(organizationDTO)
                .ratings(ratings)
                .build();
    }
}
