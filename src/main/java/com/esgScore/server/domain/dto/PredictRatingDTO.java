package com.esgScore.server.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * ESG 등급 정보를 클라이언트에 전달하기 위한 DTO 클래스
 */
@Builder
@Data
public class PredictRatingDTO {
    private String id;              // ESG 데이터의 고유 ID

    private String companyName;

    private String date;

    private Integer eScore;
    private Integer sScore;
    private Integer gScore;
}
