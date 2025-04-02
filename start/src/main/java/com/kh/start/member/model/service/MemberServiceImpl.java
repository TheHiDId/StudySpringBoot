package com.kh.start.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.MemberIdDuplicateException;
import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.dto.UpdatePasswordDTO;
import com.kh.start.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	private String passwordEncode(String password) {
		
		return passwordEncoder.encode(password);
	}
	
	private Long passwordMatches(String password) {
		
		// 1. 현재 비밀번호와 새로운 비밀번호가 같지 않은지 확인
		// 2. SecurutyContextHolder에서 사용자 정보 가져오기
		// 3. 올바른 비밀번호를 입력했는지 확인
		// 4. 새로운 비밀번호 암호화
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		return user.getMemberNo();
	}
	
	@Override
	public void signUp(MemberDTO member) {

		MemberDTO searchedMember = memberMapper.getMemberByMemberId(member.getMemberId());
		
		if(searchedMember != null) {
			throw new MemberIdDuplicateException("이미 존재하는 아이디입니다.");
		}
		
		Member memberValue = Member.builder()
								   .memberId(member.getMemberId())
								   .memberPw(passwordEncode(member.getMemberPw()))
								   .memberName(member.getMemberName())
								   .role("ROLE_USER")
								   .build();
		
		memberMapper.signUp(memberValue);
	}

	@Override
	public void updatePassword(UpdatePasswordDTO passwordEntity) {
		
		Long memberNo = passwordMatches(passwordEntity.getCurrentPassword());
		
		String encodedPassword = passwordEncode(passwordEntity.getNewPassword());
		
		Map<String, Object> updateRequest = new HashMap();
		
		updateRequest.put("memberNo", memberNo);
		updateRequest.put("encodedPassword", encodedPassword);
		
		int result = memberMapper.updatePassword(updateRequest);
		
		if(result != 1) new RuntimeException("알 수 없는 이유로 예외가 발생했습니다.");
	}

	@Override
	public void deleteMember(String password) {

		Long memberNo = passwordMatches(password);
		
		int result = memberMapper.deleteMember(memberNo);
		
		if(result != 1) new RuntimeException("알 수 없는 이유로 예외가 발생했습니다.");
	}

}
