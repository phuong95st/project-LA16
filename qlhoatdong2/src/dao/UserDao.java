package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	public boolean checkLogin(String email,  String pass);
	
	public User getUserByEmail(String email);
	
	public List<User> getListUserByRole(boolean role);
	
	public User getUserById(int userId);
	
	public boolean isExistEmail(String email, int userId);
	
	public int addUser(User user);
	
	public boolean updateImage(String image, int userId);
	
	public boolean updateUser(User user);
	
	public boolean delUser(int userId);
}	
