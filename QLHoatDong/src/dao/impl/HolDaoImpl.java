/**
 * Copyright(C) 2017 Luvina software company
 * HolDaoImpl.java, Mar 8, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.HolDao;
import entity.Hol;
import entity.User;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class HolDaoImpl extends BaseDaoImpl implements HolDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.HolDao#getListHol(int)
	 */
	@Override
	public List<Hol> getListHol(int userId) {
		List<Hol> list = new ArrayList<Hol>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_hol_sche WHERE user_id=? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, userId);

				rs = ps.executeQuery();
				while (rs.next()) {
					Hol hol = new Hol();
					hol.setId(rs.getInt("id"));
					hol.setType(rs.getInt("type"));
					hol.setStart(rs.getTimestamp("start_date"));
					hol.setEnd(rs.getTimestamp("end_date"));
					hol.setReason(rs.getString("reason"));
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					hol.setUser(user);
					hol.setStatus(rs.getBoolean("status"));
					hol.setPhep(rs.getBoolean("phep"));

					list.add(hol);
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.HolDao#update(entity.Hol, boolean)
	 */
	@Override
	public boolean update(Hol hol, boolean status) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();

			try {
				if (hol == null) {
					sql.append("UPDATE tbl_hol_sche SET status = ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setBoolean(1, status);
				} else {
					sql.append("UPDATE tbl_hol_sche SET type=?, start_date=?, end_date=?, reason =? WHERE id=?");
					ps = conn.prepareStatement(sql.toString());
					ps.setInt(1, hol.getType());
					ps.setTimestamp(2, hol.getStart());
					ps.setTimestamp(3, hol.getEnd());
					ps.setString(4, hol.getReason());
					ps.setLong(5, hol.getId());
				}
				int result = ps.executeUpdate();
				if (result != 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return false;
	}

}
