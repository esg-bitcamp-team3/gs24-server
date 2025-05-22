package com.esgScore.server.mapper;

import com.esgScore.server.domain.Corporation;
import com.esgScore.server.domain.dto.corporation.CorporationDTO;
import com.esgScore.server.domain.dto.EsgRatingDTO;
import com.esgScore.server.domain.dto.corporation.CorporationDetailDTO;
import com.esgScore.server.domain.dto.corporation.CorporationEsgRatingListDTO;

import java.util.List;

public class CorporationMapper {
    public static CorporationDTO toDTO(Corporation corporation) {
        return CorporationDTO.builder()
                .id(corporation.getId())
                .corpCode(corporation.getCorpCode())
                .corpName(corporation.getCorpName())
                .stockCode(corporation.getStockCode())
                .build();
    }

    public static CorporationDetailDTO toDetailDTO(Corporation corporation) {
        return CorporationDetailDTO.builder()
                .id(corporation.getId())
                .corpCode(corporation.getCorpCode())
                .corpName(corporation.getCorpName())
                .corpEngName(corporation.getCorpEngName())
                .stockCode(corporation.getStockCode())
                .industry(corporation.getIndustry())
                .numberOfEmployees(corporation.getNumberOfEmployees())
                .companyType(corporation.getCompanyType())
                .establishmentDate(corporation.getEstablishmentDate())
                .capital(corporation.getCapital())
                .ceoName(corporation.getCeoName())
                .graduateSalary(corporation.getGraduateSalary())
                .mainBusiness(corporation.getMainBusiness())
                .hasFourInsurances(corporation.getHasFourInsurances())
                .homepage(corporation.getHomepage())
                .address(corporation.getAddress())
                .affiliates(corporation.getAffiliates())
                .revenue(corporation.getRevenue())
                .build();
    }


    public static Corporation fromDTO(CorporationDetailDTO corporationDTO) {
        return Corporation.builder()
                .id(corporationDTO.getId())
                .corpCode(corporationDTO.getCorpCode())
                .corpName(corporationDTO.getCorpName())
                .corpEngName(corporationDTO.getCorpEngName())
                .stockCode(corporationDTO.getStockCode())
                .industry(corporationDTO.getIndustry())
                .numberOfEmployees(corporationDTO.getNumberOfEmployees())
                .companyType(corporationDTO.getCompanyType())
                .establishmentDate(corporationDTO.getEstablishmentDate())
                .capital(corporationDTO.getCapital())
                .ceoName(corporationDTO.getCeoName())
                .graduateSalary(corporationDTO.getGraduateSalary())
                .mainBusiness(corporationDTO.getMainBusiness())
                .hasFourInsurances(corporationDTO.getHasFourInsurances())
                .homepage(corporationDTO.getHomepage())
                .address(corporationDTO.getAddress())
                .affiliates(corporationDTO.getAffiliates())
                .revenue(corporationDTO.getRevenue())
                .build();
    }


    public static CorporationEsgRatingListDTO toEsgRatingListDTO(CorporationDTO corporationDTO, List<EsgRatingDTO> ratings) {
        return CorporationEsgRatingListDTO.builder()
                .corporation(corporationDTO)
                .ratings(ratings)
                .build();
    }
}
