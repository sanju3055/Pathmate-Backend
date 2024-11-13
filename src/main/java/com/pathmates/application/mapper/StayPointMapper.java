package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pathmates.application.dto.StayPointDTO;
import com.pathmates.application.entities.StayPoint;

@Mapper(componentModel = "spring")
public interface StayPointMapper {
    StayPointDTO mapToStayPointDTO(StayPoint stayPoint);
    StayPoint mapToStayPoint(StayPointDTO stayPointDTO);
    List<StayPointDTO> mapToStayPointDTOs(List<StayPoint> stayPoints);
    List<StayPoint> mapToStayPoints(List<StayPointDTO> stayPointDTOs);
}
