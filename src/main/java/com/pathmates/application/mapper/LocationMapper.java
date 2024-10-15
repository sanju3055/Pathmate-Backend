package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.pathmates.application.dto.LocationDTO;
import com.pathmates.application.entities.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO mapLocationDTO(Location location);

    Location mapToLocation(LocationDTO locationDTO);

    List<LocationDTO> mapToLocationDTOs(List<Location> locations);

    List<Location> mapToLocations(List<LocationDTO> locationDTOs);

}
