package com.esgScore.server.repository.sub;

import com.esgScore.server.domain.CompanyInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("subMongoTemplate")
public interface CompanyInfoRepository extends MongoRepository<CompanyInfo, String> {
    List<CompanyInfo> findByCompanyNameContaining(String keyword);
    Optional<CompanyInfo> findByCompanyName(String companyName);
}
