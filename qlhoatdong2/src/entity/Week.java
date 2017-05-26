/**
 * Copyright(C) 2017 Luvina software company
 * Week.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

import java.sql.Date;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Week {
	private int weekId;
	private int weekCount;
	private Date startDate;
	private Date endDate;
	private HocKy hocKy;

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the hocKy
	 */
	public HocKy getHocKy() {
		return hocKy;
	}

	/**
	 * @param hocKy
	 *            the hocKy to set
	 */
	public void setHocKy(HocKy hocKy) {
		this.hocKy = hocKy;
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
