package com.rkc.zds.mongo.service.impl;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rkc.zds.mongo.model.Phone;
import com.rkc.zds.mongo.repository.MongoPhoneRepository;
import com.rkc.zds.mongo.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private MongoPhoneRepository eMailRepo;

	@Override
	public Page<Phone> findPhones(Pageable pageable, String contactId) {

		Page<Phone> page = eMailRepo.findByContactId(pageable, contactId);

		return page;
	}

	@Override
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void savePhone(Phone email) {

		eMailRepo.save(email);
	}

	@Override
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updatePhone(Phone email) {

		eMailRepo.save(email);

	}

	@Override
	public void deletePhone(String id) {

		eMailRepo.deleteById(id);

	}

	@Override
	public Phone getPhone(String id) {

		Optional<Phone> email = eMailRepo.findById(id);
		if (email.isPresent())
			return email.get();
		else
			return null;
	}

}
