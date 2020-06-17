package com.coderby.myapp.member.dao;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberRepository {
	void insertMember(MemberVO member);
	void insertAuth(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	
}
