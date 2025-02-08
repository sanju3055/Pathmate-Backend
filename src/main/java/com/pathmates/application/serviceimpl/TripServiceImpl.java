package com.pathmates.application.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.ContactRepository;
import com.pathmates.application.dao.DestinationRepository;
import com.pathmates.application.dao.TripRepository;
import com.pathmates.application.dao.UserRepository;
import com.pathmates.application.dto.TripDTO;
import com.pathmates.application.entities.Contact;
import com.pathmates.application.entities.Destination;
import com.pathmates.application.entities.Trip;
import com.pathmates.application.entities.User;
import com.pathmates.application.mapper.TripMapper;
import com.pathmates.application.service.TripService;
import com.pathmates.application.utils.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripMapper mapper;
    @Autowired
    private TripRepository repository;

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse<TripDTO> createTrip(TripDTO tripDTO) {
        if (tripDTO.getContacts().isEmpty()) {
            return new ApiResponse<>(false, "Trip must have at least one contact", null, null);
        }

        if (tripDTO.getDestinations().isEmpty()) {
            return new ApiResponse<>(false, "Trip must have at least one destination", null, null);
        }

        if (tripDTO.getStartDate().isAfter(tripDTO.getEndDate())) {
            return new ApiResponse<>(false, "Start date cannot be after end date", null, null);
        }
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            return new ApiResponse<>(false, "User not found", null, null);
        }
        tripDTO.setUser(user);
        Trip newTrip = new Trip();
        newTrip.setTripName(tripDTO.getTripName());
        newTrip.setTripDescription(tripDTO.getTripDescription());
        newTrip.setStartDate(tripDTO.getStartDate());
        newTrip.setStartTime(tripDTO.getStartTime());
        newTrip.setEndDate(tripDTO.getEndDate());
        newTrip.setEndTime(tripDTO.getEndTime());
        newTrip.setUser(tripDTO.getUser());

        Trip trip = repository.save(newTrip);

        List<Destination> savedDestinations = saveDestinations(tripDTO.getDestinations(), trip);
        List<Contact> savedContacts = saveContacts(tripDTO.getContacts(), trip);

        trip.setDestinations(savedDestinations);
        trip.setContacts(savedContacts);

        Trip savedTrip = repository.save(trip);

        return new ApiResponse<>(true, "Trip created successfully", mapper.maTripDTO(savedTrip), null);
    }

    private List<Destination> saveDestinations(List<Destination> destinations, Trip trip) {
        destinations.forEach(destination -> destination.setTrip(trip));
        return new ArrayList<>(destinationRepository.saveAll(destinations));
    }

    @Transactional
    public List<Contact> saveContacts(List<Contact> contacts, Trip trip) {
        if (contacts == null || contacts.isEmpty()) {
            return Collections.emptyList();
        }

        if (trip == null) {
            throw new IllegalArgumentException("Trip cannot be null");
        }

        contacts.forEach(contact -> {
            contact.setTrip(trip);
            contact.setPhoneNumbers(
                    new ArrayList<>(Optional.ofNullable(contact.getPhoneNumbers()).orElse(Collections.emptyList())));
            contact.setAddresses(
                    new ArrayList<>(Optional.ofNullable(contact.getAddresses()).orElse(Collections.emptyList())));
        });

        return contactRepository.saveAll(contacts);
    }

    @Override
    public ApiResponse<TripDTO> getTripById(String tripId) {
        return new ApiResponse<>(true, "", mapper.maTripDTO(repository.findById(tripId).get()), null);
    }

    @Override
    public ApiResponse<TripDTO> updateTrip(String tripId, TripDTO tripDTO) {
        Optional<Trip> Trip = repository.findById(tripId);
        if (Trip.isPresent()) {
            Trip.get().setTripName(tripDTO.getTripName());
            repository.save(Trip.get());
            return new ApiResponse<>(true, "", mapper.maTripDTO(Trip.get()), null);
        }
        return new ApiResponse<>(true, "", "Trip is not found", null);
    }

    @Override
    public ApiResponse<String> deleteTrip(String tripId) {
        Optional<Trip> tripOptional = repository.findById(tripId);
        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            trip.getDestinations().clear();

            repository.delete(trip);

            return new ApiResponse<>(true, "Trip deleted successfully", "Trip deleted successfully", null);
        }
        return new ApiResponse<>(false, "Trip is not found", "Trip not found", null);
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
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(name);
        if (user == null) {
            return new ApiResponse<>(false, "User not found", null, null);
        }
        List<Trip> trips = repository.findByUser(user);
        return new ApiResponse<>(true, "", mapper.mapToTripDTOList(trips), null);
    }

}
