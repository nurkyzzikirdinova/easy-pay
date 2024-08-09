package easypay.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.dto.request.TransactionRequest;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.dto.response.TransactionResponse;
import zikirdinova.easypay.entities.Payment;
import zikirdinova.easypay.entities.Transaction;
import zikirdinova.easypay.enums.TransactionStatus;
import zikirdinova.easypay.repository.PaymentRepository;
import zikirdinova.easypay.repository.TransactionRepository;
import zikirdinova.easypay.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public SimpleResponse createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionReference(transactionRequest.getTransactionReference());
        transaction.setTransactionDate(transactionRequest.getTransactionDate() != null ? transactionRequest.getTransactionDate() : LocalDateTime.now());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        transactionRepository.save(transaction);
        return new SimpleResponse(HttpStatus.OK, "Transaction created successfully");
    }


    @Override
    public TransactionResponse getTransactionById(Long trId) {
        Transaction transaction = transactionRepository.findById(trId)
                .orElseThrow(() -> new RuntimeException("Transaction with id "+trId+" not found"));

        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setTransactionReference(transaction.getTransactionReference());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setAmount(transaction.getAmount());
        response.setTransactionStatus(transaction.getTransactionStatus());
        return response;
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transaction -> {
                    TransactionResponse response = new TransactionResponse();
                    response.setId(transaction.getId());
                    response.setTransactionReference(transaction.getTransactionReference());
                    response.setTransactionDate(transaction.getTransactionDate());
                    response.setAmount(transaction.getAmount());
                    response.setTransactionStatus(transaction.getTransactionStatus());
                    return response;
                }).collect(Collectors.toList());
    }

    @Override
    public SimpleResponse updateTransaction(Long id, TransactionRequest transactionRequest) {
        try {
            Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

            if (optionalTransaction.isPresent()) {
                Transaction transaction = optionalTransaction.get();
                transaction.setTransactionReference(transactionRequest.getTransactionReference());
                transaction.setTransactionDate(transactionRequest.getTransactionDate());
                transaction.setAmount(transactionRequest.getAmount());

                // Проверяем существование Payment по ID
                Optional<Payment> optionalPayment = paymentRepository.findById(transactionRequest.getPaymentId());
                if (optionalPayment.isPresent()) {
                    transaction.setPayment(optionalPayment.get());
                } else {
                    return new SimpleResponse(HttpStatus.NOT_FOUND, "Payment with id "+paymentRepository.findById(id)+" not found");
                }

                transaction.setTransactionStatus(transactionRequest.getTransactionStatus());
                transactionRepository.save(transaction);

                return new SimpleResponse(HttpStatus.OK, "Transaction updated successfully");
            } else {
                return new SimpleResponse(HttpStatus.NOT_FOUND, "Transaction not found");
            }
        } catch (Exception e) {
            return new SimpleResponse(HttpStatus.CONFLICT, "Error updating transaction: " + e.getMessage());
        }
    }


    @Override
    public SimpleResponse deleteTransaction(Long id) {
        try {
            if (transactionRepository.existsById(id)) {
                transactionRepository.deleteById(id);
                return new SimpleResponse(HttpStatus.OK, "Transaction deleted successfully");
            } else {
                return new SimpleResponse(HttpStatus.NOT_FOUND, "Transaction not found");
            }
        } catch (Exception e) {
            return new SimpleResponse(HttpStatus.CONFLICT, "Error deleting transaction: " + e.getMessage());
        }
    }

    @Override
    public List<TransactionResponse>  findAllByTransactionStatus(TransactionStatus status) {
        return transactionRepository.findAllByTransactionStatus(status).stream()
                .map(transaction -> {
                    TransactionResponse response = new TransactionResponse();
                    response.setId(transaction.getId());
                    response.setTransactionReference(transaction.getTransactionReference());
                    response.setTransactionDate(transaction.getTransactionDate());
                    response.setAmount(transaction.getAmount());
                    response.setTransactionStatus(transaction.getTransactionStatus());
                    return response;
                }).collect(Collectors.toList());

    }

}
