package ua.com.kneu.lab4.payment_validator;

public sealed class ValidationResult {

    public static final class SuccessfulValidationResult extends ValidationResult {
    }

    public static final class FailureValidationResult extends ValidationResult {
        private final String message;

        public String getMessage() {
            return message;
        }

        FailureValidationResult(String message) {
            this.message = message;
        }
    }
}
