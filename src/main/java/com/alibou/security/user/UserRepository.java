package com.alibou.security.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findById(String userId);
  List<User> findByRole(Role role);

  Optional<User> findByRoleAndId(Role role,String id);

  void deleteAll();


}
