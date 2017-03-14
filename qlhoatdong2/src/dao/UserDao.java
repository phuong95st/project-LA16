package dao;

import entity.User;

public interface UserDao {
	public boolean checkLogin(String email,  String pass);
	
	public User getUserByEmail(String email);
}	
