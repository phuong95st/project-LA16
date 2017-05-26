/**
 * Copyright(C) 2017 Luvina software company
 * ScheStu.java, Mar 14, 2017 nguyenhuuphuong
 */
package entity;

import java.sql.Time;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class ScheStu{
	private int id;
	private Time start;
	private Time end;
	private int type;
	private User user;
	private int dateOfWeek;
	private Week wStart;
	private Week wEnd;
	private String studentName;
	private String topic;
	private Position phong;

	
	/**
	 * @return the start
	 */
	public Time getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Time start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Time getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Time end) {
		this.end = end;
	}

	/**
	 * @return the dateOfWeek
	 */
	public int getDateOfWeek() {
		return dateOfWeek;
	}

	/**
	 * @param dateOfWeek the dateOfWeek to set
	 */
	public void setDateOfWeek(int dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}

	/**
	 * @return the wStart
	 */
	public Week getwStart() {
		return wStart;
	}

	/**
	 * @param wStart the wStart to set
	 */
	public void setwStart(Week wStart) {
		this.wStart = wStart;
	}

	/**
	 * @return the wEnd
	 */
	public Week getwEnd() {
		return wEnd;
	}

	/**
	 * @param wEnd the wEnd to set
	 */
	public void setwEnd(Week wEnd) {
		this.wEnd = wEnd;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
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

	/**
	 * @return the phong
	 */
	public Position getPhong() {
		return phong;
	}

	/**
	 * @param phong the phong to set
	 */
	public void setPhong(Position phong) {
		this.phong = phong;
	}

}
