package com.esgScore.server.mapper;


import com.esgScore.server.domain.InterestCorporation;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDetailDTO;

import java.util.List;

public class InterestCorporationMapper {
    public static InterestCorporationDTO toDTO(InterestCorporation interestCorporation) {
        return InterestCorporationDTO.builder()
                .id(interestCorporation.getId())
                .userId(interestCorporation.getUserId())
                .corporationId(interestCorporation.getCorporationId())
                .checkTime(interestCorporation.getCheckTime())
                .build();
    }
    public static InterestCorporationDetailDTO toDetailDTO(InterestCorporation interestCorporation, CorporationDTO corporationDTO) {
        return InterestCorporationDetailDTO.builder()
                .id(interestCorporation.getId())
                .userId(interestCorporation.getUserId())
                .corporation(corporationDTO)
                .build();
    }


    public static InterestCorporation fromDTO(InterestCorporationDTO interestCorporationDTO) {
        return InterestCorporation.builder()
                .corporationId(interestCorporationDTO.getCorporationId())
                .userId(interestCorporationDTO.getUserId())
                .build();
    }

}
