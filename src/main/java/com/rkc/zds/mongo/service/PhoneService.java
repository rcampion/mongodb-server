package com.rkc.zds.mongo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rkc.zds.mongo.model.Phone;

public interface PhoneService {
    Page<Phone> findPhones(Pageable pageable, String contactId);
    
    Phone getPhone(String id);  
    
    public void savePhone(Phone phone);
      
    public void updatePhone(Phone phone);
 
	void deletePhone(String id);
}
