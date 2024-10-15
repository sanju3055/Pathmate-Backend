package com.pathmates.application.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.DestinationRepository;
import com.pathmates.application.dto.DestinationDTO;
import com.pathmates.application.entities.Destination;
import com.pathmates.application.mapper.DestinationMapper;
import com.pathmates.application.service.DestinationService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository repository;

    @Autowired
    private DestinationMapper mapper;


    @Override
    public ApiResponse<DestinationDTO> createDestination(DestinationDTO entity) {
        return new ApiResponse<>(true, "", mapper.mapToDestinationDTO(repository.save(mapper.mapToDestination(entity))), null);
    }

    @Override
    public ApiResponse<DestinationDTO> getDestinationById(String destinationId) {
        return new ApiResponse<>(true, destinationId, mapper.mapToDestinationDTO(repository.findById(destinationId).get()), null);
    }

    @Override
    public ApiResponse<DestinationDTO> updateDestination(String destinationId, DestinationDTO entity) {
       Optional<Destination> destination = repository.findById(destinationId);
       if (destination.isPresent()) {
           destination.get().setName(entity.getName());
           return new ApiResponse<>(true, destinationId, mapper.mapToDestinationDTO(repository.save(destination.get())), null);
       }
       return new ApiResponse<>(true, destinationId, "Destination not found", null);
    }

    @Override
    public void deleteDestination(String destinationId) {
        Optional<Destination> destination = repository.findById(destinationId);
        if (destination.isPresent()) {
            repository.delete(destination.get());
        } else {
            throw new IllegalArgumentException("Destination not found");
        }
    }

}
