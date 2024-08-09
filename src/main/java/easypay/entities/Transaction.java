package easypay.entities;

import jakarta.persistence.*;
import lombok.*;
import zikirdinova.easypay.enums.TransactionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(generator = "transaction_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "transaction_gen", sequenceName = "transaction_seq", allocationSize = 1)
    private Long id;

    private String transactionReference;

    private LocalDateTime transactionDate;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}