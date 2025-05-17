package com.esgScore.server.repository.main;



import com.esgScore.server.domain.Corporation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
public interface CorporationRepository extends MongoRepository<Corporation, String> {
  Optional<Corporation> findByCorpCode(String corpCode);
  Optional<Corporation> findByStockCode(String stockCode);
}
