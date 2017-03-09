/**
 * Copyright(C) 2017 Luvina software company
 * Event.java, Mar 8, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Event extends Time {
	private long eventId;
	private TypeEvent type;
	private String place;
	private String content;
	private String title;

	/**
	 * 
	 */
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param eventId
	 * @param type
	 * @param place
	 * @param content
	 */
	public Event(long eventId, TypeEvent type, String place, String content) {
		super();
		this.eventId = eventId;
		this.type = type;
		this.place = place;
		this.content = content;
	}

	/**
	 * @return the eventId
	 */
	public long getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the type
	 */
	public TypeEvent getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TypeEvent type) {
		this.type = type;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
