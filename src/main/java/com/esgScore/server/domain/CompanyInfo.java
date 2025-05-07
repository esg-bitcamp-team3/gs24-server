package com.esgScore.server.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "company_infos")
public class CompanyInfo {
    //private String id; //csv파일에는 저장되어 있지 않은 정보
    @Id
    private String companyName;
    private String industry;

}
