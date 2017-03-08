/**
 * Copyright(C) 2017 Luvina software company
 * Time.java, Mar 8, 2017 nguyenhuuphuong
 */
package entity;

import java.sql.Timestamp;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Time {
	public Timestamp start;
	public Timestamp end;

	/**
	 * @return the start
	 */
	public Timestamp getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Timestamp start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Timestamp getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Timestamp end) {
		this.end = end;
	}

}
