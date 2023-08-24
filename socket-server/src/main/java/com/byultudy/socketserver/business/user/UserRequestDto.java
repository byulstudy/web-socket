package com.byultudy.socketserver.business.user;

import com.byultudy.socketserver.utils.TimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserRequestDto {

    private LocalDateTime at;

    public long atToEpochMilli() {
        return TimeUtils.toEpochMilli(this.at);
    }
}
