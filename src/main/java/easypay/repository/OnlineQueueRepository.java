package easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zikirdinova.easypay.entities.OnlineQueue;
import zikirdinova.easypay.entities.User;

import java.util.List;

@Repository
public interface OnlineQueueRepository extends JpaRepository<OnlineQueue, Long> {
    List<OnlineQueue> findAllByUser(User user);
}
