package com.coderby.myapp.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderby.myapp.member.model.JSONVO;

@Controller
@RequestMapping("/json")
public class JSONController {
	
	
	@RequestMapping(value="/ex")
	@ResponseBody 
	public String exam(@RequestBody JSONVO jsonVO) {
		return "name : "+jsonVO.getName()+", age : "+jsonVO.getAge();
		//한글 써주고싶을때는 produces="application/text; charset=UTF-8" 써주기
	}
	
	@RequestMapping(value="/example", produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String example(String userId) {
		return "ajax 데이터 : "+userId;
	}
	
	
	
	/** @RequestBody, @ResponseBody
	 : 각각의 HTTP요청 몸체를 자바객체로 변환하고 자바객체를 HTTP응답 몸체로 변환
	*/
	
	/**
	 * 회원가입 페이지에서 아이디 중복검사 기능 추가하기 !
	 * 중복검사 클릭했을떄 gif 로딩 이미지랑 alert 경고창 띄우기
	 * 중복 되지 않는 아이디면 중복검사 버튼 없어지고 그 값 그대로 유지 (날라가지 않고)
	 * 중복 되는 아이디면 값들 초기화 
	 * */
	
}
