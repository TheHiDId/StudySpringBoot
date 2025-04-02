package com.kh.start.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Override
	public Map<String, String> login(MemberDTO member) {
		
		/* 1. 유효성 검증
		 * 2. 아이디가 DB에 존재하는 아이디인가 확인
		 * 3. 비밀번호는 DB에 존재하는 암호문이 사용자가 입력한 평문으로 만들어진 것이 맞는지 확인
		 * 4. 토큰 발급하고 반환
		 */
		Authentication authentication = null;
		
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getMemberPw()));
			
		} catch (AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		}
		
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		
		// AccessToken과 RefreshToken 발급하고 리턴
		Map<String, String> loginResponse = tokenService.generateToken(user.getUsername(), user.getMemberNo());
		
		loginResponse.put("memberId", user.getUsername());
		loginResponse.put("memberName", user.getMemberName());

		return loginResponse;
	}

}
