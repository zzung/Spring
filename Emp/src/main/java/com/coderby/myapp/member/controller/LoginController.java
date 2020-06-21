package com.coderby.myapp.member.controller;

import java.time.LocalDateTime;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderby.myapp.member.dao.IMemberService;
import com.coderby.myapp.member.model.MemberVO;

@Controller
public class LoginController {
	
	@Autowired
	IMemberService memberService;
	
	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
//	UsernamePasswordAuthenticationToken ;(security 자체에서)인증이 됐다고 판단/메시지 전달 가능
//	AnonymousAuthenticationToken ; 인증이 안됐다고 판단/메시지 전달 불가능/기본 토큰을 컨트롤러에게 전달
	
/* provider에서 전달받는 authentication객체(UsernamePasswordAuthenticationToken)는 인증이 되었다고 판단
 * 아이디가 없으면 null처리 --> 인증 안된 객체. --> AnonymousAuthenticationFilter에 걸려서 익명객체로 만들어짐.
 *		 --> 이객체의 principal은 무조건 anonymousUser로 들어옴
 * UsernamePasswordAuthenticationToken 객체의 아이디를 null로 바꾸고 아이디가 잘못되었다는 메시지를 전달한다면?
 * --> null로 바꿔도 이미 Security자체에서는 Username~~Token으로 받았기 때문에 인증이 되었다고 판단해서, null처리가 안됨.
 * --> 아예 필터 자체를 커스터마이징 하기
 * */	
	@RequestMapping("/loginCheck")
	public String loginCheck(Model model, HttpSession session) {
//			authentication = (AnonymousAuthenticationToken)authentication; --형변환해도 이미 인증된 객체라고 판단함
//			if(authentication.equals(AnonymousAuthenticationToken.class)) {
//				model.addAttribute("message","아이디가 없습니다. 다시 확인해주세요!");
//			}else if(authentication.getCredentials()==null) {
//				model.addAttribute("message","비밀번호가 잘못됐습니다. 다시 확인해주세요!");
//			}
//			model.addAttribute("message","아이디 또는 비밀번호가 잘못됐습니다.");
//			return "login";
			//내 세션에 해당하는 details에 있는 memberVO가 들어있음
			//MemberVO객체가 들어가있으면 true, 없으면 false
			session.setAttribute("startTime", LocalDateTime.now());
//			String url="/";	//세션에서 주소를 꺼내와서 로그인 성공이후 보내줌
//			if(session.getAttribute("url")!=null) {
//				url=(String)session.getAttribute("url");
//			}
			return "redirect:/";
		}
	
}
