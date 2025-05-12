package com.esgScore.server.controller;

import com.esgScore.server.domain.dto.CompanyInfoDTO;
import com.esgScore.server.service.CompanyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyInfoController {
    private final CompanyInfoService companyInfoService;

    @GetMapping("/all")
    public List<CompanyInfoDTO> getAll() {
        return companyInfoService.getAllCompanies();
    }

    @GetMapping("/search")
    public List<CompanyInfoDTO> searchCompanies(@RequestParam String keyword) {
        return companyInfoService.searchCompanies(keyword);
    }

    @GetMapping("/{id}")
    public CompanyInfoDTO getCompanyInfoById(@PathVariable String id) {
        return companyInfoService.getCompanyInfoById(id);
    }
}
