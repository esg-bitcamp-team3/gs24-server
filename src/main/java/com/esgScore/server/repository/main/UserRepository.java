package com.esgScore.server.repository.main;

import com.esgScore.server.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("MongoRepository")
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
}
