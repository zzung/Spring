package com.coderby.myapp.member.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyJdbcTemplate extends JdbcTemplate {
	
	public MyJdbcTemplate(DataSource datasource) {
		super(datasource);
	}

	//queryForObject를 사용했을때 null값이 나올때 처리하려고 두개의 메서드를 만듬
	
	//RowMapper일때 (매퍼용)
	//<T>이 메서드에 제네릭을 쓰겠다는 뜻 , 아무거나 들어오고 나갈수 있도록 하기 위해서 T로 넣은것
	public <T> T queryForNullableObject(String sql, RowMapper<T> rowMapper, Object...args) throws DataAccessException {
		List<T> results = query(sql, rowMapper, args);
		if(results == null || results.isEmpty()) {
			return null;
		}else if (results.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1, results.size());
			//객체가 1보다 크면 한개값이 아니라는 뜻,예외처리
		}else {
			return results.iterator().next();
			//객체가 1개이면 results에 있는 값 리턴
		}
	}
	
	//String이나 Integer로 받을때 (클래스용)
	public <T> T queryForNullableObject(String sql, Class<T> requiredType, Object...args) throws DataAccessException{
		return this.queryForNullableObject(sql, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				T result = (T)rs.getObject(1); //어떤 타입인지 모르니까 Object로 받음
				return result;
			}
		}, args);
	}
	
	
	
	
	
	
	
}
