package com.pathmates.application.service;

import java.util.List;

import com.pathmates.application.dto.ContactDTO;
import com.pathmates.application.utils.ApiResponse;

public interface ContactService {
    ApiResponse<ContactDTO> createContact(ContactDTO entity);

    ApiResponse<List<ContactDTO>> createAllContact(List<ContactDTO> entities);

    ApiResponse<ContactDTO> getContactById(String contactId);

    ApiResponse<ContactDTO> updateContact(String contactId, ContactDTO entity);

    void deleteContact(String contactId);
}
