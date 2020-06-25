package study.spring.emp.file.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.emp.file.IFileService;
import study.spring.emp.file.model.FileVO;

@Controller
public class FileController {

	@Autowired
	IFileService fileService;
	
	@RequestMapping("/file")
	public ModelAndView fileHome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/file/index");
		return mav;
	}
	
	@GetMapping("/file/new")
	public String uploadFile(Model model) {
		return "/file/new";
	}
	
	@PostMapping("/file/new")
	//requestparam은 값이 꼭 들어와야할때 사용
	public String uploadFile(@RequestParam(value="dir",required=false, defaultValue="/") String dir,
			@RequestParam MultipartFile file, RedirectAttributes redirectAttrs) {
		try {
			if(file!=null && !file.isEmpty()) {
				FileVO newFile = new FileVO();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				newFile.setDirectoryName(dir);
				newFile.setFileSize(file.getSize());
				newFile.setFileName(file.getOriginalFilename());
				newFile.setFileContentType(file.getContentType());
				newFile.setFileData(file.getBytes());
				newFile.setUserId(authentication.getName()); //아이디
				fileService.uploadFile(newFile);
			}
		}catch(IOException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message",e.getMessage());
		}
		return "redirect:/file/list";
	}
	
	@GetMapping({"/img/{fileId}","/pds/{fileId}"})
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId){
		FileVO file =fileService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file !=null) {
			String[] mtypes=file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0],mtypes[1]));
			headers.setContentDispositionFormData("attachment", file.getFileName());
			headers.setContentLength(file.getFileSize());
			return new
					ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	//ResponseEntity : 파일 데이터를 보낼때 사용 / Entity속에 있는 데이터 자체를 응답 메시지로 넘겨줌.
	//경로를 두개로 나눔. (이미지 용, 일반파일용)
	//header : 메시지의 타입을 결정하는 것
	//응답헤더가 바뀌면 안되니까 final 걸어준것
	//setContentType:파일이 다운되는 유형 별로 다시 설정해주는것. (.mp3 / .pdf 등)
	//HttpStatus.OK ; http상태메시지 출력하는 것 (200번대 : 정상적으로 작동되었을때 띄어주는 것
	//400번대 : 클라이언트 에러코드
	
	@RequestMapping("/file/list")
	public String getAllFileList(Model model) {
		model.addAttribute("fileList",fileService.getAllFileList());
		return "file/list";
	}
	
	@RequestMapping("/file/list/{dir}")
	public String getFileListByDir(@PathVariable String dir, Model model) {
		model.addAttribute("fileList",fileService.getFileListBydir("/"+dir));
		return "file/list";
	}
	
	@RequestMapping("/file/updateDir")
	public String updateDirectory(int[] fileIds, String directoryName, String[] userId) {
		System.out.println(Arrays.toString(userId));
		//체크박스를 선택했을때 fileId & userId 넘기기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if((authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
				||(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER")))) {
			//권한 인증 --security가 하니까 알아볼수있는 권한 형태로 바꿔줘야함 (리턴타입 : collection<? extends GrantedAuthority>)
	
		}else {
			for(String Id:userId) {
				if(!authentication.getName().equals(Id)) {
					return "redirect:/error/runtime";
				}
			}
		}
			fileService.updateDirectory(fileIds,directoryName);
			return "redirect:/file/list";
	}
	
	@RequestMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId, String userId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String url = "";
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			fileService.deleteFile(fileId);
			url ="redirect:/file/list";
		}else if(authentication.getName().equals(userId)) {
			fileService.deleteFile(fileId);
			url ="redirect:/file/list";
		}else {
			url="/error/autherror";
		}
		return url;
	}
	
	@GetMapping("/file/info")
	public void getFileInfo(int fileId, Model model) {
		model.addAttribute("file",fileService.getFile(fileId));
	}
	
	
	
	
}
