package study.spring.emp.hr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpDetailVO;
import study.spring.emp.hr.model.EmpVO;

public interface IEmpRepository {
	
	int empCount();
	int empCount(@Param("deptId") int deptId);
	List<EmpVO> empList();
	EmpVO getEmpInfo(@Param("empId") int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(@Param("empId") int empId);
	void deleteJobHistory(@Param("empId") int empId);
	List<Map<String,Object>> getAllDeptId();
	List<Map<String,Object>> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	
	List<DeptVO> deptList(); 
	List<EmpVO> maxSalary(); //jobs테이블에 있는 부서별 최대급여
	Map<String, Object> empGetCount(@Param("empId") int empId);
	void changeManager(@Param("empId") int empId);
	List<EmpVO> haveAboveAvgSalaryByDept();
	
}
