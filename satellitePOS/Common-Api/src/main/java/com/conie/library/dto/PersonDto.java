package com.conie.library.dto;

import java.beans.Transient;

import org.apache.commons.lang3.text.WordUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto extends AuditTrailDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private boolean active;
	private String email;
	private String contactNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "PersonDto [ firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", active="
				+ active + ", email=" + email + ", contactNumber=" + contactNumber + "]";
	}


	@Transient
	public String getFullName() {
		return WordUtils.capitalize(String.format("%s, %s %s", lastName, firstName, middleName));
	}
}
