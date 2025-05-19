package com.esgScore.server.repository.main;

import com.esgScore.server.domain.InterestCorporation;
import com.esgScore.server.domain.InterestOrganization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
public interface InterestCorporationRepository extends MongoRepository<InterestCorporation, String> {
  List<InterestCorporation> findListByUserId(String userId);
  Optional<InterestCorporation> findByUserIdAndCorporationId(String userId, String corporationId);
}
