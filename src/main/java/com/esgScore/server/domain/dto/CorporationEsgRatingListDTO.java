package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CorporationEsgRatingListDTO {
    private CorporationDTO corporation;
    private List<EsgRatingDTO> ratings;
}
