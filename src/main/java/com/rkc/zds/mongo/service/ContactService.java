package com.rkc.zds.mongo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.rkc.zds.mongo.model.Contact;

@Service
//public interface ContactService extends JpaSpecificationExecutor<Contact> {
public interface ContactService {

    //Page<Contact> searchContacts(Pageable pageable, List<SearchCriteria> params);

	Page<Contact> searchContacts(Pageable pageable, Criteria query);

	Page<Contact> searchContacts(Pageable pageable, Specification<Contact> spec);

	Page<Contact> findContacts(Pageable pageable);

	Contact getContact(String id);
	
    public Contact saveContact(Contact contact);
	
    public void updateContact(Contact contact);
	
	void deleteContact(String id);
}
