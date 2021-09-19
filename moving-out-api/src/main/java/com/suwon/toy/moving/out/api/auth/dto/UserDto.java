/**
 * ===============================================================
 * File name : UserDto.java
 * Created by injeahwang on 2021-07-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(description="sign-up(회원 가입)시 요청으로 받는 정보 입니다. - 인증 과정 전에 수행합니다.")
public class UserDto {

    @ApiModelProperty(name="user-id", required = true, dataType = "String", example = "jay1234")
    @NotNull
    @Size(min = 3, max = 50)
    private String userId;

    @ApiModelProperty(name="user-id", required = true, dataType = "String", example = "1234")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @ApiModelProperty(name="user-id", required = true, dataType = "String", example = "jayhwang")
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @ApiModelProperty(name="user-id", required = true, dataType = "String", example = "사랑시 고백구 행복동")
    private String address;

    @ApiModelProperty(name="user-id", required = true, dataType = "String", example = "000-0000-0000")
    @NotNull
    private String phoneNumber;


    private String email;
}