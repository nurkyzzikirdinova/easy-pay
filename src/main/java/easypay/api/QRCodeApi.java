package easypay.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zikirdinova.easypay.dto.request.QRCodeRequest;
import zikirdinova.easypay.dto.response.QRCodeResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.exception.NotFoundException;
import zikirdinova.easypay.service.QRCodeService;

import java.util.List;

@RestController
@RequestMapping("/api/qrcode")
@RequiredArgsConstructor
@Tag(name = "QRCode-Api")
public class QRCodeApi {
    private final QRCodeService qrCodeService;

    @PostMapping("/createQRCode")
    public SimpleResponse createQRCode(@RequestBody QRCodeRequest request) {
        return qrCodeService.createQRCode(request);
    }

    @PutMapping("/updateQRCode/{id}")
    public SimpleResponse updateQRCode(@PathVariable Long id, @RequestBody QRCodeRequest request) {
        return qrCodeService.updateQRCode(id, request);
    }

    @GetMapping("/getAllQRCodes")
    public List<QRCodeResponse> getAllQRCodes() {
        return qrCodeService.getAllQRCodes();
    }

    @GetMapping("/getQRCodeById/{id}")
    public QRCodeResponse getQRCodeById(@PathVariable Long id) throws NotFoundException {
        return qrCodeService.getQRCodeById(id);

    }

    @DeleteMapping("/deleteQRCode/{id}")
    public SimpleResponse deleteQRCode(@PathVariable Long id) {
        return qrCodeService.deleteQRCode(id);
    }

}
