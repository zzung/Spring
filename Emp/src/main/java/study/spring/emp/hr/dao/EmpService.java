package study.spring.emp.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpVO;


@Service
public class EmpService implements IEmpService {

	@Autowired
	@Qualifier("IEmpRepository") //해당 타입빈이 여러개일경우 
	IEmpRepository empRepository;
	
	
	@Override
	public int getEmpCount() {
		return empRepository.empCount();
	}
	@Override
	public int getEmpCount(int deptId) {
		return empRepository.empCount(deptId);
	}
	@Override
	public List<EmpVO> getEmpList() {
		return empRepository.empList();
	}
	@Override
	public EmpVO getEmpInfo(int empId) {
		return empRepository.getEmpInfo(empId);
	}

	@Transactional("txManager") //-----xml파일에서 transaction-manager 이름 지정안해주면 이것도 이름따로 지정 안해도됨
	@Override
	public void updateEmp(EmpVO emp) {
		empRepository.deleteJobHistory(emp.getEmployeeId()); 
		empRepository.updateEmp(emp);
	}

	@Override
	public void insertEmp(EmpVO emp) {
		empRepository.insertEmp(emp);
	}

	@Transactional("txManager")
	@Override
	public void deleteEmp(int empId) {
		empRepository.changeManager(empId);
		empRepository.deleteJobHistory(empId);
		empRepository.deleteEmp(empId);
	}
	
	@Transactional("txManager")
	@Override
	public void deleteJobHistory(int empId) {
		empRepository.deleteJobHistory(empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		return empRepository.getAllDeptId();
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		return empRepository.getAllManagerId();
	}

	@Override
	public List<DeptVO> deptList() {
		return empRepository.deptList();
	}

	@Override
	public List<EmpVO> maxSalary() {
		return empRepository.maxSalary();
	}

	
	@Override
	public Map<String, Object> empGetCount(int empId) {
		return empRepository.empGetCount(empId);
	}
	@Override
	public void changeManager(int empId) {
		empRepository.changeManager(empId);
	}
	@Override
	public List<EmpVO> haveAboveAvgSalaryByDept() {
		return empRepository.haveAboveAvgSalaryByDept();
	}

}
