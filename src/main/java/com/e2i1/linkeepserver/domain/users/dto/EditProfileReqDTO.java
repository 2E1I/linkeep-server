package com.e2i1.linkeepserver.domain.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileReqDTO {

    @NotBlank(message = "닉네임은 필수 입력입니다.")
    private String nickname;

    private Boolean isDeletedImg;

    private String description;
}
