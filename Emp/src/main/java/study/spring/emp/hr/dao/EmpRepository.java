package study.spring.emp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import study.spring.emp.hr.model.DeptVO;
import study.spring.emp.hr.model.EmpDetailVO;
import study.spring.emp.hr.model.EmpVO;

@Repository   
public class EmpRepository implements IEmpRepository {
/* @Repository ?
 * DB와 관련된, SQL문과 관련된 코드만 적음.
 * 나머지 비즈니스 로직은 Service에 적음.
 * ---> 의존관계 약화시킴 (DB수정 되면 Repository만 수정/ 비즈니스로직 수정되면 Service만 수정)
*/
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	//방법2 ---RowMapper 변수 선언해서 사용하는 방법.
	RowMapper<DeptVO> deptMapper = new RowMapper<DeptVO> () {
		@Override
		public DeptVO mapRow(ResultSet rs, int count) throws SQLException {
			DeptVO dept = new DeptVO();
			dept.setDepartmentId(rs.getInt("department_id"));
			dept.setDepartmentName(rs.getString("department_name"));
			dept.setLocationId(rs.getInt("location_id"));
			dept.setManagerId(rs.getInt("manager_id"));
			return dept;
		}
	};
	
	private class EmpMapper implements RowMapper<EmpVO>{
		@Override
		public EmpVO mapRow(ResultSet rs, int count) throws SQLException{
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString(4));
			emp.setPhoneNumber(rs.getString(5));
			emp.setHireDate(rs.getDate(6));
			emp.setJobId(rs.getString(7));
			emp.setSalary(rs.getDouble(8));
			emp.setCommissionPct(rs.getDouble(9));
			emp.setManagerId(rs.getInt(10));
			emp.setDepartmentId(rs.getInt(11));
			return emp;
		}
	}
	/* RowMapper ?
		- 쿼리문의 결과를 여러번 매핑하지 않기 위해 JdbcTemplate에서 만들어진 클래스.
		- 보통 인터페이스를 구현해서 익명내부클래스로 사용함.
		- 원하는객체를 한번에 만들어서 써라 라는 용도
	*/
	
	//총 사원 수 
	@Override
	public int empCount() {
		String sql = "select count(*) from employees "; //* 대신 employeeId 넣는게 확실함
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	//부서별 사원 수 
	@Override
	public int empCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
	}
	// * 대신 employeeId 됨, managerId 안됨 ---------------------------------------------------같은 페이지 사용

	@Override
	public List<EmpVO> empList() {
		String sql = "select * from employees";
		return jdbcTemplate.query(sql, new EmpMapper());
	}
	
	//employees테이블 + departments테이블 + jobs테이블 
	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select e.employee_id, first_name, last_name, email, phone_number, hire_date, e.job_id, job_title, "
				+ "salary, commission_pct, e.manager_id, manager_name, e.department_id, department_name " 
				+ "from employees e "
				+ "left join departments d "
				+ "on d.department_id = e.department_id "
				+ "left join jobs j "
				+ "on j.job_id = e.job_id "
				+ "left join (select employee_id, first_name||' '||last_name as manager_name "
				+ "from employees "
				+ "where employee_id in (select distinct manager_id from employees)) m "
				+ "on m.employee_id=e.manager_id "
				+ "where e.employee_id=?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<EmpDetailVO>() {
			@Override
			public EmpDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmpDetailVO emp = new EmpDetailVO();
				emp.setEmployeeId(empId);
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate(6));
				emp.setJobId(rs.getString("job_id"));
				emp.setJobTitle(rs.getString("job_title"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setManagerName(rs.getString("manager_name"));
				emp.setDepartmentId(rs.getInt("department_id"));
				emp.setDeptName(rs.getString("department_name"));
				return emp;
			}
		},empId);
	}

	@Override
	public void updateEmp(EmpVO emp) {
		String sql = "update employees set first_name=?, last_name=?, email=?, phone_number=?,"
				+ "hire_date=?, job_id=?, salary=?, commission_Pct=?, manager_id=?, department_id=?"
				+ " where employee_id=?";
		jdbcTemplate.update(sql,emp.getFirstName(),emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(),
				emp.getHireDate(),emp.getJobId(), emp.getSalary(), emp.getCommissionPct(),emp.getManagerId(),
				emp.getDepartmentId(),emp.getEmployeeId());
	}

	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql, emp.getEmployeeId(),emp.getFirstName(), emp.getLastName(),emp.getEmail(),
				emp.getPhoneNumber(),emp.getJobId(),emp.getSalary(),emp.getCommissionPct(),emp.getManagerId(),
				emp.getDepartmentId());
	}
	//물음표는 테이블 열 순서대로 넣어줘야함 = EmpVO 순서대로!
	//sysdate 지금 데이터가 들어가는 날짜를 넣어줌--> hiredate()는 sql sysdate로 시간 넣어줄거니까 매개변수에 안넣음

	@Override
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql,empId);
	}

	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql = "select department_id as departmentId, department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		String sql = "select job_id as jobId, job_title as jobTitle from jobs";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId, first_name||' '||last_name as managerName "
				+ "from employees where employee_id in (select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<DeptVO> deptList() {
		String sql = "select * from departments";
		return jdbcTemplate.query(sql,deptMapper);
	} 
	//한개만 추출할게 아니니까 매개변수 x
	//잘 안쓸꺼니까 위에다가 구현하지말고 익명내부클래스로 만들어주기.
	//익명 클래스로 쓰는 이유 : 여기서 한번 쓰고 다시 안쓸거니까 ! 최소 10번이상 쓸때 클래스 따로 만들어줘야함.

	
	// 부서별 최대 급여자의 (부서아이디+급여)
	@Override
	public List<EmpVO> maxSalary() {
		String sql = "select * from employees where (department_id,salary) in" + 
				"(select department_id, max(salary) from employees group by department_id)";
		return jdbcTemplate.query(sql,new EmpMapper());
	}

	//매니저 아이디가 ?인 사원의 수 + 매니저아이디가 ?인 사원이 맡고있는 부서 갯수
	@Override
	public Map<String, Object> empGetCount(int empId) {
		String sql = "select (select count(*) from employees where manager_id=?) as empCount,"
				+ "(select count(*) from departments where manager_id=?) as deptCount from dual";
		return jdbcTemplate.queryForObject(sql,new RowMapper<Map<String,Object>>(){
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Object> map = new HashMap<>();
				map.put("empCount", rs.getInt("empCount"));
				map.put("deptCount",rs.getInt("deptCount"));
				return map;
			}
		},empId,empId);
	}

	@Override
	public void changeManager(int empId) {
		String sql = "update employees set manager_id=null where manager_id=?";
		jdbcTemplate.update(sql, empId);
		String sql2 = "update departments set manager_id=null where manager_id=?";
		jdbcTemplate.update(sql2,empId);
	}

	@Override
	public List<EmpVO> haveAboveAvgSalaryByDept() {
		String sql = "select * from (select * from employees e " + 
				"join (select department_id, avg(salary) as avg_salary "
				+ "from employees group by department_id) d "
				+ "on e.department_id = d.department_id) "
				+ "where salary > avg_salary";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	
	
	
	
	
	
}