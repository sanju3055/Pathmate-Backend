package com.pathmates.application.service;

import com.pathmates.application.dto.DestinationDTO;
import com.pathmates.application.utils.ApiResponse;

public interface DestinationService {
    ApiResponse<DestinationDTO> createDestination(DestinationDTO entity);
    ApiResponse<DestinationDTO> getDestinationById(String destinationId);
    ApiResponse<DestinationDTO> updateDestination(String destinationId, DestinationDTO entity);
    void deleteDestination(String destinationId);
}
