package easypay.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name ="qr_codes")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCode {
    @Id
    @GeneratedValue(generator = "qrcode_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "qrcode_gen", sequenceName = "qrcode_seq", allocationSize = 1)

    private Long id;

    private String code;

    private LocalDateTime generatedDate;

    @ManyToOne
    private User user;

    @OneToOne
    private Payment payment;
}
