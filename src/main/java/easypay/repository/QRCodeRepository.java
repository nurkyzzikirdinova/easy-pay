package easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zikirdinova.easypay.entities.QRCode;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    // Можно добавить дополнительные методы поиска при необходимости
}
