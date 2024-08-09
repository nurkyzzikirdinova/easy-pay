package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.OnlineQueueRequest;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.OnlineQueue;
import zikirdinova.easypay.entities.User;
import zikirdinova.easypay.exception.NotFoundException;
import zikirdinova.easypay.service.OnlineQueueService;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
@Tag(name = "OnlineQueue-api")
public class OnlineQueueApi {
    private final OnlineQueueService onlineQueueService;

    @PostMapping("/createQueue")
    public OnlineQueue createQueue(@RequestBody OnlineQueueRequest request) {
        return onlineQueueService.createQueue(request);
    }


    @PutMapping("/updateQueue/{id}")
    public OnlineQueue updateQueue(@PathVariable Long id, @RequestBody OnlineQueueRequest request) throws NotFoundException {
        return onlineQueueService.updateQueue(id, request);
    }


    @GetMapping("/getQueueById/{id}")
    public OnlineQueue getQueueById(@PathVariable Long id, @RequestBody OnlineQueueRequest request) throws NotFoundException {
        return onlineQueueService.getQueueById(id, request);
    }

    @GetMapping("/getAllQueues")
    public List<OnlineQueue> getAllQueues() {
        return onlineQueueService.getAllQueues();
    }

    @GetMapping("/getQueueByUser")
    public List<OnlineQueue> getQueueByUser(User user) {
        return onlineQueueService.getQueueByUser(user);
    }

    @DeleteMapping("/deleteQueue/{id}")
    public SimpleResponse deleteQueue(@PathVariable Long id) throws NotFoundException {
        return onlineQueueService.deleteQueue(id);
    }
}
