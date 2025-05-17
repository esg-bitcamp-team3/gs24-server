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
    List<EsgRating> findByCorporationId(String corporationId);
    List<EsgRating> findByYear(Integer year);
}