package easypay.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zikirdinova.easypay.dto.request.QRCodeRequest;
import zikirdinova.easypay.dto.response.QRCodeResponse;
import zikirdinova.easypay.dto.response.SimpleResponse;
import zikirdinova.easypay.entities.QRCode;
import zikirdinova.easypay.exception.NotFoundException;
import zikirdinova.easypay.repository.QRCodeRepository;
import zikirdinova.easypay.service.QRCodeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final QRCodeRepository qrCodeRepository;

    @Override
    public SimpleResponse createQRCode(QRCodeRequest request) {
        QRCode qrCode = new QRCode();
        qrCode.setCode(request.getCode());
        qrCode.setGeneratedDate(request.getGeneratedDate());
        qrCode.setUser(request.getUser());
        qrCode.setPayment(request.getPayment());

        qrCodeRepository.save(qrCode);
        return new SimpleResponse(HttpStatus.OK, "QR code created successfully");
    }

    @Override
    public SimpleResponse updateQRCode(Long id, QRCodeRequest request) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR code with id " + id + " not found"));
        qrCode.setCode(request.getCode());
        qrCode.setGeneratedDate(request.getGeneratedDate());
        qrCode.setUser(request.getUser());
        qrCode.setPayment(request.getPayment());

        qrCodeRepository.save(qrCode);
        return new SimpleResponse(HttpStatus.OK, "QR code updated successfully");
    }

    @Override
    public SimpleResponse deleteQRCode(Long id) {
        if (!qrCodeRepository.existsById(id)) {
            throw new RuntimeException("QR code with id " + id + " not found");
        }
        qrCodeRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "QR code deleted successfully");
    }

    @Override
    public QRCodeResponse getQRCodeById(Long id) throws NotFoundException {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("QR Code not found with id: " + id));

        return new QRCodeResponse(
                qrCode.getId(),
                qrCode.getCode(),
                qrCode.getGeneratedDate(),
                qrCode.getUser(),
                qrCode.getPayment()
        );
    }

    @Override
    public List<QRCodeResponse> getAllQRCodes() {
        List<QRCode> qrCodes = qrCodeRepository.findAll();
        return qrCodes.stream()
                .map(qrCode -> new QRCodeResponse(
                        qrCode.getId(),
                        qrCode.getCode(),
                        qrCode.getGeneratedDate(),
                        qrCode.getUser(),
                        qrCode.getPayment()
                ))
                .collect(Collectors.toList());
    }
}
