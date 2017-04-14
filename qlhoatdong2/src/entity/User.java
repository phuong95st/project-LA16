/**
 * Copyright(C) 2017 Luvina software company
 * User.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

import java.sql.Date;


/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class User {
	private int userId;
	private String email;
	private String phone;
	private String pass;
	private String fax;
	private String listSubject;
	private String releasedBook;
	private String other;
	private String releasedEngine;
	private String office;
	private String research;
	private boolean role;
	private String name;
	private String image;
	private String title;

	private Date birthDay;
	private boolean sex;
	private String queQuan;
	private String danToc;
	private String addressNow;
	private String congTac;
	private String cmt;

	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the sex
	 */
	public boolean isSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(boolean sex) {
		this.sex = sex;
	}

	/**
	 * @return the queQuan
	 */
	public String getQueQuan() {
		return queQuan;
	}

	/**
	 * @param queQuan the queQuan to set
	 */
	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	/**
	 * @return the danToc
	 */
	public String getDanToc() {
		return danToc;
	}

	/**
	 * @param danToc the danToc to set
	 */
	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}

	/**
	 * @return the addressNow
	 */
	public String getAddressNow() {
		return addressNow;
	}

	/**
	 * @param addressNow the addressNow to set
	 */
	public void setAddressNow(String addressNow) {
		this.addressNow = addressNow;
	}

	/**
	 * @return the congTac
	 */
	public String getCongTac() {
		return congTac;
	}

	/**
	 * @param congTac the congTac to set
	 */
	public void setCongTac(String congTac) {
		this.congTac = congTac;
	}

	/**
	 * @return the cmt
	 */
	public String getCmt() {
		return cmt;
	}

	/**
	 * @param cmt the cmt to set
	 */
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the listSubject
	 */
	public String getListSubject() {
		return listSubject;
	}

	/**
	 * @param listSubject
	 *            the listSubject to set
	 */
	public void setListSubject(String listSubject) {
		this.listSubject = listSubject;
	}

	/**
	 * @return the releasedBook
	 */
	public String getReleasedBook() {
		return releasedBook;
	}

	/**
	 * @param releasedBook
	 *            the releasedBook to set
	 */
	public void setReleasedBook(String releasedBook) {
		this.releasedBook = releasedBook;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other
	 *            the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * @return the releasedEngine
	 */
	public String getReleasedEngine() {
		return releasedEngine;
	}

	/**
	 * @param releasedEngine
	 *            the releasedEngine to set
	 */
	public void setReleasedEngine(String releasedEngine) {
		this.releasedEngine = releasedEngine;
	}

	/**
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}

	/**
	 * @param office
	 *            the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}

	/**
	 * @return the research
	 */
	public String getResearch() {
		return research;
	}

	/**
	 * @param research
	 *            the research to set
	 */
	public void setResearch(String research) {
		this.research = research;
	}

	/**
	 * @return the role
	 */
	public boolean isRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(boolean role) {
		this.role = role;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
