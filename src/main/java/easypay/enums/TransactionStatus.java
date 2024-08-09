package easypay.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {
    PENDING,
    COMPLETED,
    FAILED,
    CANCELLED;

    @JsonCreator
    public static TransactionStatus forValue(String value) {
        return TransactionStatus.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
