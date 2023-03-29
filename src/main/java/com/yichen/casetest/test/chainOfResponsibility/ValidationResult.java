package com.yichen.casetest.test.chainOfResponsibility;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/3/29 8:57
 * @describe
 */
@Value
@Slf4j
public class ValidationResult {
    private final boolean isValid;
    private final String errorMsg;

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(String errorMsg) {
        log.info("ValidationResult invalid {}", errorMsg);
        return new ValidationResult(false, errorMsg);
    }

    public boolean notValid() {
        return !isValid;
    }
}
