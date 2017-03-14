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
public class ScheStu extends Time{
	private int id;
	private Student student;

	/**
	 * 
	 */
	public ScheStu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param student
	 */
	public ScheStu(int id, Student student) {
		super();
		this.id = id;
		this.student = student;
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
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

}
