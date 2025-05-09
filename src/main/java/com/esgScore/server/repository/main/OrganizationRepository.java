package com.esgScore.server.repository.main;


import com.esgScore.server.domain.Organization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
public interface OrganizationRepository extends MongoRepository<Organization, String> {
  Optional<Organization> findByCompanyName(String companyName);

  List<Organization> findByIdIn(List<String> ids);

  Optional<Organization> findByCompanyCode(String code);

}
