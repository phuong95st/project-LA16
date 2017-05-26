/**
 * Copyright(C) 2017 Luvina software company
 * UserDaoImpl.java, Mar 14, 2017 nguyenhuuphuong
 */
package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UserDao#getListUserByRole(boolean)
	 */
	@Override
	public List<User> getListUserByRole(boolean role) {
		List<User> list = new ArrayList<>();
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT `user_id`, `name`, `role` FROM `tbl_user` WHERE `role` = ?");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setBoolean(1, role);

				rs = ps.executeQuery();
				while (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					// user.setEmail(rs.getString("email"));
					// user.setPass(rs.getString("pass"));
					// user.setPhone(rs.getString("phone"));
					// user.setFax(rs.getString("fax"));
					// user.setOffice(rs.getString("office"));
					// user.setListSubject(rs.getString("list_subject"));
					// user.setResearch(rs.getString("research"));
					// user.setReleasedEngine(rs.getString("released_engine"));
					// user.setReleasedBook(rs.getString("released_book"));
					// user.setOther(rs.getString("other"));
					user.setRole(rs.getBoolean("role"));
					user.setName(rs.getString("name"));
					// user.setImage(rs.getString("image"));
					// user.setTitle(rs.getString("title"));
					// user.setBirthDay(rs.getDate("birthday"));
					// user.setSex(rs.getBoolean("sex"));
					// user.setQueQuan(rs.getString("que_quan"));
					// user.setDanToc(rs.getString("dan_toc"));
					// user.setAddressNow(rs.getString("dia_chi_hien_tai"));
					// user.setCongTac("tieu_su_cong_tac");
					// user.setCmt(rs.getString("cmt"));

					list.add(user);
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
	 * @see dao.UserDao#getUserById(int)
	 */
	@Override
	public User getUserById(int userId) {
		User user = null;
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM `tbl_user` WHERE `user_id`=? ");

			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, userId);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UserDao#isExistEmail(java.lang.String, int)
	 */
	@Override
	public boolean isExistEmail(String email, int userId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			try {
				// nếu là add
				if (userId == 0) {
					sql.append("SELECT COUNT(*) FROM `tbl_user` WHERE `email`= ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setString(1, email);
				} else {
					sql.append("SELECT COUNT(*) FROM `tbl_user` WHERE `email`= ? AND `user_id` != ?");
					ps = conn.prepareStatement(sql.toString());
					ps.setString(1, email);
					ps.setInt(2, userId);
				}
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
	 * @see dao.UserDao#addUser(entity.User)
	 */
	@Override
	public int addUser(User user) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO `tbl_user`(`email`, `pass`, `phone`, `fax`, `office`, `list_subject`, `research`, `released_engine`, `released_book`, `other`, `role`, `name`, `title`, `birthday`, `sex`, `que_quan`, `dan_toc`, `dia_chi_hien_tai`, `tieu_su_cong_tac`, `cmt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			try {
				ps = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
				int i=1;
				ps.setString(i++, user.getEmail());
				ps.setString(i++, user.getPass());
				ps.setString(i++, user.getPhone());
				ps.setString(i++, user.getFax());
				ps.setString(i++, user.getOffice());
				ps.setString(i++, user.getListSubject());
				ps.setString(i++, user.getResearch());
				ps.setString(i++, user.getReleasedEngine());
				
				ps.setString(i++, user.getReleasedBook());
				ps.setString(i++, user.getOther());
				ps.setBoolean(i++, user.isRole());
				ps.setString(i++, user.getName());
				ps.setString(i++, user.getTitle());
				ps.setDate(i++, user.getBirthDay());
				ps.setBoolean(i++, user.isSex());
				ps.setString(i++, user.getQueQuan());
				ps.setString(i++, user.getDanToc());
				ps.setString(i++, user.getAddressNow());
				ps.setString(i++, user.getCongTac());
				ps.setString(i++, user.getCmt());
				
				int result = ps.executeUpdate();
				if(result != 0){
					rs = ps.getGeneratedKeys();
					if(rs.next()){
						return rs.getInt(1);
					}	
				}
			}catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
			
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see dao.UserDao#updateImage(java.lang.String, int)
	 */
	@Override
	public boolean updateImage(String image, int userId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE `tbl_user` SET `image`= ? WHERE `user_id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, image);
				ps.setInt(2, userId);
				
				int result = ps.executeUpdate();
				if(result != 0){
					return true;
				}
			}catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
			
			
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see dao.UserDao#updateUser(entity.User)
	 */
	@Override
	public boolean updateUser(User user) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE `tbl_user` SET `email`=?,`pass`=?,`phone`=?,`fax`=?,`office`=?,`list_subject`=?,`research`=?,`released_engine`=?,`released_book`=?,`other`=?,`role`=?,`name`=?,`title`=?,`birthday`=?,`sex`=?,`que_quan`=?,`dan_toc`=?,`dia_chi_hien_tai`=?,`tieu_su_cong_tac`=?,`cmt`=? WHERE `user_id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
				int i=1;
				ps.setString(i++, user.getEmail());
				ps.setString(i++, user.getPass());
				ps.setString(i++, user.getPhone());
				ps.setString(i++, user.getFax());
				ps.setString(i++, user.getOffice());
				ps.setString(i++, user.getListSubject());
				ps.setString(i++, user.getResearch());
				ps.setString(i++, user.getReleasedEngine());
				
				ps.setString(i++, user.getReleasedBook());
				ps.setString(i++, user.getOther());
				ps.setBoolean(i++, user.isRole());
				ps.setString(i++, user.getName());
				ps.setString(i++, user.getTitle());
				ps.setDate(i++, user.getBirthDay());
				ps.setBoolean(i++, user.isSex());
				ps.setString(i++, user.getQueQuan());
				ps.setString(i++, user.getDanToc());
				ps.setString(i++, user.getAddressNow());
				ps.setString(i++, user.getCongTac());
				ps.setString(i++, user.getCmt());
				
				ps.setInt(i++, user.getUserId());
				
				ps.executeUpdate();
				return true;
				
			}catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
			
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.UserDao#delUser(int)
	 */
	@Override
	public boolean delUser(int userId) {
		if (connectToDB()) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM `tbl_user` WHERE `user_id` = ?");
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setInt(1, userId);
				
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				close();
			}
		}
		return false;
	}
}
