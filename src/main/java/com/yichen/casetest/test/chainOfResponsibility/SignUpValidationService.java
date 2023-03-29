package com.yichen.casetest.test.chainOfResponsibility;

/**
 * @author Qiuxinchao
 * @date 2023/3/29 8:57
 * @describe
 */
public interface SignUpValidationService {
    ValidationResult validate(SignUpCommand command);
}
