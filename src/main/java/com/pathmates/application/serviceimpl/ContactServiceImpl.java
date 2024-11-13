package com.pathmates.application.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.ContactRepository;
import com.pathmates.application.dto.ContactDTO;
import com.pathmates.application.entities.Contact;
import com.pathmates.application.mapper.ContactMapper;
import com.pathmates.application.service.ContactService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private ContactMapper mapper;

    @Override
    public ApiResponse<ContactDTO> createContact(ContactDTO entity) {
        return new ApiResponse<>(true, "", mapper.mapToContactDTO(repository.save(mapper.mapToContact(entity))), "");
    }

    @Override
    public ApiResponse<ContactDTO> getContactById(String contactId) {
        return new ApiResponse<>(true, "", mapper.mapToContactDTO(repository.findById(contactId).get()), null);
    }

    @Override
    public ApiResponse<ContactDTO> updateContact(String contactId, ContactDTO entity) {
        Optional<Contact> contact = repository.findById(contactId);
        if (contact.isPresent()) {
            return new ApiResponse<>(true, "", mapper.mapToContactDTO(repository.save(contact.get())), null);
        }
        return new ApiResponse<>(true, "", "Contact not found", null);
    }

    @Override
    public ApiResponse<String> deleteContact(String contactId) {
        Optional<Contact> contact = repository.findById(contactId);
        if (contact.isPresent()) {
            repository.delete(contact.get());
            return new ApiResponse<>(true, "Contact deleted successfully", "Contact deleted successfully", null);
        } else {
            throw new IllegalArgumentException("Contact not found");
        }
    }

    @Override
    public ApiResponse<List<ContactDTO>> createAllContact(List<ContactDTO> entities) {
        List<Contact> contacts = entities.stream()
                .map(mapper::mapToContact)
                .collect(Collectors.toList());

        List<Contact> savedContacts = repository.saveAll(contacts);

        List<ContactDTO> savedContactDTOs = savedContacts.stream()
                .map(mapper::mapToContactDTO)
                .collect(Collectors.toList());

        return new ApiResponse<>(true, "Members added Successfully", savedContactDTOs, null);

    }

}
