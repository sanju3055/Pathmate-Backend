package com.pathmates.application.service;

import com.pathmates.application.dto.OtpVerificationDTO;
import com.pathmates.application.utils.ApiResponse;

public interface OtpVerificationService {
ApiResponse<OtpVerificationDTO> createOtpVerificationDTO(OtpVerificationDTO otpVerificationDTO);
    ApiResponse<OtpVerificationDTO> getOtpVerificationDTOById(String otpVerificationId);
    ApiResponse<OtpVerificationDTO> updateOtpVerificationDTO(String otpVerificationId, OtpVerificationDTO otpVerificationDTO);
    ApiResponse<String> deleteOtpVerificationDTO(String otpVerificationId);
}
