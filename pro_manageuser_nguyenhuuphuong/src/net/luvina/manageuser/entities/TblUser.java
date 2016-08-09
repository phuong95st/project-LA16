/**
 * Copyright(C) 2016 Luvina Software Company
 * TblUser.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.entities;

import java.io.Serializable;

/**
 * @author PhuongNH
 *
 */
public class TblUser implements Serializable{
	private int id;
	private String loginId;
	private String password;
	private String fullName;
	private String email;
	private String tel;
	private int groupId;
	private String groupName;

	/**
	 * Contructor
	 */
	public TblUser() {
		this.id = 0;
		this.loginId = "";
		this.password = "";
		this.fullName = "";
		this.email = "";
		this.tel = "";
		this.groupId = 0;
	}

	/**
	 *
	 * @param id
	 *            int ID
	 * @param name
	 *            String fullName
	 * @param email
	 *            String Email
	 * @param tel
	 *            String Tel
	 * @param group_id
	 *            int GroupID
	 */
	public TblUser(int id, String fullName, String email, String tel,
			String groupName) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.tel = tel;
		this.groupName = groupName;
	}

	/**
	 *
	 * @param id
	 *            ID
	 * @param fullName
	 *            fullName
	 * @param email
	 *            email
	 * @param tel
	 *            tel
	 * @param loginId
	 *            loginId
	 * @param password
	 *            password
	 */
	public TblUser(int id, String fullName, String email, String tel,
			String loginId, String password) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.tel = tel;
		this.loginId = loginId;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
