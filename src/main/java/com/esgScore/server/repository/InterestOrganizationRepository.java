package com.esgScore.server.repository;

import com.esgScore.server.domain.InterestOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InterestOrganizationRepository extends MongoRepository<InterestOrganization, String> {
  List<InterestOrganization> findListByUserId(String userId);
}
