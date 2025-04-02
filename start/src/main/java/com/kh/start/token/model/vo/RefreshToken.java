package com.kh.start.token.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class RefreshToken {

	private String token;
	private Long memberNo;
	private Long expiration;
	
}
