package kr.co.jhta.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import kr.co.jhta.vo.User;

public interface UserService {
	//유저 찾기
	User getUserById(String id,String pwd);
	void addUser(User user);
	void updateByUser(User user);
	

	
	
}
