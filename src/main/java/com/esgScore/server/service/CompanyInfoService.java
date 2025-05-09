package com.esgScore.server.service;

import com.esgScore.server.domain.dto.CompanyInfoDTO;
import com.esgScore.server.exceptions.NotFoundException;
import com.esgScore.server.mapper.CompanyInfoMapper;
import com.esgScore.server.repository.sub.CompanyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;
    private final OrganizationService organizationService;

    public List<CompanyInfoDTO> getAllCompanies() {
        return companyInfoRepository.findAll().stream()
                .map(CompanyInfoMapper::toDTO)
                .toList();
    }

    public List<CompanyInfoDTO> searchCompanies(String keyword) {
        return companyInfoRepository.findByCompanyNameContaining(keyword).stream()
                .map(CompanyInfoMapper::toDTO)
                .toList();
    }

    public CompanyInfoDTO getByCompanyName(String organizationId) {
        String companyName = organizationService.getById(organizationId).getCompanyName();
        return companyInfoRepository.findByCompanyName(companyName).map(CompanyInfoMapper::toDTO).orElseThrow(() -> new NotFoundException("기업이 없습니다."));
    }
}
