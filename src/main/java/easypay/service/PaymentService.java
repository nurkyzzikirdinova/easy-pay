package easypay.service;

import zikirdinova.easypay.dto.request.PaymentRequest;
import zikirdinova.easypay.dto.response.PaymentResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.enums.PaymentStatus;
import zikirdinova.easypay.exception.NotFoundException;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);

    PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) throws NotFoundException;

    SimpleResponse deletePayment(Long id) throws NotFoundException;

    PaymentResponse getPaymentById(Long id) throws NotFoundException;

    List<PaymentResponse> getAllPayments();

    List<PaymentResponse> getPaymentsByStatus(PaymentStatus status);
}
