package com.yichen.casetest.test.chainOfResponsibility;

/**
 * @author Qiuxinchao
 * @date 2023/3/29 8:57
 * @describe
 */
public abstract class ValidationStep<T> {

    private ValidationStep<T> next;

    public ValidationStep<T> linkWith(ValidationStep<T> next) {
        if (this.next == null) {
            this.next = next;
            return this;
        }
        ValidationStep<T> lastStep = this.next;
        while (lastStep.next != null) {
            lastStep = lastStep.next;
        }
        lastStep.next = next;
        return this;
    }

    public abstract ValidationResult validate(T toValidate);

    protected ValidationResult checkNext(T toValidate) {
        if (next == null) {
            return ValidationResult.valid();
        }

        return next.validate(toValidate);
    }
}
