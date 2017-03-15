/**
 * Copyright(C) 2017 Luvina software company
 * Position.java, Mar 15, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class Position {
	private String key;
	private double latitude;
	private double longitude;

	/**
	 * 
	 */
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param key
	 * @param latitude
	 * @param longitude
	 */
	public Position(String key, double latitude, double longitude) {
		super();
		this.key = key;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
