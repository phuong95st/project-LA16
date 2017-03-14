/**
 * Copyright(C) 2017 Luvina software company
 * TeachDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.TeachDao;
import entity.Teach;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class TeachDaoImpl extends BaseDaoImpl implements TeachDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.TeachDao#getListTeach(java.sql.Timestamp, java.sql.Timestamp,
	 * int)
	 */
	@Override
	public List<Teach> getListTeach(Timestamp timeStart, Timestamp timeEnd,
			int userId) {
		List<Teach> list = new ArrayList<>();
		
		return list;
	}

}
