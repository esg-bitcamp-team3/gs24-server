package com.esgScore.server.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * ESG 등급 정보를 클라이언트에 전달하기 위한 DTO 클래스
 */
@Builder
@Data
public class EsgRatingInfoDTO {
    private String id;              // ESG 데이터의 고유 ID

//    private OrganizationDTO organization;

    private Integer no;             // 순번

    private String esgGrade;        // ESG 종합 등급

    private String environment;     // 환경 점수

    private String social;          // 사회 점수

    private String governance;      // 지배구조 점수

    private Integer year;           // 평가 연도

}
