package com.coderby.myapp.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyObjectMapper extends ObjectMapper{
	//# 웹에서 날짜 방식이  timeStamps로 나왔을때 (yyyy-MM-dd)형태로 변경하는 메서드 
	//default 매퍼를 자기방식으로 조금만(날짜만) 변경할 용도
	//---생성자에 넣어주면 자동으로 적용시키게 할수 있음
	private static final long serialVersionUID = 1L;
	
	public MyObjectMapper() {
		disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); //---표현식 찾아서 그때그때 설정에 맞춰서 사용
	}
	
	
}
