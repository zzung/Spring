package study.spring.emp.hr.model;

public class EmpDetailVO extends EmpVO{
	
	private String deptName;
	private String managerName;
	private String jobTitle;
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Override
	public String toString() {
		return "EmpDetailVO [deptName=" + deptName + ", managerName=" + managerName + ", jobTitle=" + jobTitle + "]";
	}

	
}
