package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.PaymentHistoryRequest;
import zikirdinova.easypay.dto.response.PaymentHistoryResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.enums.PaymentStatus;
import zikirdinova.easypay.service.PaymentHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/paymentHistory")
@RequiredArgsConstructor
@Tag(name = "PaymentHistory-Api")
public class PaymentHistoryApi {
    private final PaymentHistoryService paymentHistoryService;

    @PostMapping("/createPaymentHistory")
    public SimpleResponse createPaymentHistory(@RequestBody PaymentHistoryRequest request) {
        return paymentHistoryService.createPaymentHistory(request);
    }

    @PutMapping("/updatePaymentHistory/{id}")
    public SimpleResponse updatePaymentHistory(@PathVariable Long id, @RequestBody PaymentHistoryRequest request) {
        return paymentHistoryService.updatePaymentHistory(id, request);
    }

    @GetMapping("/getPaymentHistoryById/{id}")
    public PaymentHistoryResponse getPaymentHistoryById(@PathVariable Long id) {
        return paymentHistoryService.getPaymentHistoryById(id);
    }


    @GetMapping("/getAllPaymentHistories")
    public List<PaymentHistoryResponse> getAllPaymentHistories() {
        return paymentHistoryService.getAllPaymentHistories();
    }

    @GetMapping("/getPaymentHistoriesByStatus")
    public List<PaymentHistoryResponse> getPaymentHistoriesByStatus(@RequestParam PaymentStatus status) {
        return paymentHistoryService.getPaymentHistoriesByStatus(status);
    }


    @DeleteMapping("/deletePaymentHistory/{id}")
    public SimpleResponse deletePaymentHistory(@PathVariable Long id) {
        return paymentHistoryService.deletePaymentHistory(id);
    }


}
