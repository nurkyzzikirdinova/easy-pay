package easypay.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.dto.request.FineRequest;
import zikirdinova.easypay.dto.response.FineResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.Fine;
import zikirdinova.easypay.repository.FineRepository;
import zikirdinova.easypay.service.FineService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;

    @Override
    public SimpleResponse createFine(FineRequest request) {
        Fine fine = new Fine();
        fine.setAmount(request.getAmount());
        fine.setIssuedDate(request.getIssuedDate());
        fine.setDueDate(request.getDueDate());
        fine.setUser(request.getUser());
        fine.setPaid(request.getPaid());

        fineRepository.save(fine);
        return new SimpleResponse(HttpStatus.OK, "Fine created successfully");
    }

    @Override
    public SimpleResponse updateFine(Long id, FineRequest request) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fine with id " + id + " not found"));
        fine.setAmount(request.getAmount());
        fine.setIssuedDate(request.getIssuedDate());
        fine.setDueDate(request.getDueDate());
        fine.setUser(request.getUser());
        fine.setPaid(request.getPaid());

        fineRepository.save(fine);
        return new SimpleResponse(HttpStatus.OK, "Fine updated successfully");
    }

    @Override
    public SimpleResponse deleteFine(Long id) {
        if (!fineRepository.existsById(id)) {
            throw new RuntimeException("Fine with id " + id + " not found");
        }
        fineRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "Fine deleted successfully");
    }

    @Override
    public FineResponse getFineById(Long id) {
        Fine fine = fineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fine with id " + id + " not found"));
        return mapToResponse(fine);
    }

    @Override
    public List<FineResponse> getAllFines() {
        return fineRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FineResponse> getFinesByPaidStatus(Boolean paid) {
        return fineRepository.findAllByPaid(paid).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FineResponse mapToResponse(Fine fine) {
        FineResponse response = new FineResponse();
        response.setId(fine.getId());
        response.setAmount(fine.getAmount());
        response.setIssuedDate(fine.getIssuedDate());
        response.setDueDate(fine.getDueDate());
        response.setUser(fine.getUser());
        response.setPaid(fine.getPaid());
        return response;
    }
}
