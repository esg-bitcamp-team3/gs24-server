package com.esgScore.server.service;

import com.esgScore.server.domain.dto.CompanyInfoDTO;
import com.esgScore.server.mapper.CompanyInfoMapper;
import com.esgScore.server.repository.CompanyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;

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
}
