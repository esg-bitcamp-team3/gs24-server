package com.esgScore.server.mapper;

import com.esgScore.server.domain.CompanyInfo;
import com.esgScore.server.domain.dto.CompanyInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class CompanyInfoMapper {
    public static CompanyInfoDTO toDTO(CompanyInfo entity) {
        System.out.println("entity name: " + entity.getCompanyName());
        return new CompanyInfoDTO(
                entity.getCompanyName(),
                entity.getIndustry(),
                entity.getNumberOfEmployees(),
                entity.getCompanyType(),
                entity.getEstablishmentDate(),
                entity.getCapital(),
                entity.getCeoName(),
                entity.getGraduateSalary(),
                entity.getMainBusiness(),
                entity.getHasFourInsurances(),
                entity.getHomepage(),
                entity.getAddress(),
                Arrays.stream(entity.getAffiliates().split(" ")).toList(),
                entity.getRevenue()
        );
    }
}
