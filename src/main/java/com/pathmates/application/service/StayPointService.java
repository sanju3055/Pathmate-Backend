package com.pathmates.application.service;

import com.pathmates.application.dto.StayPointDTO;
import com.pathmates.application.utils.ApiResponse;

public interface StayPointService {
    ApiResponse<StayPointDTO> createStayPoint(StayPointDTO stayPointDTO);
    ApiResponse<StayPointDTO> getStayPointById(String stayPointId);
    ApiResponse<StayPointDTO> updateStayPoint(String stayPointId, StayPointDTO stayPointDTO);
    void deleteStayPoint(String stayPointId);
}
