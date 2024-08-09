package easypay.enums;

public enum PaymentStatus {
    PENDING,      // Платеж ожидает обработки
    COMPLETED,    // Платеж завершен успешно
    FAILED,       // Платеж не удался
    CANCELLED     // Платеж был отменен
}