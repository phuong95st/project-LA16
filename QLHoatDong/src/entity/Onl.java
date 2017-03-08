/**
 * Copyright(C) 2017 Luvina software company
 * Onl.java, Mar 8, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Onl extends Time {
	private long id;
	private int caTruc;
	private User user;

	/**
	 * 
	 */
	public Onl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param caTruc
	 * @param user
	 */
	public Onl(long id, int caTruc, User user) {
		super();
		this.id = id;
		this.caTruc = caTruc;
		this.user = user;
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
