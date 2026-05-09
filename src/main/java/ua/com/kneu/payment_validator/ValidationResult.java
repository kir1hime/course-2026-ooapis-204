package ua.com.kneu.payment_validator;

// сілд інтерфейс для визначення результату валідації
public sealed interface ValidationResult {

    // клас для визначення успішного результату
    final class SuccessfulValidationResult implements ValidationResult {
    }

    // клас для визначення неуспішного результату
    final class FailureValidationResult implements ValidationResult {
        private final String message;

        public String getMessage() {
            return message;
        }

        FailureValidationResult(String message) {
            this.message = message;
        }
    }
}
