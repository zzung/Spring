package com.coderby.myapp.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.coderby.myapp.member.dao.IMemberService;
import com.coderby.myapp.member.dao.MemberService;
import com.coderby.myapp.member.model.MemberVO;

@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	/* 1. AuthenticationManager가 전달받은 토큰(authentication객체)을 supports메서드의 매개변수로 넣어서,
	 * 	  검증할 객체인지 아닌지 판별후 Provider에게 전달
	 * 2. false면 검증 x, true면 authenticate메서드 실행
	 * 3. result에 인증이 완료된 MemberVO객체를 넣어서 반환
	 * 4. SecurityContext 안에 Authentication객체로 저장됨 (검증 완료된 세션으로 저장)
	 * 
	 * * 객체들을 검증할때마다 여러개의 provider가 생성되고, 각각의 인증 방식을 위해 사용됨
	 * 	  그 중 인증 완료된 객체는 또다시 인증할 필요가 없음 --> 그 객체를 매개변수로 받아서 판별(MemberVO 혹은 null)
	 *   --> MemberVO객체를 담고있음 ==인증이 끝난상태 ==false리턴==인증x  
	 *   --> null을 담고있음== 인증을 안한 상태==true리턴==인증 진행
	 * * 중간에 null처리되는 것==인증 안된것==익명토큰 생성 (AnonymousAuthenticationToken)
	 * * 인증 된것 == UsernamePasswordAuthenticationToken 생성
	 * * 인증완료된 객체는 SecurityContext안에 있다가 추후에 같은 세션에서 요청이 들어오면 그것을 반환해줌
	 * */
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("로그인 시작");
		if(authentication.getPrincipal()==null) {
			return null;
		}
		if(authentication.getCredentials()==null) {
			return null;
		}
		String userId = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		String dbpw = memberService.getPassword(userId);
		if(dbpw==null) {
			throw new InternalAuthenticationServiceException("아이디가 없습니다.");
//			throw new UsernameNotFoundException(msg); ---BadCredentialsException으로 넘겨버려서 메시지 전달못함
		}
		if(!bpe.matches(password, dbpw)) { //원래 비번이랑 암호화된 비번 비교
			throw new BadCredentialsException("비밀번호가 다릅니다.");	//null이면 anonyMousUser라고 저장
		}
		MemberVO member = memberService.getMember(userId);
		if(!member.isEnabled()) {
			throw new DisabledException("정지당한 계정입니다 .관리자에게 문의하세요");
		}
		System.out.println("로그인 완료");
		UsernamePasswordAuthenticationToken result =
				new UsernamePasswordAuthenticationToken(member, password, member.getAuthorities());
		//principal이 객체 자체를 뜻할떄도 있음
		//토큰에 id,password,authorities만 들어가있을때는 다른 userDetails 정보가 필요할떄 가져오기 어려우니까 , 객체 자체를 넘겨주는것이 편함.
		return result;
	}
	


}
