package com.rkc.zds.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

@QueryEntity
@Document(collection = "email")
public class EMail {

	@Id
    private String emailId;
    
	private String contactId;
	
	private String email;
	
	private int emailKind;

    public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmailKind() {
		return emailKind;
	}

	public void setEmailKind(int emailKind) {
		this.emailKind = emailKind;
	}	
	
}
