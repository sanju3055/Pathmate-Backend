package com.pathmates.application.dto;

import com.pathmates.application.entities.Contact.Birthday;
import com.pathmates.application.entities.Contact.EmailAddress;
import com.pathmates.application.entities.Contact.PhoneNumber;
import com.pathmates.application.entities.Contact.PostalAddress;
import com.pathmates.application.entities.Contact.UrlAddress;
import com.pathmates.application.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDTO {
    private String contactId;

    private String id;
    private String displayName;
    private String givenName;
    private String familyName;
    private String middleName;
    private String jobTitle;
    private String company;
    private String department;
    private String note;
    private boolean hasThumbnail;
    private String thumbnailPath;

    private Birthday birthday;

    private List<EmailAddress> emails;

    private List<PhoneNumber> phoneNumbers;

    private List<PostalAddress> addresses;

    private List<UrlAddress> urls;

    private Trip trip;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
