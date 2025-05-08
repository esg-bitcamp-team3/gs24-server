package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompanyInfoDTO {
    //private String id; //csv파일에는 저장되어 있지 않은 정보
    private String companyName;
    private String industry;        // 산업
    private String numberOfEmployees; // 사원수
    private String companyType;     // 기업 구분 (ex. 대기업, 중소기업 등)
    private String establishmentDate; // 설립일
    private String capital;           // 자본금 (단위: 원)
    private String ceoName;         // 대표자
    private String graduateSalary; // 대졸 초임 (연봉 또는 월급)
    private String mainBusiness;    // 주요 사업
    private String hasFourInsurances; // 4대 보험 가입 여부
    private String homepage;        // 홈페이지 URL
    private String address;         // 본사 주소
    private List<String> affiliates; // 계열사 목록
    private String revenue; //매출액
}
