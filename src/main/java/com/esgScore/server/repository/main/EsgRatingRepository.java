package com.esgScore.server.repository.main;

import com.esgScore.server.domain.EsgRating;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("MongoRepository")
public interface EsgRatingRepository extends MongoRepository<EsgRating, String> {
    List<EsgRating> findByOrganizationId(String organizationId);
    List<EsgRating> findByYear(Integer year);
}