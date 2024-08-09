package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.FineRequest;
import zikirdinova.easypay.dto.response.FineResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.service.FineService;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
@RequiredArgsConstructor
@Tag(name = "Fine-Api")
public class Fine {
    private final FineService fineService;

    @PostMapping("/createFine")
    public SimpleResponse createFine(@RequestBody FineRequest request) {
        return fineService.createFine(request);
    }

    @PutMapping("/updateFine/{id}")
    public SimpleResponse updateFine(@PathVariable Long id, @RequestBody FineRequest request) {
        return fineService.updateFine(id, request);
    }

    @GetMapping("/getFineById/{id}")
    public FineResponse getFineById(@PathVariable Long id) {
        return fineService.getFineById(id);
    }

    @GetMapping("/getAllFines")
    public List<FineResponse> getAllFines() {
        return fineService.getAllFines();
    }

    @GetMapping("/getFinesByPaidStatus")
    public List<FineResponse> getFinesByPaidStatus(@RequestParam Boolean paid) {
        return fineService.getFinesByPaidStatus(paid);
    }


}