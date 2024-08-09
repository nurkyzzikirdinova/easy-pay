package easypay.service;

import zikirdinova.easypay.dto.request.QRCodeRequest;
import zikirdinova.easypay.dto.response.QRCodeResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.exception.NotFoundException;

import java.util.List;

public interface QRCodeService {
    SimpleResponse createQRCode(QRCodeRequest request);
    SimpleResponse updateQRCode(Long id, QRCodeRequest request);
    SimpleResponse deleteQRCode(Long id);
    QRCodeResponse getQRCodeById(Long id) throws NotFoundException;
    List<QRCodeResponse> getAllQRCodes();
}
