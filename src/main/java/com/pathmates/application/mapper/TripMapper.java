package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.pathmates.application.dto.TripDTO;
import com.pathmates.application.entities.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripDTO maTripDTO(Trip trip);
    Trip mapToTrip(TripDTO tripDTO);
    List<Trip> mapToTripList(List<TripDTO> tripDTOs);
    List<TripDTO> mapToTripDTOList(List<Trip> trips);
}
