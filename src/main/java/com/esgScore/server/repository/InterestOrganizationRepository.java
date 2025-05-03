package com.esgScore.server.repository;

import com.esgScore.server.domain.InterestOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestOrganizationRepository extends MongoRepository<InterestOrganization, String> {

}
