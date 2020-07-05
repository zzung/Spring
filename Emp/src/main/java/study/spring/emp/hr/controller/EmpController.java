package study.spring.emp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderby.myapp.util.MyValidator;

import study.spring.emp.hr.dao.IEmpService;
import study.spring.emp.hr.model.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
	@Autowired
	MyValidator myValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//WebDataBinder : 모델구성해주고 값만들어서 유효성 검사하는 용도
		binder.setValidator(myValidator); //내가 만든 validator를 실행하도록 설정 (어노테이션을 쓸때 사용하도록)
	}
	
	@RequestMapping("/hr/main")
	public String empMain(Model model) {
		return "/hr/main";
	}
	
	//사원 정보 삭제
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping(value="/hr/delete")
	public String empDelete(@RequestParam(value="empId", required=false, defaultValue = "0") int empId, Model model) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("count",empService.empGetCount(empId));
		return "/hr/delete";
		}
	
	//삭제 후 목록으로 보내기
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping(value="/hr/delete")
	public String empDelete(Model model, int empId) {
		empService.changeManager(empId);
		empService.deleteJobHistory(empId);
		empService.deleteEmp(empId);
		return "redirect:/hr/list";
	}

	//사원 정보 수정
	@GetMapping(value="/hr/update")
	public String empUpdate(Model model, @RequestParam(value="empId", required=false, defaultValue = "0") int empId) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message","update");
		return "hr/insert";
	}	
	
	//사원 정보 수정 후 목록으로 보내기
	@PostMapping(value="/hr/update")
	public String empUpdate(EmpVO emp) {
		empService.updateEmp(emp);
		return "redirect:/hr/view";
	}

	
	//사원 정보 입력
	@GetMapping(value="/hr/insert") 
	public void empInsert(Model model) {
		model.addAttribute("emp",new EmpVO()); //빈객체 하나 던져줌 (유효성검사하는 용도)
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message","insert");
	}
	
	//사원 정보 입력 후 목록으로 보내기
	@PostMapping(value="/hr/insert")
	public String empInsert(@ModelAttribute("emp") @Valid EmpVO emp, BindingResult result, Model model) {
		//@ModelAttribue : RequestParam과 같은 원리. 객체(emp)를 넘겨줌
		//@Valid : 검증할 대상
		//BindingResult : 무조건 EmpVO뒤에 넣어줘야함.(앞에 있는 변수를 다 검증하려고 하니까, 앞에 매개변수는 검증할 매개변수로 설정해줘야함)
		//				---에러메세지가 담겨있음
		//입력하고 저장하면 ? 리스트로 감 --> 객체 필요x
		//파라미터에 있는 "EmpVO emp == 커맨드 객체" ----요청할때 들어온 model(emp객체)가 응답할때 "그대로" emp객체로 내보냄.
		if(result.hasErrors()) { //에러가 있는지 없는지 리턴해줌(페이지에서 뜨게해줘야함,다시 insert로 보내서 에러메세지를 띄워줘야함)
//			model.addAttribute("emp",emp);
//			model.addAttribute("error",error); ---------------커맨드객체 안에 그대로 들어있어서 또 설정 안해도됨!
			model.addAttribute("jobList",empService.getAllJobId());
			model.addAttribute("manList",empService.getAllManagerId());
			model.addAttribute("deptList",empService.getAllDeptId());
			model.addAttribute("message","insert");
			return "hr/insert";
		}else {
			empService.insertEmp(emp);
			return "redirect:/hr/list";//주소창에 직접칠거니까 /hr/list 붙여주기 
		}
	}
	
	//사원번호로 사원 정보 조회
	@GetMapping(value="/hr/view")
	public String empView(Model model,@RequestParam(value="employeeId", required = true) int employeeId) {
		model.addAttribute("emp", empService.getEmpInfo(employeeId));
		return "hr/view";
	}
	
	//전체 사원 목록
	@GetMapping(value="/hr/list")
	public void empList(Model model) {	
		model.addAttribute("list",empService.getEmpList());
		model.addAttribute("message","getEmpList");
	}
	
	//부서별 사원 인원수
	@GetMapping(value="/hr/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue = "0") int deptId, Model model) {
		if(deptId==0) {
			model.addAttribute("count", empService.getEmpCount());
			model.addAttribute("message","allEmpCount");
		}else {
			model.addAttribute("count", empService.getEmpCount(deptId));
			model.addAttribute("message","deptEmpCount");
		}
		return "hr/count";
	}
	//@RequestParam : request.getparameter 했던 것들을 자동으로 값을 변수에 매핑시켜주는 용도
	
	
	//부서별 최고 급여자 조회
	@GetMapping(value="/hr/maxSalary")
	public String maxSalary(Model model) {
		model.addAttribute("list",empService.maxSalary());
		model.addAttribute("message","maxSalary");
		return "hr/list";
	}
	
	//부서별 평균이상 급여자 조회
	@GetMapping(value="/hr/haveAboveAvgSalaryByDept")
	public String haveAboveAvgSalaryByDept(Model model) {
		model.addAttribute("list",empService.haveAboveAvgSalaryByDept());
		model.addAttribute("message","haveAboveAvgSalaryByDept");
		return "hr/list";
	}
	
//	------------------------------------------------------------------------------------------------------
	//예외처리-컴파일 예외/런타임예외 두가지가있음, 
	@ExceptionHandler(RuntimeException.class) 
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("url",request.getRequestURI());
		model.addAttribute("exception",ex);
		return "error/runtime";
	}

	//json - 일반 텍스트 형태. 파싱하는 방식은 똑같은 형태. 언어에 상관없이 받기 쉬움
	//웹에서 동작하는것. ---> 웹에서 동작하는 servlet-context.xml에 빈 적얻놓기
	//뷰에다가 전해주는 것이 아니라 데이터를 뿌려주는 식
	//pom.xml에 라이브러리 추가해준다음 MessageConverter 추가해주면 자동으로 실행해줌.
	@RequestMapping(value="/hr/json/list")
	public @ResponseBody List<EmpVO> getAllEmployees() {
		List<EmpVO> empList = empService.getEmpList();
		return empList;
	}
	
	@RequestMapping(value="/hr/json/{employeeId}")
	public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		return emp;
	}
	
	/*
	 * 리다이렉트 : 겟방식, 요청을 다시 보내는것(새로운요청)--> 객체가 없어짐. / ----모델이 다시 생겨야됨
	 * 객체가 없어지기 전에 세션에 저장했다가 세션에 있는 정보를 addFlashAttribute 메서드로 빼서 사용가능
	 * addAttribute : 세션에 복사한게 아니라서 URL에 데이터가 보임.
	 */
	
	/*
	 * 입력값 검증 ? 클라이언트, 서버 둘다에서 해야함
	 * 자바스크립트 또는 다른방식으로 클라이언트에 설정해놓아도 피해갈수있으니까 서버측에서도 설정해놓아야함
	 * EmpVO 객체를 하나 던져놓고
	 * 모델 만들어지고 세팅되는 순서 p167
	 */
	
}
