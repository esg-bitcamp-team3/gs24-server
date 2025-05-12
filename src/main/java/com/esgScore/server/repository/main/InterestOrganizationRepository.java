package com.esgScore.server.repository.main;

import com.esgScore.server.domain.InterestOrganization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
public interface InterestOrganizationRepository extends MongoRepository<InterestOrganization, String> {
  List<InterestOrganization> findListByUserId(String userId);
  Optional<InterestOrganization> findByUserIdAndOrganizationId(String userId, String organizationId);
}
