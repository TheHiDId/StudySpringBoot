package com.kh.start.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePasswordDTO {

	@Size(min = 4, max = 20, message = "비밀번호 길이는 4자에서 20자 사이여야 합니다.")
	@Pattern(regexp = "^[a-z0-9]*$", message = "잘못된 비밀번호 형식입니다.")
	@NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
	private String currentPassword;
	
	@Size(min = 4, max = 20, message = "새 비밀번호 길이는 4자에서 20자 사이여야 합니다.")
	@Pattern(regexp = "^[a-z0-9]*$", message = "잘못된 새 비밀번호 형식입니다.")
	@NotBlank(message = "새 비밀번호는 비어있을 수 없습니다.")
	private String newPassword;
}
