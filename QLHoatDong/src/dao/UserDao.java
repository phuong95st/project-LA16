/**
 * Copyright(C) 2017 Luvina software company
 * UserDao.java, Mar 2, 2017 nguyenhuuphuong
 */
package dao;

import java.sql.Timestamp;

import entity.User;

/**
 * 
 * @author nguyenhuuphuong
 * 
 */
public interface UserDao {
	/**
	 * Kiểm tra tài khoản có tồn tại
	 * 
	 * @param email
	 *            email
	 * @param password
	 *            password
	 * @return true: tồn tại tài khoản. false: không tồn tại tài khoản
	 */
	public boolean checkLogin(String email, String password);

	/**
	 * Lấy thông tin user trong db từ email
	 * 
	 * @param email
	 *            email
	 * @return Đối tượng User
	 */
	public User getUserByEmail(String email);
	
	public boolean updateLastLogin(Timestamp time, int userId);
	
	public Timestamp getLastLogin(int userId);
}
