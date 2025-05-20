package com.esgScore.server.repository.main;


import com.esgScore.server.domain.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Qualifier("MongoRepository")
public interface CategoryRepository extends MongoRepository<Category, String> {
  Optional<Category> findByName(String name);
  List<Category> findAllByUserId(String userId);

}
