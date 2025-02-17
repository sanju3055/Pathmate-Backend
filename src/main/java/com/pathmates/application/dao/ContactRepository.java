package com.pathmates.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pathmates.application.entities.Contact;
import com.pathmates.application.utils.PhoneNumber;

public interface ContactRepository extends JpaRepository<Contact, String> {
    @Query("SELECT c FROM Contact c JOIN c.trip t " +
            "WHERE t.tripId = :tripId AND :phoneNumber MEMBER OF c.phoneNumbers")
    Optional<Contact> findByTripIdAndPhoneNumber(@Param("tripId") String tripId,
            @Param("phoneNumber") PhoneNumber phoneNumber);

}
