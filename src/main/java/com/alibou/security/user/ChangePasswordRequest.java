package com.alibou.security.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor // Add this annotation
@AllArgsConstructor
public class ChangePasswordRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}