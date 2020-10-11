package com.rkc.zds.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rkc.zds.mongo.model.EMail;
import com.rkc.zds.mongo.model.Phone;

public interface MongoPhoneRepository extends MongoRepository<Phone, String> {

	Page<Phone> findByContactId(Pageable pageable, String contactId);

}
