package com.kh.start.member.model.service;

import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.dto.UpdatePasswordDTO;

public interface MemberService {
	
	void signUp(MemberDTO member);
	
	void updatePassword(UpdatePasswordDTO passwordEntity);
	
	void deleteMember(String password);
}
