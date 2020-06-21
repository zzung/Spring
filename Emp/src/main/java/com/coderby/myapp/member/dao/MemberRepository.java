package com.coderby.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.member.model.AuthoritiesVO;
import com.coderby.myapp.member.model.MemberVO;

import study.spring.emp.hr.model.EmpVO;

@Repository
public class MemberRepository implements IMemberRepository{

	@Autowired
	MyJdbcTemplate jt;
	
	private class MemMapper implements RowMapper<MemberVO>{
		@Override
		public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberVO mem = new MemberVO();
			mem.setUserId(rs.getString("userid"));
			mem.setName(rs.getString("name"));
			mem.setPassword(rs.getString("password"));
			mem.setEmail(rs.getString("email"));
			mem.setAddress(rs.getString("address"));
			mem.setEnabled(rs.getInt("enabled"));
			return mem;
		}
	}
	
	@Override
	public void insertMember(MemberVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?)";
		jt.update(sql,mem.getUserId(),mem.getUsername(),mem.getPassword(), mem.getEmail(), 
				mem.getAddress(),mem.getEnabled());
	}

	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		jt.update(sql, userId, "ROLE_USER");
	}

	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, name, password, email, address, enabled, "
				+ "authority from member m "
				+ "join authorities au "
				+ "on m.userid=au.userid "
				+ "where m.userid=? ";
		return jt.query(sql, new ResultSetExtractor<MemberVO>() {
			//extractData : rs.next 가되기 때문에 null처리가 가능함
			@Override
			public MemberVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setName(rs.getString("name"));
					mem.setPassword(rs.getString("password"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				}else {
					return null;
				}
			}
		}, userId);
	}

	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return jt.queryForNullableObject(sql, String.class, userId);
	}

	@Override
	public void updateMember(MemberVO member) {
		String sql = "update member set name=?, email=?, address=?, enabled=? "
				+ "where userid=?";
		jt.update(sql, member.getName(), member.getEmail(),
				member.getAddress(), member.getEnabled(), member.getUserId());
	}

	@Override
	public void deleteMember(String userId) {
		String sql = "delete from member where userid=?";
		jt.update(sql, userId);
	}

	@Override
	public List<MemberVO> getMemberList() {
		String sql = "select * from member m " + 
				"left join authorities au " + 
				"on m.userid=au.userid ";
		return jt.query(sql,new RowMapper<MemberVO> () {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mem = new MemberVO();
				mem.setUserId(rs.getString("userid"));
				mem.setName(rs.getString("name"));
				mem.setPassword(rs.getString("password"));
				mem.setEmail(rs.getString("email"));
				mem.setAddress(rs.getString("address"));
				mem.setEnabled(rs.getInt("enabled"));
				mem.setAuth(rs.getString("authority"));
				return mem;
			}
		});
	}

	@Override
	public void changeEnabled(String userId) {
		String sql = "update member set enabled = case when enabled=0 then 1 "
				+ "when enabled=1 then 0 end where userid=?";
		jt.update(sql,userId);
	}

	@Override
	public List<AuthoritiesVO> getAuthorityList() {
		String sql = "select distinct authority from authorities";
		return jt.query(sql, new RowMapper<AuthoritiesVO>() {
			@Override
			public AuthoritiesVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				AuthoritiesVO au = new AuthoritiesVO();
				au.setAuthority(rs.getString("authority"));
				return au;
			}
		});
	}

	@Override
	public void deleteAuthority(String userId) {
		String sql = "delete from authorities where userid=?";
		jt.update(sql,userId);
	}

	@Override
	public void changeAuth(String userId, String auth) {
		String authority = "ROLE_"+auth;
		String sql = "update authorities set authority=? where userid=?";
		jt.update(sql, authority, userId);
	}

	@Override
	public List<MemberVO> searchMember(String keyword) {
		String kw = "%"+keyword+"%";
		String sql = "select * from member m " + 
				"left join authorities au " + 
				"on m.userid=au.userid " + 
				"where m.userid like ? or name like ?";
		return jt.query(sql, new RowMapper<MemberVO> () {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO mem = new MemberVO();
				mem.setUserId(rs.getString("userid"));
				mem.setName(rs.getString("name"));
				mem.setPassword(rs.getString("password"));
				mem.setEmail(rs.getString("email"));
				mem.setAddress(rs.getString("address"));
				mem.setEnabled(rs.getInt("enabled"));
				mem.setAuth(rs.getString("authority"));
				return mem;
			}
		}, kw,kw);
	}


	
	

	
}
