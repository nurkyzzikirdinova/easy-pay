package easypay.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import zikirdinova.easypay.enums.PaymentStatus;
import zikirdinova.easypay.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(generator = "payment_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "payment_gen", sequenceName = "payment_seq", allocationSize = 1)

    private Long id;

    private String paymentReference;

    @NotNull
    private Double amount;

    @NotNull
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne(mappedBy = "payment")
    private QRCode qrCode;

    @OneToOne(mappedBy = "payment")
    private Fine fine;
}