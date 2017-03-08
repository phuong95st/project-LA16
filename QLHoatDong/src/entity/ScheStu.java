/**
 * Copyright(C) 2017 Luvina software company
 * ScheStu.java, Mar 8, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class ScheStu extends Time {
	private long id;
	private String type;
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
	public ScheStu(long id, String type, User user) {
		super();
		this.id = id;
		this.type = type;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
