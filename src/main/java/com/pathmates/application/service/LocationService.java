package com.pathmates.application.service;

import com.pathmates.application.dto.LocationDTO;
import com.pathmates.application.utils.ApiResponse;

public interface LocationService {
    ApiResponse<LocationDTO> createLocation(LocationDTO locationDTO);
    ApiResponse<LocationDTO> getLocationById(String locationId);
    ApiResponse<LocationDTO> updateLocation(String locationId, LocationDTO locationDTO);
    void deleteLocation(String locationId);
}
