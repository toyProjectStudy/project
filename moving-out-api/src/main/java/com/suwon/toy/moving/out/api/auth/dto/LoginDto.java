/**
 * ===============================================================
 * File name : LoginDto.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="login시 요청으로 받는 정보 입니다.")
public class LoginDto {

    @ApiModelProperty(name="user-id", required = true, dataType = "String", example = "jay1234")
    @NotNull
    @Size(min=3, max=100)
    private String userId;

    @ApiModelProperty(name="password", notes = "가급적 hashing 된 값이 넘어왔으면 좋겠습니다.", required = true, dataType = "String", example = "1234")
    @NotNull
    private String password;
}
