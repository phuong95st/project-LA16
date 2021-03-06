/**
 * Copyright(C) 2017 Luvina software company
 * OnlDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import utils.Common;
import dao.OnlDao;
import entity.Onl;
import entity.User;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class OnlDaoImpl extends BaseDaoImpl implements OnlDao {

	/*
	 * (non-Javadoc)
	 * @see dao.OnlDao#getListOnl(java.sql.Timestamp, java.sql.Timestamp, int)
	 */
	@Override
	public List<Onl> getListOnl(Timestamp timeStart, Timestamp timeEnd, int id) {
		List<Onl> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_onl_cal WHERE time_start > ? AND time_start < ? AND user_id = ? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1, timeStart);
				ps.setTimestamp(2, timeEnd);
				ps.setInt(3, id);

				rs = ps.executeQuery();
				while (rs.next()) {
					Onl onl = new Onl();
					onl.setId(rs.getInt("id"));
					onl.setCaTruc(rs.getInt("ca_truc"));
					onl.setStart(rs.getTimestamp("time_start"));
					onl.setEnd(rs.getTimestamp("time_end"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					onl.setUser(user);
					
					list.add(onl);
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
		System.out.println(new OnlDaoImpl().getListOnl(Common.getStartDay(), Common.getLastDayOfWeek(), 1));
	}

}
