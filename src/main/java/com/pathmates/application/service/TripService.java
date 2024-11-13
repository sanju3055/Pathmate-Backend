package com.pathmates.application.service;

import java.util.List;

import com.pathmates.application.dto.TripDTO;
import com.pathmates.application.utils.ApiResponse;

public interface TripService {
    ApiResponse<TripDTO> createTrip(TripDTO tripDTO);
    ApiResponse<TripDTO> getTripById(String tripId);
    ApiResponse<TripDTO> updateTrip(String tripId, TripDTO tripDTO);
    ApiResponse<String> deleteTrip(String tripId);
    ApiResponse<TripDTO> addUserToTrip(String tripId, String userId);
    ApiResponse<TripDTO> removeUserFromTrip(String tripId, String userId);
    boolean isTripNameTaken(String name);
    ApiResponse<List<TripDTO>> getTrips();
}
