package com.rkc.zds.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rkc.zds.mongo.model.EMail;

public interface MongoEMailRepository extends MongoRepository<EMail, String> {

	Page<EMail> findByContactId(Pageable pageable, String contactId);

}
