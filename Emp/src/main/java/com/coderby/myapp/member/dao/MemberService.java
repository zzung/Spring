package com.coderby.myapp.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderby.myapp.member.model.AuthoritiesVO;
import com.coderby.myapp.member.model.MemberVO;

@Service
public class MemberService implements IMemberService {

	@Autowired
	IMemberRepository memRepository;
	
	@Transactional("txManager")
	@Override
	public void insertMember(MemberVO mem) {
		memRepository.insertMember(mem);
		memRepository.insertAuth(mem.getUserId());
	}

	@Override
	public MemberVO getMember(String userId) {
		return memRepository.getMember(userId);
	}

	@Override
	public String getPassword(String userId) {
		return memRepository.getPassword(userId);
	}

	@Transactional("txManager")
	@Override
	public void insertAuth(String userId) {
		memRepository.insertAuth(userId);
	}

	@Transactional("txManager")
	@Override
	public void updateMember(MemberVO mem) {
		memRepository.updateMember(mem);
	}

	@Transactional("txManager")
	@Override
	public void deleteMember(String userId) {
		memRepository.deleteAuthority(userId);
		memRepository.deleteMember(userId);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memRepository.getMemberList();
	}

	@Override
	public void changeEnabled(String userId) {
		memRepository.changeEnabled(userId);
	}

	@Override
	public List<AuthoritiesVO> getAuthorityList() {
		return memRepository.getAuthorityList();
	}

	@Override
	public void deleteAuthority(String userId) {
		memRepository.deleteAuthority(userId);
	}

	@Override
	public void changeAuth(String userId, String auth) {
		memRepository.changeAuth(userId, auth);
	}

	@Override
	public List<MemberVO> searchMember(String keyword) {
		return memRepository.searchMember(keyword);
	}

	
	
	
	
	
	
	
	
	
}
