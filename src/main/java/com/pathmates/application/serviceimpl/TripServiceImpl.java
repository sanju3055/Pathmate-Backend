package com.pathmates.application.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.TripRepository;
import com.pathmates.application.dto.TripDTO;
import com.pathmates.application.entities.Trip;
import com.pathmates.application.mapper.TripMapper;
import com.pathmates.application.service.TripService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripMapper mapper;
    @Autowired
    private TripRepository repository;

    @Override
    public ApiResponse<TripDTO> createTrip(TripDTO tripDTO) {
        return new ApiResponse<>(true, "", mapper.maTripDTO(repository.save(mapper.mapToTrip(tripDTO))), null);
    }

    @Override
    public ApiResponse<TripDTO> getTripById(String tripId) {
        return new ApiResponse<>(true, "", mapper.maTripDTO(repository.findById(tripId).get()), null);
    }

    @Override
    public ApiResponse<TripDTO> updateTrip(String tripId, TripDTO tripDTO) {
        Optional<Trip> Trip = repository.findById(tripId);
        if (Trip.isPresent()) {
            Trip.get().setName(tripDTO.getName());
            repository.save(Trip.get());
            return new ApiResponse<>(true, "", mapper.maTripDTO(Trip.get()), null);
        }
        return new ApiResponse<>(true, "", "Trip is not found", null);
    }

    @Override
    public ApiResponse<String> deleteTrip(String tripId) {
        Optional<Trip> trip = repository.findById(tripId);
        if (trip.isPresent()) {
            repository.delete(trip.get());
            return new ApiResponse<>(true, "", "Trip deleted successfully", null);
        }
        return new ApiResponse<>(true, "", "Trip is not found", null);
    }

    @Override
    public ApiResponse<TripDTO> addUserToTrip(String TripId, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUserToTrip'");
    }

    @Override
    public ApiResponse<TripDTO> removeUserFromTrip(String TripId, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeUserFromTrip'");
    }

    @Override
    public boolean isTripNameTaken(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTripNameTaken'");
    }

    @Override
    public ApiResponse<List<TripDTO>> getTrips() {
        List<Trip> trips = repository.findAll();
        return new ApiResponse<>(true, "", mapper.mapToTripDTOList(trips), null);
    }

}
