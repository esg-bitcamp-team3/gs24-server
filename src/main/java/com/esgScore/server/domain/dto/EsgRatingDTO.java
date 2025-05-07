package com.esgScore.server.domain.dto;

import com.esgScore.server.domain.EsgRating;
import lombok.Builder;
import lombok.Data;

/**
 * ESG 등급 정보를 클라이언트에 전달하기 위한 DTO 클래스
 */
@Builder
@Data
public class EsgRatingDTO {
    private String id;              // ESG 데이터의 고유 ID

    private String organizationID;  // 조직 ID

    private Integer no;             // 순번

    private String esgGrade;        // ESG 종합 등급

    private String environment;     // 환경 점수

    private String social;          // 사회 점수

    private String governance;      // 지배구조 점수

    private Integer year;           // 평가 연도

    /**
     * ESGRating 도메인 객체를 DTO로 변환하는 메서드
     *
     * @param esgRating 변환할 EsgRating 도메인 객체
     * @return 변환된 EsgRatingDTO 객체
     */
    public static EsgRatingDTO toDTO(EsgRating esgRating) {
        return EsgRatingDTO.builder()
                .id(esgRating.getId())
                .organizationID(esgRating.getOrganizationId())
                .no(esgRating.getNo())
                .esgGrade(esgRating.getEsgGrade())
                .environment(esgRating.getEnvironment())
                .social(esgRating.getSocial())
                .governance(esgRating.getGovernance())
                .year(esgRating.getYear())
                .build();
    }
}
