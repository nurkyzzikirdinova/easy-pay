package easypay.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zikirdinova.easypay.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "histories")
@Getter
@Setter
public class PaymentHistory {
    @Id
    @GeneratedValue(generator = "history_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "history_gen", sequenceName = "history_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
