/**
 * Copyright(C) 2017 Luvina software company
 * TeachDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.TeachDao;
import entity.Teach;
import entity.User;

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
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_tea_cal WHERE time_start > ? AND time_start < ? AND user_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, timeStart);
				ps.setTimestamp(2, timeEnd);
				ps.setInt(3, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					Teach teach = new Teach();
					teach.setId(rs.getInt("id"));
					teach.setStart(rs.getTimestamp("time_start"));
					teach.setEnd(rs.getTimestamp("time_end"));
					teach.setWeek(rs.getInt("week"));
					teach.setCodeClass(rs.getString("code_class"));
					teach.setCodeSubject(rs.getString("code_subject"));
					teach.setName(rs.getString("name"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					teach.setUser(user);
					teach.setPhong(rs.getString("phong"));

					list.add(teach);
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return list;
	}

}
