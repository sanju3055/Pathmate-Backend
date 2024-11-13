package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.pathmates.application.dto.DestinationDTO;
import com.pathmates.application.entities.Destination;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    Destination mapToDestination(DestinationDTO destinationDTO);
    DestinationDTO mapToDestinationDTO(Destination destination);
    List<Destination> mapToDestinations(List<DestinationDTO> destinationDTOs);
    List<DestinationDTO> mapToDestinationDTOs(List<Destination> destinations);
}
