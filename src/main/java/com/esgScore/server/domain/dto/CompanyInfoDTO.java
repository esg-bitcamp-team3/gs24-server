package com.esgScore.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyInfoDTO {
    //private String id; //csv파일에는 저장되어 있지 않은 정보
    private String companyName;
    private String industry;
}
