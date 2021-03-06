package com.coderby.myapp.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.member.dao.IMemberService;
import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.util.PagingManager;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	//고정값을 계속 바뀐 암호값으로 제공
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("message","insert");
		return "member/insert";
	}
	
	@PostMapping("/insert")
	public String insert(Model model, MemberVO member, RedirectAttributes redirectAttributes) {
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memberService.insertMember(member);
		model.addAttribute("message","insert");
		redirectAttributes.addFlashAttribute("message","회원 가입 완료");
		return "redirect:/login";
	}

	@PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN','ROLE_MASTER') or #userId==principal.username)")
	@GetMapping("/info")
	public String memberInfo(Model model, @RequestParam(value="userId",required=false, defaultValue="/") String userId) {
		model.addAttribute("member",memberService.getMember(userId));
		model.addAttribute("authList",memberService.getAuthorityList());
		return "member/info";
	}
	@PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN','ROLE_MASTER') or #userId==principal.username)")
	@GetMapping("/update")
	public String updateMember(Model model, @RequestParam(value="userId",required=false, defaultValue="/") String userId) {
		model.addAttribute("member",memberService.getMember(userId));
		model.addAttribute("message","update");
		return "member/insert";
	}
	
	@PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN','ROLE_MASTER') or #member.userId==principal.username)")
	@PostMapping("/update")
	public String updateMember(MemberVO member) {
		memberService.updateMember(member);
		return "redirect:/member/info?userId="+member.getUserId();
	}
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MASTER')")
	@GetMapping("/list")
	public String memList(@RequestParam(required=false, defaultValue = "1") int page, @RequestParam(required=false) String keyword, Model model) {
		model.addAttribute("memList",memberService.getMemberList(page, keyword));
		model.addAttribute("pageManager", new PagingManager(memberService.getMemberCount(keyword), page));
		return "member/list";
	}
	
	@PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN','ROLE_MASTER') or #userId==principal.username)")
	@GetMapping("/delete")
	public String deleteMember(@RequestParam(value="userId", required=false, defaultValue = "/") String userId, Model model) {
		model.addAttribute("member",memberService.getMember(userId));
		return "member/delete";
	}
	
//	isAuthenticated() --- 익명이 아니라는것 , 인증은 안됀거 
//	어노테이션 사용했을떄 : #userId==principal.username (내가 provider에서 리턴타입을 memberVO로 했으니까 principal==객체/ userId면 principal==userId)
	@PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER'))")
	@PostMapping("/delete")
	public String deleteMember(Model model, String pw, String userId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if((authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) || 
				(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER")))){
			memberService.deleteMember(userId);
			return "redirect:/member/list";
		} else {
			String dbpw = memberService.getPassword(authentication.getName());
			if(!bpe.matches(pw, dbpw)) {
				throw new RuntimeException("비밀번호가 다릅니다.");	
			}else {
				memberService.deleteMember(userId);
			}
			return "redirect:/logout";
		}
	}
	
	@PostMapping("/enabled")
	public String changeEnabled(String userId) {
		memberService.changeEnabled(userId);
		memberService.getMember(userId);
		return "redirect:/member/list";
	}
	
	@PostMapping("/changeAuth")
	public String changeAuth(String userId, String authority) {
		memberService.changeAuth(userId, authority);
		return "redirect:/member/info?userId="+userId;
	}
	
	@GetMapping("/search")
	public String searchMember(String keyword, Model model) {
		model.addAttribute("memList",memberService.searchMember(keyword));
		return "member/list";
	}
	
	@ResponseBody //ajax 요청이 들어온 주소에 그대로 0 or 1 보내짐 --> xhr.responseText 나 jquery의 success 메서드 매개변수에 0or1 전달
	@PostMapping("/check")
//	@RequestMapping(value="/", method=RequestMethod.POST)
	public Integer checkUserId(String userId) {
		System.out.println(userId);
		return memberService.checkUserId(userId);
	}
	
	
}
