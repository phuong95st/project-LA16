/**
 * Copyright(C) 2017 Luvina software company
 * Teach.java, Mar 8, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Teach extends Time {
	private long id;
	private int week;
	private String codeClass;
	private String codeSubject;
	private String name;
	private User user;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the week
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * @param week
	 *            the week to set
	 */
	public void setWeek(int week) {
		this.week = week;
	}

	/**
	 * @return the codeClass
	 */
	public String getCodeClass() {
		return codeClass;
	}

	/**
	 * @param codeClass
	 *            the codeClass to set
	 */
	public void setCodeClass(String codeClass) {
		this.codeClass = codeClass;
	}

	/**
	 * @return the codeSubject
	 */
	public String getCodeSubject() {
		return codeSubject;
	}

	/**
	 * @param codeSubject
	 *            the codeSubject to set
	 */
	public void setCodeSubject(String codeSubject) {
		this.codeSubject = codeSubject;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
