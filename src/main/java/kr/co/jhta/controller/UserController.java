package kr.co.jhta.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jhta.service.UserService;
import kr.co.jhta.utils.SessionUtils;
import kr.co.jhta.vo.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인 페이지
	@RequestMapping(value="/user/login.do",method=RequestMethod.GET)
	public String loginform(){
		return "login/loginform";
	}
	//로그인 처리
	@RequestMapping(value="/user/login.do", method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, 
						@RequestParam("checkBox") boolean checkBox, @RequestParam("autoLogin") boolean autoLogin,
						HttpSession session, HttpServletRequest request){
		
		User user = userService.getUserById(id,pwd);
		if(user == null){
			return "redirect:login.do?error=fail";
		}
		if(pwd.equals(user.getPwd())){
			return "redirect:login.do?error=fail";
		}	
		SessionUtils.setAttribute("LOGIN_USER",user);
		
		//쿠키처리
		
		
		
		return "redirect:home.do";
		
	}
	
	//회원가입ㅍ 페이지
	@RequestMapping(value="/user/registerform.do")
	public String registerform(){
		return "login/registerform";
	}
	//회원가입 처리
	@RequestMapping(value="/register.do" )
	public String register(User user){
		userService.addUser(user);
		return "login/login";
	}
	
	
	//회원 수정
	@RequestMapping(value="/user/updateform.do")
	public String updateform(){
		return "login/udapteform";
	}
	
	//회원가입 수정처리
	@RequestMapping(value="/user/update.do")
	public String update(User user){
		userService.updateByUser(user);
		return "redirect:home.do";
	}	
	
	
	//로그아웃
	@RequestMapping(value="/user/logout.do")
	public String logout(HttpSession session){
		SessionUtils.removeAttribute("LOGIN_USER");
		return "redirect:home.do";
	}
	
	
	
}
