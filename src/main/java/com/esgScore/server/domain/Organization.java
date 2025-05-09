package com.esgScore.server.domain;

import com.esgScore.server.domain.dto.OrganizationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "organization")
public class Organization {
  @Id
  private String id;

  @Field("기업명")
  private String companyName;

  @Field("기업코드")
  private String companyCode;

  private String rating;

  private String industry;        // 산업
  private Integer numberOfEmployees; // 사원수
  private String companyType;     // 기업 구분 (ex. 대기업, 중소기업 등)
  private LocalDate establishmentDate; // 설립일
  private Long capital;           // 자본금 (단위: 원)
  private String ceoName;         // 대표자
  private Integer graduateSalary; // 대졸 초임 (연봉 또는 월급)
  private String mainBusiness;    // 주요 사업
  private Boolean hasFourInsurances; // 4대 보험 가입 여부
  private String homepage;        // 홈페이지 URL
  private String address;         // 본사 주소
  private List<String> affiliates; // 계열사 목록
}
