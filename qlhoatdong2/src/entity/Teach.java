/**
 * Copyright(C) 2017 Luvina software company
 * Teach.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Teach extends Time {
	private long teachId;
	private Week week;
	private String codeClass;
	private String codeSubject;
	private String name;
	private User user;
	private boolean hol;
	private Position phong;
	private boolean late;
	private int lateMin;
	private String reason;

	/**
	 * @return the late
	 */
	public boolean isLate() {
		return late;
	}

	/**
	 * @param late
	 *            the late to set
	 */
	public void setLate(boolean late) {
		this.late = late;
	}

	/**
	 * @return the lateMin
	 */
	public int getLateMin() {
		return lateMin;
	}

	/**
	 * @param lateMin
	 *            the lateMin to set
	 */
	public void setLateMin(int lateMin) {
		this.lateMin = lateMin;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the teachId
	 */
	public long getTeachId() {
		return teachId;
	}

	/**
	 * @param teachId
	 *            the teachId to set
	 */
	public void setTeachId(long teachId) {
		this.teachId = teachId;
	}

	/**
	 * @return the week
	 */
	public Week getWeek() {
		return week;
	}

	/**
	 * @param week
	 *            the week to set
	 */
	public void setWeek(Week week) {
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

	/**
	 * @return the hol
	 */
	public boolean isHol() {
		return hol;
	}

	/**
	 * @param hol
	 *            the hol to set
	 */
	public void setHol(boolean hol) {
		this.hol = hol;
	}

	/**
	 * @return the phong
	 */
	public Position getPhong() {
		return phong;
	}

	/**
	 * @param phong
	 *            the phong to set
	 */
	public void setPhong(Position phong) {
		this.phong = phong;
	}

}
