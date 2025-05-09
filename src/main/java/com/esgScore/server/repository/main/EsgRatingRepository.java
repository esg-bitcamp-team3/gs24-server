package com.esgScore.server.repository.main;

import com.esgScore.server.domain.EsgRating;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Qualifier("MongoRepository")
/**
 * MongoDB에서 EsgRating 문서를 조회하기 위한 리포지토리 인터페이스
 */
public interface EsgRatingRepository extends MongoRepository<EsgRating, String> {
    /**
     * 조직 ID를 기준으로 해당 조직의 ESG 등급 데이터를 조회
     * @param organizationId 조회할 조직의 고유 식별자 (ID)
     * @return 해당 조직의 ESG 등급 정보를 담은 EsgRating 리스트
     */
    List<EsgRating> findByOrganizationId(String organizationId);
    List<EsgRating> findByYear(Integer year);
}