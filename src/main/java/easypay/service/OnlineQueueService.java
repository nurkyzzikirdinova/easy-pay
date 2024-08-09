package easypay.service;

import zikirdinova.easypay.dto.request.OnlineQueueRequest;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.OnlineQueue;
import zikirdinova.easypay.entities.User;
import zikirdinova.easypay.exception.NotFoundException;

import java.util.List;

public interface OnlineQueueService {
    OnlineQueue createQueue(OnlineQueueRequest request);

    OnlineQueue updateQueue(Long id, OnlineQueueRequest request) throws NotFoundException;

    SimpleResponse deleteQueue(Long id) throws NotFoundException;

    OnlineQueue getQueueById(Long id, OnlineQueueRequest request) throws NotFoundException;

    List<OnlineQueue> getAllQueues();

    List<OnlineQueue> getQueueByUser(User user);
}
