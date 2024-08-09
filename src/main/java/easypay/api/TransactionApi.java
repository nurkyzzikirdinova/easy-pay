package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.TransactionRequest;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.dto.response.TransactionResponse;
import zikirdinova.easypay.enums.TransactionStatus;
import zikirdinova.easypay.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@Tag(name = "Transaction-api")
public class TransactionApi {

    private final TransactionService transactionService;

    @PostMapping("/createTransaction")
    public SimpleResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

    @GetMapping("/getTransactionById/{trId}")
    public TransactionResponse getTransactionById(@PathVariable Long trId) {
        return transactionService.getTransactionById(trId);
    }

    @GetMapping("/getAllTransactions")
    public List<TransactionResponse> getAllTransactions() {
        return  transactionService.getAllTransactions();
    }

    @PutMapping("/updateTransaction/{trId}")
    public SimpleResponse updateTransaction(@PathVariable Long trId, @RequestBody TransactionRequest transactionRequest){
        return  transactionService.updateTransaction(trId, transactionRequest);
    }

    @DeleteMapping("/deleteTransaction/{trId}")
    public SimpleResponse deleteTransaction(@PathVariable Long  trId){
        return  transactionService.deleteTransaction( trId);
    }

    @GetMapping("/findAllByTransactionStatus")
    public List<TransactionResponse> findAllByTransactionStatus(@RequestParam TransactionStatus status) {
        return transactionService.findAllByTransactionStatus(status);
    }


}
