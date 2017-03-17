/**
 * Copyright(C) 2017 Luvina software company
 * Week.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Week extends Time{
	private int weekId;
	private int weekCount;

	/**
	 * 
	 */
	public Week() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param weekId
	 * @param weekCount
	 */
	public Week(int weekId, int weekCount) {
		super();
		this.weekId = weekId;
		this.weekCount = weekCount;
	}

	/**
	 * @return the weekId
	 */
	public int getWeekId() {
		return weekId;
	}

	/**
	 * @param weekId
	 *            the weekId to set
	 */
	public void setWeekId(int weekId) {
		this.weekId = weekId;
	}

	/**
	 * @return the weekCount
	 */
	public int getWeekCount() {
		return weekCount;
	}

	/**
	 * @param weekCount
	 *            the weekCount to set
	 */
	public void setWeekCount(int weekCount) {
		this.weekCount = weekCount;
	}

}
