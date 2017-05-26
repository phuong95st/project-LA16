/**
 * Copyright(C) 2017 Luvina software company
 * TeachWeek.java, Mar 17, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class TeachWeek {
	private long id;
	private Teach teach;
	private Week week;
	private boolean hol;
	private boolean late;
	private int lateMin;
	private String reason;

	/**
	 * @return the teach
	 */
	public Teach getTeach() {
		return teach;
	}

	/**
	 * @param teach
	 *            the teach to set
	 */
	public void setTeach(Teach teach) {
		this.teach = teach;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
