package com.esgScore.server.repository.main;

import com.esgScore.server.domain.EsgTerm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

@Qualifier("MongoRepository")
public interface EsgTermsRepository extends MongoRepository<EsgTerm, String> {
}
