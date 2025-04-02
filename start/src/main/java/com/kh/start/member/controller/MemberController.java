package com.kh.start.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.dto.UpdatePasswordDTO;
import com.kh.start.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	/* /members == 약속
	 * GET -> 조회 요청 (SELECT)
	 * GET(/members/멤버번호) -> 멤버번호로 조건을 걸어서 단일 조회 요청 (SELECT)
	 * POST -> 데이터 생성 요청 (INSERT)
	 * PUT -> 데이터 갱신 요청 (UPDATE)
	 * DELETE -> 데이터 삭제 요청 (DELETE)
	 * 
	 * 계층 구조로 식별할 때 /자원/PK
	 * 요청 시 전달값이 많을 때 /자원?키=값&키=값&키=값
	 */
	
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody @Valid MemberDTO member) {
		
		memberService.signUp(member);
		
		return ResponseEntity.status(201).build();
	}
	
	@PutMapping
	public ResponseEntity<?> updatePassword(@RequestBody @Valid UpdatePasswordDTO passwordEntity) {
		
		memberService.updatePassword(passwordEntity);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteMember(@RequestBody Map<String, String> request) {
		
		memberService.deleteMember(request.get("password"));
		
		return null;
	}
}
