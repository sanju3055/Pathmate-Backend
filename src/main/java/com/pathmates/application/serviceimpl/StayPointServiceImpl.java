package com.pathmates.application.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.StayPointRepository;
import com.pathmates.application.dto.StayPointDTO;
import com.pathmates.application.entities.StayPoint;
import com.pathmates.application.mapper.StayPointMapper;
import com.pathmates.application.service.StayPointService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class StayPointServiceImpl implements StayPointService {

    @Autowired
    private StayPointRepository repository;
    @Autowired
    private StayPointMapper mapper;

    @Override
    public ApiResponse<StayPointDTO> createStayPoint(StayPointDTO stayPointDTO) {
        return new ApiResponse<>(true, null,
                mapper.mapToStayPointDTO(repository.save(mapper.mapToStayPoint(stayPointDTO))), null);
    }

    @Override
    public ApiResponse<StayPointDTO> getStayPointById(String stayPointId) {
        return new ApiResponse<>(true, null, mapper.mapToStayPointDTO(repository.findById(stayPointId).get()), null);
    }

    @Override
    public ApiResponse<StayPointDTO> updateStayPoint(String stayPointId, StayPointDTO stayPointDTO) {
        Optional<StayPoint> stayPoint = repository.findById(stayPointId);
        if (stayPoint.isPresent()) {
            stayPoint.get().setName(stayPointDTO.getName());
            repository.save(stayPoint.get());
            return new ApiResponse<>(true, null, mapper.mapToStayPointDTO(stayPoint.get()), null);
        }
        return new ApiResponse<>(true, null, "Stay Point not found", null);
    }

    @Override
    public void deleteStayPoint(String stayPointId) {
        Optional<StayPoint> stayPoint = repository.findById(stayPointId);
        if (stayPoint.isPresent()) {
            repository.delete(stayPoint.get());
        } else {
            throw new IllegalArgumentException("Stay Point not found");
        }
    }

}
