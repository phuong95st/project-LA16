/**
 * Copyright(C) 2017 Luvina software company
 * UserDaoImpl.java, Mar 14, 2017 nguyenhuuphuong
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
	public boolean checkLogin(String email, String pass) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT count(*) FROM tbl_user WHERE email=? AND pass = ?");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, email);
				ps.setString(2, pass);

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
					user.setEmail(rs.getString("email"));
					user.setPass(rs.getString("pass"));
					user.setPhone(rs.getString("phone"));
					user.setFax(rs.getString("fax"));
					user.setOffice(rs.getString("office"));
					user.setListSubject(rs.getString("list_subject"));
					user.setResearch(rs.getString("research"));
					user.setReleasedEngine(rs.getString("released_engine"));
					user.setReleasedBook(rs.getString("released_book"));
					user.setOther(rs.getString("other"));
					user.setRole(rs.getBoolean("role"));
					user.setName(rs.getString("name"));
					user.setImage(rs.getString("image"));
					user.setTitle(rs.getString("title"));
					
					user.setBirthDay(rs.getDate("birthday"));
					user.setSex(rs.getBoolean("sex"));
					user.setQueQuan(rs.getString("que_quan"));
					user.setDanToc(rs.getString("dan_toc"));
					user.setAddressNow(rs.getString("dia_chi_hien_tai"));
					user.setCongTac(rs.getString("tieu_su_cong_tac"));
					user.setCmt(rs.getString("cmt"));		
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
