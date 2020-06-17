package com.coderby.myapp.hr;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import study.spring.emp.hr.dao.IEmpService;
import study.spring.emp.hr.model.EmpVO;

public class EmpMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext con = new GenericXmlApplicationContext("app-config.xml");
		IEmpService empService = con.getBean("empService",IEmpService.class);
		/*
		System.out.println(empService.getEmpCount()+"\n");
		System.out.println(empService.getEmpCount(30)+"\n");
		System.out.println(empService.getEmpList()+"\n");
		System.out.println(empService.getAllManagerId()+"\n");
		System.out.println(empService.deptList()+"\n");
		System.out.println(empService.maxSalary()+"\n");
		*/
		empService.updateEmp(empService.getEmpInfo(108));
//		empService.empSearch("susan");
		
	
		
		con.close();
	}

}
