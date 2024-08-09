package easypay.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fines")
@Getter
@Setter
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fine_gen")
    @SequenceGenerator(name = "fine_gen", sequenceName = "fine_seq", allocationSize = 1)
    private Long id;

    private Double amount;

    private LocalDateTime issuedDate;

    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean paid;

    @OneToOne
    private Payment payment;
}
