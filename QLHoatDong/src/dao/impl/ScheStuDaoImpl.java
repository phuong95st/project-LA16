/**
 * Copyright(C) 2017 Luvina software company
 * ScheStuDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import utils.Common;
import dao.ScheStuDao;
import entity.ScheStu;
import entity.User;

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
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_she_stu WHERE time_start > ? AND time_start < ? AND user_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, timeStart);
				ps.setTimestamp(2, timeEnd);
				ps.setInt(3, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					ScheStu scheStu = new ScheStu();
					scheStu.setId(rs.getInt("id"));
					scheStu.setStart(rs.getTimestamp("time_start"));
					scheStu.setEnd(rs.getTimestamp("time_end"));
					scheStu.setType(rs.getInt("type_stu"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					scheStu.setUser(user);
					
					list.add(scheStu);
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return list;
	}
	
	public static void main(String[] args){
		System.out.println(new ScheStuDaoImpl().getListScheStu(Common.getStartDay(), Common.getLastDayOfWeek(), 1));
	}

}
