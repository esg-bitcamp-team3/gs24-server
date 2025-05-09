package com.esgScore.server.repository;

import com.esgScore.server.domain.CompanyInfo;
import com.esgScore.server.domain.EsgRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CompanyInfoRepository extends MongoRepository<CompanyInfo, String> {
    List<CompanyInfo> findByCompanyNameContaining(String keyword);
}
