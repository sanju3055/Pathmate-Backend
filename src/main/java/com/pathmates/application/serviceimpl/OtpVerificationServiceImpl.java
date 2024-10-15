package com.pathmates.application.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dto.OtpVerificationDTO;
import com.pathmates.application.entities.OtpVerification;
import com.pathmates.application.mapper.OtpVerificationMapper;
import com.pathmates.application.service.OtpVerificationService;
import com.pathmates.application.utils.ApiResponse;
import com.pathmates.application.dao.OtpVerificationRepository;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {

    @Autowired
    private OtpVerificationRepository repository;

    @Autowired
    private OtpVerificationMapper mapper;

    @Override
    public ApiResponse<OtpVerificationDTO> createOtpVerificationDTO(OtpVerificationDTO otpVerificationDTO) {
        return new ApiResponse<>(true, null,
                mapper.mapToOtpVerificationDTO(repository.save(mapper.mapToOtpVerification(otpVerificationDTO))), null);
    }

    @Override
    public ApiResponse<OtpVerificationDTO> getOtpVerificationDTOById(String otpVerificationId) {
        return new ApiResponse<>(true, null,
                mapper.mapToOtpVerificationDTO(repository.findById(otpVerificationId).get()), null);
    }

    @Override
    public ApiResponse<OtpVerificationDTO> updateOtpVerificationDTO(String otpVerificationId,
            OtpVerificationDTO otpVerificationDTO) {

        Optional<OtpVerification> otpVerification = repository.findById(otpVerificationId);
        if (otpVerification.isPresent()) {
            otpVerification.get().setOtp(otpVerificationDTO.getOtp());
            ;
            repository.save(otpVerification.get());
            return new ApiResponse<>(true, null,
                    mapper.mapToOtpVerificationDTO(otpVerification.get()), null);
        }
        return new ApiResponse<>(true, null, "OtpVerification not found", null);
    }

    @Override
    public void deleteOtpVerificationDTO(String otpVerificationId) {
        Optional<OtpVerification> otpVerification = repository.findById(otpVerificationId);
        if (otpVerification.isPresent()) {
            repository.delete(otpVerification.get());
            return;
        }
        throw new IllegalArgumentException("OtpVerification not found");
    }

}
