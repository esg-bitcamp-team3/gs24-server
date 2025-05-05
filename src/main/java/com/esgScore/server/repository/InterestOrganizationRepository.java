package com.esgScore.server.repository;

import com.esgScore.server.domain.InterestOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InterestOrganizationRepository extends MongoRepository<InterestOrganization, String> {
  List<InterestOrganization> findByUserId(String userId);
  Optional<InterestOrganization> findByUserIdAndOrganizationId(String userId, String organizationId);
}
