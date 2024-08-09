package easypay.service;

import zikirdinova.easypay.dto.request.PaymentHistoryRequest;
import zikirdinova.easypay.dto.response.PaymentHistoryResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.enums.PaymentStatus;

import java.util.List;


public interface PaymentHistoryService {
    SimpleResponse createPaymentHistory(PaymentHistoryRequest request);

    SimpleResponse updatePaymentHistory(Long id, PaymentHistoryRequest request);

    SimpleResponse deletePaymentHistory(Long id);

    PaymentHistoryResponse getPaymentHistoryById(Long id);

    List<PaymentHistoryResponse> getAllPaymentHistories();

    List<PaymentHistoryResponse> getPaymentHistoriesByStatus(PaymentStatus status);
}
