package com.esgScore.server.mapper;

import com.esgScore.server.domain.CompanyInfo;
import com.esgScore.server.domain.dto.CompanyInfoDTO;
import lombok.Getter;
import lombok.Setter;

public class CompanyInfoMapper {
    public static CompanyInfoDTO toDTO(CompanyInfo entity) {
        System.out.println("entity name: " + entity.getCompanyName());
        return new CompanyInfoDTO(
                entity.getCompanyName(),
                entity.getIndustry()
        );
    }
}
