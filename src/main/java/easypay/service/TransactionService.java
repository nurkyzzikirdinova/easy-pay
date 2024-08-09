package easypay.service;

import zikirdinova.easypay.dto.request.TransactionRequest;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.dto.response.TransactionResponse;
import zikirdinova.easypay.enums.TransactionStatus;

import java.util.List;

public interface TransactionService {
    SimpleResponse createTransaction(TransactionRequest transaction);

    TransactionResponse getTransactionById(Long trId);

    List<TransactionResponse> getAllTransactions();

    SimpleResponse updateTransaction(Long id, TransactionRequest transactionDetails);

   SimpleResponse deleteTransaction(Long id);

    List<TransactionResponse>  findAllByTransactionStatus(TransactionStatus status);
}
