package com.esgScore.server.repository;


import com.esgScore.server.domain.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
  Optional<Organization> findByName(String name);
}
