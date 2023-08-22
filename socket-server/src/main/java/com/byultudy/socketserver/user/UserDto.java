package com.byultudy.socketserver.user;

import com.byultudy.socketserver.utils.TimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserDto {

    private LocalDateTime at;

    public long atToEpochMilli() {
        return TimeUtils.toEpochMilli(this.at);
    }
}
