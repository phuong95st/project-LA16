/**
 * Copyright(C) 2017 Luvina software company
 * Student.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Student {
	private int studentId;
	private String name;
	private String topic;
	private int type;

	/**
	 * 
	 */
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param studentId
	 * @param name
	 * @param topic
	 * @param type
	 */
	public Student(int studentId, String name, String topic, int type) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.topic = topic;
		this.type = type;
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
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
