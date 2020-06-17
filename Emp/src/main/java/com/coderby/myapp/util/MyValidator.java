package com.coderby.myapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import study.spring.emp.hr.model.EmpVO;

@Component
public class MyValidator implements Validator{

	//먼저 유효성검사할 객체인지 확인 (맞으면 true, 아니면 false)
	//클래스가 EmpVO객체인지 확인 (구현하거나상속하거나 관련된 객체인지 먼저 확인)
	@Override
	public boolean supports(Class<?> clazz) {
		return EmpVO.class.isAssignableFrom(clazz);
	}

	//target으로 받아온 타입은 EmpVO니까 일단 Object로 받아놓고 형변환 시켜주기.
	@Override
	public void validate(Object target, Errors errors) {
		EmpVO emp = (EmpVO)target; //집어넣은 객체의 모든 값들이 들어있음 
		
		if(emp.getEmployeeId()<207) {
//			# rejectValue메서드의 매개변수
//			errors.rejectValue(field, errorCode); ---field: 멤버변수 값들 
			errors.rejectValue("employeeId", "emp.employeeId","employee_id가 207이상이여야 함");
		}
		if(emp.getFirstName().length()>12) {
			errors.rejectValue("firstName", "emp.firstName","first_name의 길이가 너무 김");
		} else if ((emp.getFirstName().isEmpty()) || (emp.getLastName().isEmpty())) {
			errors.rejectValue("firstName", "emp.firstName","first_name이 비어있음");
		}
		if(emp.getLastName().length()>12) {
			errors.rejectValue("lastName", "emp.lastName","last_name의 길이가 너무 김");
		}else if ((emp.getLastName().isEmpty())) {
			errors.rejectValue("lastName", "emp.lastName","last_name이 비어있음");
		}
		
		
	}

}
