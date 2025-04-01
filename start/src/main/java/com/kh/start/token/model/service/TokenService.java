package com.kh.start.token.model.service;

import java.util.Map;

public interface TokenService {
	// 1. AccessToken 만들기
	
	// 2. RefreshToken 만들기
	
	Map<String, String> generateToken(String username);
	
	// 3. RefreshToken Table에 INSERT 하기
	
	// 4. 만료기간이 끝난 RefreshToken DELETE 하기
	
	// 5. 사용자가 RefreshToken을 가지고 증명하려 할 때 DB가서 SELECT 하기
}
