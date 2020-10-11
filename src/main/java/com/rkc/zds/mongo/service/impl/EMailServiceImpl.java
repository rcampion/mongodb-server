package com.rkc.zds.mongo.service.impl;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rkc.zds.email.SendMailUsingAuthentication;
import com.rkc.zds.mongo.model.EMail;
import com.rkc.zds.mongo.model.EMailSend;
import com.rkc.zds.mongo.repository.MongoEMailRepository;
import com.rkc.zds.mongo.service.EMailService;

@Service
public class EMailServiceImpl implements EMailService {

	@Autowired
	private MongoEMailRepository eMailRepo;

	@Override
	public Page<EMail> findEMails(Pageable pageable, String contactId) {

		Page<EMail> page = eMailRepo.findByContactId(pageable, contactId);

		return page;
	}

	@Override
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void saveEMail(EMail email) {

		eMailRepo.save(email);
	}

	@Override
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateEMail(EMail email) {

		eMailRepo.save(email);

	}

	@Override
	public void deleteEMail(String id) {

		eMailRepo.deleteById(id);

	}

	@Override
	public EMail getEMail(String id) {

		Optional<EMail> email = eMailRepo.findById(id);
		if (email.isPresent())
			return email.get();
		else
			return null;
	}

	@Override
	public void sendEMail(EMailSend emailSend) {
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		try {
			String[] array = emailSend.getEmailList().split(",");
			smtpMailSender.postMail(array, emailSend.getEmailSubjectTxt(), emailSend.getEmailMsgTxt(),
					emailSend.getEmailFromAddress());

		} catch (MessagingException e) {
			e.printStackTrace();

		}
	}

}
