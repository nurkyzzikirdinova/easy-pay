package easypay.service;

import zikirdinova.easypay.dto.request.FineRequest;
import zikirdinova.easypay.dto.response.FineResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;

import java.util.List;


public interface FineService {
    SimpleResponse createFine(FineRequest request);
    SimpleResponse updateFine(Long id, FineRequest request);
    SimpleResponse deleteFine(Long id);
    FineResponse getFineById(Long id);
    List<FineResponse> getAllFines();
    List<FineResponse> getFinesByPaidStatus(Boolean paid);
}
