package com.coderby.myapp.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.member.dao.IMemberService;
import com.coderby.myapp.member.model.MemberVO;

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
	
	@GetMapping("/info")
	public String memberInfo(Model model, @RequestParam(value="userId",required=false, defaultValue="/") String userId) {
		model.addAttribute("member",memberService.getMember(userId));
		model.addAttribute("authList",memberService.getAuthorityList());
		return "member/info";
	}
	
	@GetMapping("/update")
	public String updateMember(Model model, @RequestParam(value="userId",required=false, defaultValue="/") String userId) {
		model.addAttribute("member",memberService.getMember(userId));
		model.addAttribute("message","update");
		return "member/insert";
	}
	
	@PostMapping("/update")
	public String updateMember(MemberVO member) {
		memberService.updateMember(member);
		return "redirect:/member/info?userId="+member.getUserId();
	}
	
	@GetMapping("/list")
	public String memList(Model model) {
		model.addAttribute("memList",memberService.getMemberList());
		return "member/list";
	}
	
	@GetMapping("/delete")
	public String deleteMember(@RequestParam(value="userId", required=false, defaultValue = "/") String userId, Model model) {
		model.addAttribute("mem",memberService.getMember(userId));
		return "member/delete";
	}
	
	@PostMapping("/delete")
	public String deleteMember(Model model, String pw) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String dbpw = memberService.getPassword(authentication.getName());
		if(!bpe.matches(pw, dbpw)) {
			throw new RuntimeException("비밀번호가 다릅니다.");	
		}
		memberService.deleteMember(authentication.getName());
		return "redirect:/member/list";
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
	
	
	
}
