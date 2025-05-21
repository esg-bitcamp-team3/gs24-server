package com.esgScore.server.repository.main;

import com.esgScore.server.domain.InterestCorporationCategory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
public interface InterestCorporationCategoryRepository extends MongoRepository<InterestCorporationCategory, String> {
  List<InterestCorporationCategory> findByInterestCorporationId(String interestCorporationId);
  List<InterestCorporationCategory> findByCategoryId(String categoryId);
  Optional<InterestCorporationCategory> findByCategoryIdAndInterestCorporationId(String categoryId, String interestCorporationId);
  Integer countByInterestCorporationId(String interestCorporationId);
}
