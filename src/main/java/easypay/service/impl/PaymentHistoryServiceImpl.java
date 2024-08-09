package easypay.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.dto.request.PaymentHistoryRequest;
import zikirdinova.easypay.dto.response.PaymentHistoryResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.PaymentHistory;
import zikirdinova.easypay.enums.PaymentStatus;
import zikirdinova.easypay.repository.PaymentHistoryRepository;
import zikirdinova.easypay.service.PaymentHistoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public SimpleResponse createPaymentHistory(PaymentHistoryRequest request) {
        PaymentHistory history = new PaymentHistory();
        history.setPayment(request.getPayment());
        history.setTimestamp(request.getTimestamp());
        history.setPaymentStatus(request.getPaymentStatus());
        history.setAmount(request.getAmount());
        history.setUser(request.getUser());

        paymentHistoryRepository.save(history);
        return new SimpleResponse(HttpStatus.OK, "Payment history created successfully");
    }

    @Override
    public SimpleResponse updatePaymentHistory(Long id, PaymentHistoryRequest request) {
        PaymentHistory history = paymentHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment history with id " + id + " not found"));
        history.setPayment(request.getPayment());
        history.setTimestamp(request.getTimestamp());
        history.setPaymentStatus(request.getPaymentStatus());
        history.setAmount(request.getAmount());
        history.setUser(request.getUser());

        paymentHistoryRepository.save(history);
        return new SimpleResponse(HttpStatus.OK, "Payment history with id " + id + " updated successfully");
    }

    @Override
    public SimpleResponse deletePaymentHistory(Long id) {
        if (!paymentHistoryRepository.existsById(id)) {
            throw new RuntimeException("Payment history with id " + id + " not found");
        }
        paymentHistoryRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "Payment history with id " + id + " deleted successfully");
    }

    @Override
    public PaymentHistoryResponse getPaymentHistoryById(Long id) {
        PaymentHistory history = paymentHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with id " + id + " history not found"));
        return mapToResponse(history);
    }

    @Override
    public List<PaymentHistoryResponse> getAllPaymentHistories() {
        return paymentHistoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentHistoryResponse> getPaymentHistoriesByStatus(PaymentStatus status) {
        return paymentHistoryRepository.findAllByPaymentStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentHistoryResponse mapToResponse(PaymentHistory history) {
        PaymentHistoryResponse response = new PaymentHistoryResponse();
        response.setId(history.getId());
        response.setPayment(history.getPayment());
        response.setTimestamp(history.getTimestamp());
        response.setPaymentStatus(history.getPaymentStatus());
        response.setAmount(history.getAmount());
        response.setUser(history.getUser());
        return response;
    }
}
