/**
 * Copyright(C) 2017 Luvina software company
 * UserDaoImpl.java, Mar 2, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;

import dao.UserDao;
import entity.User;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UserDao#checkLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkLogin(String email, String password) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT count(*) FROM tbl_user WHERE email=? AND pass = ?");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, email);
				ps.setString(2, password);

				rs = ps.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) != 0) {
						return true;
					}
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UserDao#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		User user = null;
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tbl_user WHERE email=? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, email);

				rs = ps.executeQuery();
				if (rs.next()) {
					user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setRole(rs.getBoolean("role"));
					user.setPass(rs.getString("pass"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setLastLogin(rs.getTimestamp("last_login"));
				}
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}

		}
		return user;
	}

}
