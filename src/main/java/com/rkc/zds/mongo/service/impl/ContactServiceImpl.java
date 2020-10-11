package com.rkc.zds.mongo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.rkc.zds.mongo.model.Contact;
import com.rkc.zds.mongo.repository.MongoContactRepository;
import com.rkc.zds.mongo.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	private static final int PAGE_SIZE = 50;
	
	@Autowired
	MongoContactRepository mongoContactRepository;


	@Override
	public Page<Contact> searchContacts(Pageable pageable, Specification<Contact> spec) {
		//return mongoContactRepository.findAll(spec, pageable);
return null;
	}

	@Override
	public Page<Contact> searchContacts(Pageable pageable, Criteria query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Contact> findContacts(Pageable pageable) {
		return mongoContactRepository.findAll(pageable);
	}

	@Override
	public Contact getContact(String id) {
	
		Optional<Contact> contact = mongoContactRepository.findById(id);
		//Optional<Contact> contact = mongoContactRepository.
		if(contact.isPresent())
			return contact.get();
		else
			return null;
	}

	@Override
	public Contact saveContact(Contact contact) {
		Contact newContact = mongoContactRepository.save(contact);
		return newContact;
	}
	
	@Override
	public void updateContact(Contact contact) {
		mongoContactRepository.save(contact);
		
	}
	
	@Override
	public void deleteContact(String id) {
		mongoContactRepository.deleteById(id);		
	}

}
