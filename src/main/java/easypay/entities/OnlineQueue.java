package easypay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OnlineQueue {
    @Id
    @GeneratedValue(generator = "queue_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "queue_gen", sequenceName = "queue_seq", allocationSize = 1)

    private Long id;

    private LocalDateTime queueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
