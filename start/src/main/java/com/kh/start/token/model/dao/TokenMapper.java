package com.kh.start.token.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.start.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {
	
	RefreshToken selectToken(RefreshToken token);
	
	void insertToken(RefreshToken token);
	
	void deleteExpiredToken(Long currentTime);
}
