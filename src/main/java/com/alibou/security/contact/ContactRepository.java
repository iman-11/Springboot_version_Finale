package com.alibou.security.contact;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ContactRepository extends MongoRepository<Contact, String>{


}