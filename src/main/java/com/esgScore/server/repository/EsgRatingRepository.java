package com.esgScore.server.repository;

import com.esgScore.server.domain.EsgRating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EsgRatingRepository extends MongoRepository<EsgRating, String> {
    EsgRating findByCompanyName(String companyName);
}
