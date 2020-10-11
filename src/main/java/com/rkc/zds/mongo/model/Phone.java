package com.rkc.zds.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

@QueryEntity
@Document(collection = "phone")
public class Phone {
	
	@Id
    private String phoneId;
    
	private String contactId;
	
	private String phone;
	
	private int phoneKind;	
	
	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(int phoneKind) {
		this.phoneKind = phoneKind;
	}

}
