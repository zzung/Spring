package study.spring.emp.hr.dao;

import java.util.List;
import java.util.Map;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpDetailVO;
import study.spring.emp.hr.model.EmpVO;

public interface IEmpRepository {
	
	int empCount();
	int empCount(int deptId);
	List<EmpVO> empList();
	EmpVO getEmpInfo(int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(int empId);
	void deleteJobHistory(int empId);
	List<Map<String,Object>> getAllDeptId();
	List<Map<String,Object>> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	
	List<DeptVO> deptList(); 
	List<EmpVO> maxSalary(); //jobs테이블에 있는 부서별 최대급여
	Map<String, Object> empGetCount(int empId);
	void changeManager(int empId);
	List<EmpVO> haveAboveAvgSalaryByDept();
	
}
