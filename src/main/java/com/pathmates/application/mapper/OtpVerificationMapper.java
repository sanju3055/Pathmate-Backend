package com.pathmates.application.mapper;

import org.mapstruct.Mapper;

import com.pathmates.application.dto.OtpVerificationDTO;
import com.pathmates.application.entities.OtpVerification;
import java.util.*;

@Mapper(componentModel = "spring")
public interface OtpVerificationMapper {
    OtpVerification mapToOtpVerification(OtpVerificationDTO otpVerificationDTO);
    OtpVerificationDTO mapToOtpVerificationDTO(OtpVerification otpVerification);
    List<OtpVerification> mapToOtpVerificationList(List<OtpVerificationDTO> otpVerificationDTOs);
    List<OtpVerificationDTO> mapToOtpVerificationDTOList(List<OtpVerification> otpVerifications);
}
