package com.ejemplo.JDBCDavidChema.Entities;


import java.sql.Blob;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id", 
    		     "firstName", 
    		     "lastName", 
    		     "company", 
                     "emailAddress", 
                     "businessPhone", 
                     "mobilePhone", 
                     "jobTitle", 
                     "homePhone", 
                     "faxNumber", 
                     "address", 
                     "city", 
                     "zipPostalCode", 
                     "stateProvince", 
                     "countryRegion", 
                     "countryRegion", 
                     "notes"})
public class Order {
	private Integer id;
	private String company;
	private String lastName;
	private String firstName;
	private String emailAddress;
	private String jobTitle;
	private String businessPhone;
	private String homePhone;
	private String faxNumber;
	private String mobilePhone;
	private String address;
	private String city;
	private String zipPostalCode;
	private String stateProvince;
	private String countryRegion;
	private String webPage;
	private String notes;
	private Blob attachment;
	
	
	
	public Order() {

	}

	public Order(Integer id, String company, String lastName, String firstName, String emailAddress, String jobTitle,
			String businessPhone, String homePhone, String faxNumber, String mobilePhone, String address, String city,
			String zipPostalCode, String stateProvince, String countryRegion, String webPage, String notes,
			Blob attachment) {

		this.id = id;
		this.company = company;
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.jobTitle = jobTitle;
		this.businessPhone = businessPhone;
		this.homePhone = homePhone;
		this.faxNumber = faxNumber;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.city = city;
		this.zipPostalCode = zipPostalCode;
		this.stateProvince = stateProvince;
		this.countryRegion = countryRegion;
		this.webPage = webPage;
		this.notes = notes;
		this.attachment = attachment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipPostalCode() {
		return zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCountryRegion() {
		return countryRegion;
	}

	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "Customer [" + (id != null ? "id=" + id + ", " : "")
				+ (company != null ? "company=" + company + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (emailAddress != null ? "emailAddress=" + emailAddress + ", " : "")
				+ (businessPhone != null ? "businessPhone=" + businessPhone + ", " : "")
				+ (homePhone != null ? "homePhone=" + homePhone + ", " : "")
				+ (mobilePhone != null ? "mobilePhone=" + mobilePhone : "") + "]";
	}

}



