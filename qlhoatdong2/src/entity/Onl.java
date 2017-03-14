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
public class Onl extends Time {
	private int caTruc;
	private long id;
	private User user;

	/**
	 * 
	 */
	public Onl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param caTruc
	 * @param id
	 * @param user
	 */
	public Onl(int caTruc, long id, User user) {
		super();
		this.caTruc = caTruc;
		this.id = id;
		this.user = user;
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

}
