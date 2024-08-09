package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.PaymentRequest;
import zikirdinova.easypay.dto.response.PaymentResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.enums.PaymentStatus;
import zikirdinova.easypay.exception.NotFoundException;
import zikirdinova.easypay.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Auth-api")
public class PaymentApi {
    private final PaymentService paymentService;

    @PostMapping("/createPayment")
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping("/updatePayment/{id}")
    public PaymentResponse updatePayment(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest) throws NotFoundException {
        return paymentService.updatePayment(id, paymentRequest);
    }

    @DeleteMapping("/deletePayment/{id}")
    public SimpleResponse deletePayment(@PathVariable Long id) throws NotFoundException {
        return paymentService.deletePayment(id);
    }

    @GetMapping("/getPaymentById/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) throws NotFoundException {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/getAllPayments")
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/getPaymentsByStatus")
    public List<PaymentResponse> getPaymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentService.getPaymentsByStatus(status);
    }
}
