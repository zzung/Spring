package com.coderby.myapp.member.dao;

import java.util.List;

import com.coderby.myapp.member.model.AuthoritiesVO;
import com.coderby.myapp.member.model.MemberVO;

public interface IMemberRepository {
	
	void insertMember(MemberVO member);
	void insertAuth(String userId);
	void updateMember(MemberVO mem);
	void deleteMember(String userId);
	void deleteAuthority(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	List<MemberVO> getMemberList(int page, String word);
	List<AuthoritiesVO> getAuthorityList();
	void changeEnabled(String userId);
	void changeAuth(String userId,String auth);
	List<MemberVO> searchMember(String keyword);
	int getMemberCount(String keyword);
	Integer checkUserId(String userId);
	
	
}
