package com.esgScore.server.repository;

import com.esgScore.server.domain.EsgRating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EsgRatingRepository extends MongoRepository<EsgRating, String> {
    List<EsgRating> findByOrganizationId(String organizationId);
}