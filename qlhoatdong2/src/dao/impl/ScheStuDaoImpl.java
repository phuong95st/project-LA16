/**
 * Copyright(C) 2017 Luvina software company
 * ScheStuDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.ScheStuDao;
import entity.ScheStu;

/**
 * 
 * @author nguyenhuuphuong
 *
 */
public class ScheStuDaoImpl extends BaseDaoImpl implements ScheStuDao {

	/* (non-Javadoc)
	 * @see dao.ScheStuDao#getListScheStu(java.sql.Timestamp, java.sql.Timestamp, int)
	 */
	@Override
	public List<ScheStu> getListScheStu(Timestamp timeStart, Timestamp timeEnd,
			int userId) {
		List<ScheStu> list = new ArrayList<>();
		
		return list;
	}
	

}
