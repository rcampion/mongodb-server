package com.rkc.zds.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkc.zds.mongo.model.Contact;
import com.rkc.zds.mongo.model.EMail;
import com.rkc.zds.mongo.model.EMailSend;
import com.rkc.zds.mongo.model.Phone;
import com.rkc.zds.mongo.repository.MongoContactRepository;
import com.rkc.zds.mongo.service.ContactService;
import com.rkc.zds.mongo.service.EMailService;
import com.rkc.zds.mongo.service.PhoneService;

@CrossOrigin(origins = "http://localhost:8089")
@RestController
@RequestMapping(value = "/api/contact")
public class ContactController {

	@Autowired
	ContactService contactService;

	@Autowired
	EMailService emailService;

	@Autowired
	PhoneService phoneService;

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MongoContactRepository mongoContactRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Contact>> findAllContacts(Pageable pageable, HttpServletRequest req) {
		Page<Contact> page = contactService.findContacts(pageable);
		ResponseEntity<Page<Contact>> response = new ResponseEntity<>(page, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> getContact(@PathVariable String id, HttpServletRequest req) {
		Contact contact = contactService.getContact(id);
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Contact>> findAllByRsql(Pageable pageable,
			@RequestParam(value = "search") String search) {

/*
		final String regex = "(?i).*" + Pattern.quote(search.toLowerCase()) + ".*";
		Criteria firstName = Criteria.where("firstName").regex(regex);
		Criteria lastName = Criteria.where("lastName").regex(regex);
		Criteria company = Criteria.where("company").regex(regex);
		Criteria criteria = new Criteria().orOperator(company, firstName, lastName);
		// return query(criteria).with(pageable);
		//Pageable newPageable = new Pageable.of();
		Pageable paging = PageRequest.of(pageable.getPageNumber(), 1000, pageable.getSort());
		//paging.set
		Query query = new Query().with(paging);
		query.addCriteria(criteria);
		// MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(),
		// "database");
		List<Contact> contacts = mongoTemplate.find(query, Contact.class);
*/		
		Page<Contact> page = mongoContactRepository.findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrCompanyLikeIgnoreCase(search, search, search, pageable);

		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteContact(@PathVariable int id) {
		contactService.deleteContact(String.valueOf(id));
		return Integer.toString(id);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void updateContact(@RequestBody String jsonString) {
		ObjectMapper mapper = new ObjectMapper();

		Contact contact = new Contact();
		try {
			contact = mapper.readValue(jsonString, Contact.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contactService.updateContact(contact);

	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public Contact createContact(@RequestBody String jsonString) {

		ObjectMapper mapper = new ObjectMapper();

		Contact contactDTO = new Contact();
		try {
			contactDTO = mapper.readValue(jsonString, Contact.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Contact dto = contactService.saveContact(contactDTO);

		return dto;
	}

	@RequestMapping(value = "/email/{contactId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<EMail>> findEMails(@PathVariable String contactId, Pageable pageable,
			HttpServletRequest req) {
		Page<EMail> page = emailService.findEMails(pageable, contactId);
		ResponseEntity<Page<EMail>> response = new ResponseEntity<>(page, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/email/email/{emailId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EMail> getEmail(@PathVariable String emailId) {
		EMail email = emailService.getEMail(emailId);
		return new ResponseEntity<>(email, HttpStatus.OK);
	}

	@RequestMapping(value = "/email/email", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void createEmail(@RequestBody String jsonString) {

		ObjectMapper mapper = new ObjectMapper();

		EMail emailDTO = new EMail();
		try {
			emailDTO = mapper.readValue(jsonString, EMail.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailService.saveEMail(emailDTO);
	}

	@RequestMapping(value = "/email/email", method = RequestMethod.PUT, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void updateEMail(@RequestBody String jsonString) {
		ObjectMapper mapper = new ObjectMapper();

		EMail email = new EMail();
		try {
			email = mapper.readValue(jsonString, EMail.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailService.updateEMail(email);

	}

	@RequestMapping(value = "/email/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteEmail(@PathVariable String id) {
		emailService.deleteEMail(id);
		return id;
	}

	@RequestMapping(value = "/email/send", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void sendEmail(@RequestBody String jsonString) {

		ObjectMapper mapper = new ObjectMapper();

		EMailSend emailSend = new EMailSend();
		try {
			emailSend = mapper.readValue(jsonString, EMailSend.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailService.sendEMail(emailSend);
	}

	@RequestMapping(value = "/phone/{contactId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Phone>> findPhones(@PathVariable String contactId, Pageable pageable,
			HttpServletRequest req) {
		Page<Phone> page = phoneService.findPhones(pageable, contactId);
		ResponseEntity<Page<Phone>> response = new ResponseEntity<>(page, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/phone/phone/{phoneId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Phone> getPhone(@PathVariable String phoneId) {
		Phone phone = phoneService.getPhone(phoneId);
		return new ResponseEntity<>(phone, HttpStatus.OK);
	}

	@RequestMapping(value = "/phone/phone", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void createPhone(@RequestBody String jsonString) {

		ObjectMapper mapper = new ObjectMapper();

		Phone phoneDTO = new Phone();
		try {
			phoneDTO = mapper.readValue(jsonString, Phone.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		phoneService.savePhone(phoneDTO);
	}

	@RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deletePhone(@PathVariable String id) {
		phoneService.deletePhone(id);
		return id;
	}

	@RequestMapping(value = "/phone/phone", method = RequestMethod.PUT, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void updatePhone(@RequestBody String jsonString) {
		ObjectMapper mapper = new ObjectMapper();

		Phone phone = new Phone();
		try {
			phone = mapper.readValue(jsonString, Phone.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		phoneService.updatePhone(phone);

	}
}
