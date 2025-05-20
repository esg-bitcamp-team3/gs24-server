package com.esgScore.server.mapper;


import com.esgScore.server.domain.InterestCorporationCategory;
import com.esgScore.server.domain.dto.CategoryDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryCreateDTO;
import com.esgScore.server.domain.dto.InterestCorporationCategoryDTO;
import com.esgScore.server.domain.dto.interest.InterestCorporationDetailDTO;

public class InterestCorporationCategoryMapper {
    public static InterestCorporationCategoryDTO toDTO(InterestCorporationCategory interestCorporation, InterestCorporationDetailDTO interestCorporationDetail, CategoryDTO category) {
        return  InterestCorporationCategoryDTO.builder()
                .id(interestCorporation.getId())
                .interestCorporationDetailDTO(interestCorporationDetail)
                .categoryDTO(category)
                .build();
    }

    public static InterestCorporationCategory fromDTO(InterestCorporationCategoryCreateDTO interestCorporationCategoryCreateDTO) {
        return InterestCorporationCategory.builder()
                .interestCorporationId(interestCorporationCategoryCreateDTO.getInterestCorporationId())
                .categoryId(interestCorporationCategoryCreateDTO.getCategoryId())
                .build();
    }

}
