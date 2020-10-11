package com.rkc.zds.mongo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rkc.zds.mongo.model.EMail;
import com.rkc.zds.mongo.model.EMailSend;


public interface EMailService {
    Page<EMail> findEMails(Pageable pageable, String contactId);
       
    EMail getEMail(String id);  
   
    public void saveEMail(EMail email);
        
    public void updateEMail(EMail email);
      
	void deleteEMail(String id);

	void sendEMail(EMailSend emailSend);
}
