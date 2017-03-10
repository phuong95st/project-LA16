/**
 * Copyright(C) 2017 Luvina software company
 * Late.java, Mar 10, 2017 nguyenhuuphuong
 */
package entity;

import java.sql.Timestamp;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Late {
	private long id;
	private Timestamp timeLogin;
	private String reason;
	private User user;
	private int rangeMin;

	/**
	 * 
	 */
	public Late() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param id
	 * @param timeLogin
	 * @param reason
	 * @param user
	 * @param range
	 */
	public Late(long id, Timestamp timeLogin, String reason, User user,
			int range) {
		super();
		this.id = id;
		this.timeLogin = timeLogin;
		this.reason = reason;
		this.user = user;
		this.rangeMin = range;
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
	 * @return the timeLogin
	 */
	public Timestamp getTimeLogin() {
		return timeLogin;
	}

	/**
	 * @param timeLogin
	 *            the timeLogin to set
	 */
	public void setTimeLogin(Timestamp timeLogin) {
		this.timeLogin = timeLogin;
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
	 * @return the range
	 */
	public int getRange() {
		return rangeMin;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(int range) {
		this.rangeMin = range;
	}

}
