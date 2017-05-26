/**
 * Copyright(C) 2017 Luvina software company
 * CheckInStudent.java, 02-04-2017 Tran
 */
package entity;

/**
 * 
 * @author Tran
 * 
 */
public class CheckInStudent {
	private long id;
	private ScheStu scheStu;
	private Week week;
	private boolean status;
	private String content;
	private String other;

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
	 * @return the scheStu
	 */
	public ScheStu getScheStu() {
		return scheStu;
	}

	/**
	 * @param scheStu
	 *            the scheStu to set
	 */
	public void setScheStu(ScheStu scheStu) {
		this.scheStu = scheStu;
	}

	/**
	 * @return the week
	 */
	public Week getWeek() {
		return week;
	}

	/**
	 * @param week
	 *            the week to set
	 */
	public void setWeek(Week week) {
		this.week = week;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

}
