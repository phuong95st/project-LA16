/**
 * Copyright(C) 2017 Luvina software company
 * TypeEvent.java, Mar 8, 2017 nguyenhuuphuong
 */
package entity;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class TypeEvent {
	private int typeId;
	private String typeName;

	/**
	 * 
	 */
	public TypeEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param typeId
	 * @param typeName
	 */
	public TypeEvent(int typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
