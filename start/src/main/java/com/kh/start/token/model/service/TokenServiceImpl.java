package com.kh.start.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.start.auth.util.JwtUtil;
import com.kh.start.token.model.dao.TokenMapper;
import com.kh.start.token.model.vo.RefreshToken;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	
	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;

	private Map<String, String> createToken(String username) {
		
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> tokens = new HashMap<String, String>();
		
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}
	
	private void insertToken(String refreshToken, Long memberNo) {
		RefreshToken token = RefreshToken.builder()
										 .token(refreshToken)
										 .memberNo(memberNo)
										 .expiration(System.currentTimeMillis() + 36000000L * 24 * 3)
										 .build();
		
		tokenMapper.insertToken(token);
	}
	
	private void deleteExpiredToken() {
		
		tokenMapper.deleteExpiredToken(System.currentTimeMillis());
	}
	
	private String getUsernameByToken(String refreshToken) {
		
		Claims claims = tokenUtil.parseJwt(refreshToken);
		
		return claims.getSubject();
	}
	
	@Override
	public Map<String, String> generateToken(String username, Long memberNo) {

		Map<String, String> tokens = createToken(username);
		
		insertToken(tokens.get("refreshToken"), memberNo);
		
		deleteExpiredToken();
		
		return tokens;
	}

	@Override
	public Map<String, String> refreshToken(String refreshToken) {
		
		RefreshToken token = RefreshToken.builder().token(refreshToken).build();
		
		RefreshToken responseToken = tokenMapper.selectToken(token);
		
		/* 1. JwtUtil을 이용하여 refreshToken을 Parsing한 뒤 MEMBER_NO 및 token값을 이용하여 SELECT하는 문으로 수정
		 * 2. 예외 발생 시 예외 처리기가 처리하도록 만들기
		 */
		
		if(responseToken == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new RuntimeException("유효하지 않은 토큰입니다.");
		}
		
		String username = getUsernameByToken(refreshToken);
		
		Long memberNo = responseToken.getMemberNo();
		
		return generateToken(username, memberNo);
	}

}
