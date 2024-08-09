package easypay.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.dto.request.OnlineQueueRequest;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.OnlineQueue;
import zikirdinova.easypay.entities.User;
import zikirdinova.easypay.exception.NotFoundException;
import zikirdinova.easypay.repository.OnlineQueueRepository;
import zikirdinova.easypay.service.OnlineQueueService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnlineQueueServiceImpl implements OnlineQueueService {

    private final OnlineQueueRepository onlineQueueRepository;

    @Override
    public OnlineQueue createQueue(OnlineQueueRequest request) {
        OnlineQueue queue = new OnlineQueue();
        queue.setQueueDate(request.getQueueDate());
        queue.setUser(request.getUser());
        return onlineQueueRepository.save(queue);
    }

    @Override
    public OnlineQueue updateQueue(Long id, OnlineQueueRequest request) throws NotFoundException {
        OnlineQueue queue = onlineQueueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Queue entry not found with id: " + id));
        queue.setQueueDate(request.getQueueDate());
        queue.setUser(request.getUser());
        return onlineQueueRepository.save(queue);
    }


    @Override
    public SimpleResponse deleteQueue(Long id) throws NotFoundException {
        if (!onlineQueueRepository.existsById(id)) {
            throw new NotFoundException("Queue entry not found with id: " + id);
        }
        onlineQueueRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "Online queue with id " + id + " deleted successfully!");
    }


    @Override
    public OnlineQueue getQueueById(Long id, OnlineQueueRequest request) throws NotFoundException {
        return onlineQueueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Queue not found with id: " + id));
    }

    @Override
    public List<OnlineQueue> getAllQueues() {
        return onlineQueueRepository.findAll();
    }

    @Override
    public List<OnlineQueue> getQueueByUser(User user) {
        return onlineQueueRepository.findAllByUser(user);
    }
}
