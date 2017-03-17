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
public class Teach {
	private long teachId;
	private int dateOfWeek;
	private java.sql.Time timeStart;
	private java.sql.Time timeEnd;
	private String codeClass;
	private String codeSubject;
	private String name;
	private User user;
	private Position phong;
	private Week weekStart;
	private Week weekEnd;
	private String hocKy;

	/**
	 * @return the timeStart
	 */
	public java.sql.Time getTimeStart() {
		return timeStart;
	}

	/**
	 * @param timeStart
	 *            the timeStart to set
	 */
	public void setTimeStart(java.sql.Time timeStart) {
		this.timeStart = timeStart;
	}

	/**
	 * @return the timeEnd
	 */
	public java.sql.Time getTimeEnd() {
		return timeEnd;
	}

	/**
	 * @param timeEnd
	 *            the timeEnd to set
	 */
	public void setTimeEnd(java.sql.Time timeEnd) {
		this.timeEnd = timeEnd;
	}

	/**
	 * @return the dateOfWeek
	 */
	public int getDateOfWeek() {
		return dateOfWeek;
	}

	/**
	 * @param dateOfWeek
	 *            the dateOfWeek to set
	 */
	public void setDateOfWeek(int dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}

	/**
	 * @return the weekStart
	 */
	public Week getWeekStart() {
		return weekStart;
	}

	/**
	 * @param weekStart
	 *            the weekStart to set
	 */
	public void setWeekStart(Week weekStart) {
		this.weekStart = weekStart;
	}

	/**
	 * @return the weekEnd
	 */
	public Week getWeekEnd() {
		return weekEnd;
	}

	/**
	 * @param weekEnd
	 *            the weekEnd to set
	 */
	public void setWeekEnd(Week weekEnd) {
		this.weekEnd = weekEnd;
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

	/**
	 * @return the hocKy
	 */
	public String getHocKy() {
		return hocKy;
	}

	/**
	 * @param hocKy
	 *            the hocKy to set
	 */
	public void setHocKy(String hocKy) {
		this.hocKy = hocKy;
	}

}
