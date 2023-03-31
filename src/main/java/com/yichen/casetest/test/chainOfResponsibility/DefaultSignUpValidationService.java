package com.yichen.casetest.test.chainOfResponsibility;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Qiuxinchao
 * @date 2023/3/29 8:58
 * @describe 责任链 demo  =>  https://mp.weixin.qq.com/s/ErNXsmOuiLbHu7xtyDi2Vw
 */
@Service
@AllArgsConstructor
public class DefaultSignUpValidationService implements SignUpValidationService {

    @Override
    public ValidationResult validate(SignUpCommand command) {
        return new CommandConstraintsValidationStep()
                .linkWith(new UsernameDuplicationValidationStep())
                .linkWith(new EmailDuplicationValidationStep())
                .validate(command);
    }

    private static class CommandConstraintsValidationStep extends ValidationStep<SignUpCommand> {

        @Override
        public ValidationResult validate(SignUpCommand command) {
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                final Validator validator = validatorFactory.getValidator();
                final Set<ConstraintViolation<SignUpCommand>> constraintsViolations = validator.validate(command);

                if (!constraintsViolations.isEmpty()) {
                    return ValidationResult.invalid(constraintsViolations.iterator().next().getMessage());
                }
            }
            return checkNext(command);
        }
    }

    @NoArgsConstructor
    private static class UsernameDuplicationValidationStep extends ValidationStep<SignUpCommand> {


        @Override
        public ValidationResult validate(SignUpCommand command) {
            if (!"yichen".equals(command.getUsername())) {
                return ValidationResult.invalid(String.format("Username [%s] is already taken", command.getUsername()));
            }
            return checkNext(command);
        }
    }

    @NoArgsConstructor
    private static class EmailDuplicationValidationStep extends ValidationStep<SignUpCommand> {

        @Override
        public ValidationResult validate(SignUpCommand command) {
            if (!"163.com".equals(command.getEmail())) {
                return ValidationResult.invalid(String.format("Email [%s] is already taken", command.getEmail()));
            }
            return checkNext(command);
        }
    }
}
