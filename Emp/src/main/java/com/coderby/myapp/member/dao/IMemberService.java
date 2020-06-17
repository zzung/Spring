package com.coderby.myapp.member.dao;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberService {
	
	public void insertMember(MemberVO mem);
	public MemberVO getMember(String userId);
	public String getPassword(String userId);
}
