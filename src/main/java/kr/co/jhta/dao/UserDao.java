package kr.co.jhta.dao;

import kr.co.jhta.vo.User;


public interface UserDao {
	
	User getUserById(String id);
	void addUser(User user);
	void udateUser(User user);
	
	void getUserByNo(int no);
}
