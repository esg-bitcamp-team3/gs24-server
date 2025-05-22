package com.esgScore.server.repository.main;

import com.esgScore.server.domain.PredictRating;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
/**
 * MongoDB에서 EsgRating 문서를 조회하기 위한 리포지토리 인터페이스
 */
public interface PredictRatingRepository extends MongoRepository<PredictRating, String> {
    Optional<PredictRating> findByCompanyName(String companyName);
}