package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.pathmates.application.dto.ContactDTO;
import com.pathmates.application.entities.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDTO mapToContactDTO(Contact contact);
    Contact mapToContact(ContactDTO contactDTO);
    List<ContactDTO>  mapToContactDTOs(List<Contact> contacts);
    List<Contact> mapToContacts(List<ContactDTO> contactDTOs);
}
