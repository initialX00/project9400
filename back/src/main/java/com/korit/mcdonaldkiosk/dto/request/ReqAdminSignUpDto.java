package com.korit.mcdonaldkiosk.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReqAdminSignUpDto {

    @NotBlank(message = "관리자 이름은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,}$", message = "관리자 이름은 영어와 숫자 조합으로 5자 이상이어야 합니다.")
    private String adminName;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "비밀번호는 영어와 숫자 조합으로 8자 이상이어야 합니다.")
    private String adminPassword;

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "상호명은 필수 입력 항목입니다.")
    @Size(max = 16, message = "상호명은 16자 이내로 입력해야 합니다.")
    private String tradeName;
}
