package com.byultudy.socketserver.business.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

    private long epochMilli;
    private String session;

    public static UserResponseDto from(long epochMilli , String session) {
        return new UserResponseDto(epochMilli , session);
    }

}
