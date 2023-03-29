package com.yichen.casetest.test.chainOfResponsibility;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Qiuxinchao
 * @date 2023/3/29 8:56
 * @describe
 */
@Value
public class SignUpCommand {

    @Size(min = 2, max = 40)
    @NotBlank
    private final String firstName;

    @Size(min = 2, max = 40)
    @NotBlank
    private final String lastName;

    @Size(min = 2, max = 40)
    @NotBlank
    private final String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private final String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private final String rawPassword;
}
