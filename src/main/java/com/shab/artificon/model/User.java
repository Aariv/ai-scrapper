/**
 * 
 */
package com.shab.artificon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author zentere
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String azureId;

	private String jobTitle;

	private String employeeId;

	private String userType;

	private String userPrincipalName;

	private String registeredMobile;

	public User() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the azureId
	 */
	public String getAzureId() {
		return azureId;
	}

	/**
	 * @param azureId
	 *            the azureId to set
	 */
	public void setAzureId(String azureId) {
		this.azureId = azureId;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the userPrincipalName
	 */
	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	/**
	 * @param userPrincipalName
	 *            the userPrincipalName to set
	 */
	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	/**
	 * @return the registeredMobile
	 */
	public String getRegisteredMobile() {
		return registeredMobile;
	}

	/**
	 * @param registeredMobile
	 *            the registeredMobile to set
	 */
	public void setRegisteredMobile(String registeredMobile) {
		this.registeredMobile = registeredMobile;
	}

}