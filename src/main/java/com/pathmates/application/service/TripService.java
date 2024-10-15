package com.pathmates.application.service;

import com.pathmates.application.dto.TripDTO;
import com.pathmates.application.utils.ApiResponse;

public interface TripService {
    ApiResponse<TripDTO> createTrip(TripDTO tripDTO);
    ApiResponse<TripDTO> getTripById(String tripId);
    ApiResponse<TripDTO> updateTrip(String tripId, TripDTO tripDTO);
    void deleteTrip(String tripId);
    ApiResponse<TripDTO> addUserToTrip(String tripId, String userId);
    ApiResponse<TripDTO> removeUserFromTrip(String tripId, String userId);
    boolean isTripNameTaken(String name);
}
