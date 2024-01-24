package com.alibou.security.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface  Storagerepo extends MongoRepository<ImageData,String> {
    Optional<ImageData> findByName(String fileName);

}
