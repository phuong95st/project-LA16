/**
 * Copyright(C) 2017 Luvina software company
 * ScheStu.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class ScheStu extends Time {
	private int id;
	private int type;
	private User user;

	/**
	 * 
	 */
	public ScheStu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param type
	 * @param user
	 */
	public ScheStu(int id, int type, User user) {
		super();
		this.id = id;
		this.type = type;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
