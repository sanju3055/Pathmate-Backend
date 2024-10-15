package com.pathmates.application.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.LocationRepository;
import com.pathmates.application.dto.LocationDTO;
import com.pathmates.application.entities.Location;
import com.pathmates.application.mapper.LocationMapper;
import com.pathmates.application.service.LocationService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private LocationMapper mapper;

    @Override
    public ApiResponse<LocationDTO> createLocation(LocationDTO locationDTO) {
        return new ApiResponse<>(true, "", mapper.mapLocationDTO(repository.save(mapper.mapToLocation(locationDTO))),
                null);
    }

    @Override
    public ApiResponse<LocationDTO> getLocationById(String locationId) {
        return new ApiResponse<>(true, "", mapper.mapLocationDTO(repository.findById(locationId).get()), null);
    }

    @Override
    public ApiResponse<LocationDTO> updateLocation(String locationId, LocationDTO locationDTO) {
        Optional<Location> location = repository.findById(locationId);
        if (location.isPresent()) {
            location.get().setName(locationDTO.getName());
            return new ApiResponse<>(true, "", mapper.mapLocationDTO(repository.save(location.get())), null);
        }
        return new ApiResponse<>(true, "", "Location not found", null);
    }

    @Override
    public void deleteLocation(String locationId) {
        Optional<Location> location = repository.findById(locationId);
        if (location.isPresent()) {
            repository.delete(location.get());
        } else {
            throw new IllegalArgumentException("Location not found");
        }
    }

}
