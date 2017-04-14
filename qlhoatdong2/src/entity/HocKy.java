/**
 * Copyright(C) 2017 Luvina software company
 * HocKy.java, 01-04-2017 Tran
 */
package entity;

/**
 * 
 * @author Tran
 * 
 */
public class HocKy {
	private int id;
	private String name;
	private String namHoc;

	/**
	 * @param id
	 * @param name
	 * @param namHoc
	 */
	public HocKy(int id, String name, String namHoc) {
		super();
		this.id = id;
		this.name = name;
		this.namHoc = namHoc;
	}

	/**
	 * 
	 */
	public HocKy() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the namHoc
	 */
	public String getNamHoc() {
		return namHoc;
	}

	/**
	 * @param namHoc
	 *            the namHoc to set
	 */
	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}

}
