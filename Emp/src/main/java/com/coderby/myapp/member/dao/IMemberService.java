package com.coderby.myapp.member.dao;

import java.util.List;
import java.util.Map;

import com.coderby.myapp.member.model.AuthoritiesVO;
import com.coderby.myapp.member.model.MemberVO;

public interface IMemberService {
	
	void insertMember(MemberVO mem);
	void insertAuth(String userId);
	void updateMember(MemberVO mem);
	void deleteMember(String userId);
	void deleteAuthority(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	List<MemberVO> getMemberList(int page, String keyword);
	void changeEnabled(String userId);
	List<AuthoritiesVO> getAuthorityList();
	void changeAuth(String userId,String auth);
	List<MemberVO> searchMember(String keyword);
	int getMemberCount(String keyword);
	Integer checkUserId(String userId);
	
}
