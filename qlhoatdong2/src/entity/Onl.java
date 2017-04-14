/**
 * Copyright(C) 2017 Luvina software company
 * Onl.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Onl {
	private int caTruc;
	private long id;
	private java.sql.Time timeStart;
	private java.sql.Time timeEnd;
	private Week week;
	private int dateOfWeek;
	private User user;
	private boolean hol;
	private boolean late;
	private int lateMin;
	private String reason;

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
	 * @return the caTruc
	 */
	public int getCaTruc() {
		return caTruc;
	}

	/**
	 * @param caTruc
	 *            the caTruc to set
	 */
	public void setCaTruc(int caTruc) {
		this.caTruc = caTruc;
	}

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

}
