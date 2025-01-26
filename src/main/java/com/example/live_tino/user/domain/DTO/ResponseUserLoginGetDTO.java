package com.example.live_tino.user.domain.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserLoginGetDTO {
    String loginId;
}
