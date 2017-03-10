/**
 * Copyright(C) 2017 Luvina software company
 * LateDaoImpl.java, Mar 10, 2017 nguyenhuuphuong
 */
package dao.impl;

import com.mysql.jdbc.Statement;

import dao.LateDao;
import entity.Late;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class LateDaoImpl extends BaseDaoImpl implements LateDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.LateDao#insertLate(entity.Late)
	 */
	@Override
	public long insertLate(Late late) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tbl_late (time_login, reason, user_id, range_min) VALUES (?,?,?,?)");

			try {
				ps = conn.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setTimestamp(1, late.getTimeLogin());
				ps.setString(2, late.getReason());
				ps.setInt(3, late.getUser().getUserId());
				ps.setInt(4, late.getRange());

				int result = ps.executeUpdate();
				if (result != 0) {
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						return rs.getLong(1);
					}

				}
			} catch (Exception e) {
				System.out.println("Erorr: " + e.getMessage());
			} finally {
				close();
			}
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.LateDao#updateLate(java.lang.String, long)
	 */
	@Override
	public boolean updateLate(String reason, long id) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tbl_late SET reason = ? WHERE id = ?");

			try {
				ps = conn.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, reason);
				ps.setLong(2, id);

				int result = ps.executeUpdate();
				if (result != 0) {
					return true;

				}
			} catch (Exception e) {
				System.out.println("Erorr: " + e.getMessage());
			} finally {
				close();
			}
		}
		return false;
	}
}
