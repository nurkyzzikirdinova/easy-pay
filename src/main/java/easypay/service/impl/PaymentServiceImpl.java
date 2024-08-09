package easypay.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.dto.request.PaymentRequest;
import zikirdinova.easypay.dto.response.PaymentResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.enums.PaymentStatus;
import zikirdinova.easypay.exception.NotFoundException;
import zikirdinova.easypay.repository.PaymentRepository;
import zikirdinova.easypay.service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setPaymentReference(paymentRequest.getPaymentReference());
        payment.setPaymentDate(paymentRequest.getPaymentDate());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus() != null ?
                paymentRequest.getPaymentStatus() :
                PaymentStatus.PENDING);
        Payment savedPayment = paymentRepository.save(payment);

        PaymentResponse response = new PaymentResponse();
        response.setId(savedPayment.getId());
        response.setPaymentReference(savedPayment.getPaymentReference());
        response.setPaymentDate(savedPayment.getPaymentDate());
        response.setAmount(savedPayment.getAmount());
        response.setPaymentStatus(savedPayment.getPaymentStatus());

        return response;
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) throws NotFoundException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found with id: " + id));

        payment.setPaymentReference(paymentRequest.getPaymentReference());
        payment.setPaymentDate(paymentRequest.getPaymentDate());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus() != null ?
                paymentRequest.getPaymentStatus() :
                payment.getPaymentStatus());


        Payment updatedPayment = paymentRepository.save(payment);

        PaymentResponse response = new PaymentResponse();
        response.setId(updatedPayment.getId());
        response.setPaymentReference(updatedPayment.getPaymentReference());
        response.setPaymentDate(updatedPayment.getPaymentDate());
        response.setAmount(updatedPayment.getAmount());
        response.setPaymentStatus(updatedPayment.getPaymentStatus());

        return response;
    }

    @Override
    public SimpleResponse deletePayment(Long id) throws NotFoundException {
        if (!paymentRepository.existsById(id)) {
            throw new NotFoundException("Payment not found with id: " + id);
        }

        paymentRepository.deleteById(id);

        return new SimpleResponse(HttpStatus.OK, "Payment deleted successfully");
    }


    @Override
    public PaymentResponse getPaymentById(Long id) throws NotFoundException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found with id: " + id));

        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setPaymentReference(payment.getPaymentReference());
        response.setPaymentDate(payment.getPaymentDate());
        response.setAmount(payment.getAmount());
        response.setPaymentStatus(payment.getPaymentStatus());

        return response;
    }


    @Override
    public List<PaymentResponse> getAllPayments() {

        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(payment -> {
            PaymentResponse response = new PaymentResponse();
            response.setId(payment.getId());
            response.setPaymentReference(payment.getPaymentReference());
            response.setPaymentDate(payment.getPaymentDate());
            response.setAmount(payment.getAmount());
            response.setPaymentStatus(payment.getPaymentStatus());
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponse> getPaymentsByStatus(PaymentStatus status) {
        List<Payment> payments = paymentRepository.findAllByPaymentStatus(status);

        return payments.stream().map(payment -> {
            PaymentResponse response = new PaymentResponse();
            response.setId(payment.getId());
            response.setPaymentReference(payment.getPaymentReference());
            response.setPaymentDate(payment.getPaymentDate());
            response.setAmount(payment.getAmount());
            response.setPaymentStatus(payment.getPaymentStatus());
            return response;
        }).collect(Collectors.toList());
    }

}
