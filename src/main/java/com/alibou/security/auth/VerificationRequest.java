package com.alibou.security.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationRequest {
    private String email;
    private String code;
}
