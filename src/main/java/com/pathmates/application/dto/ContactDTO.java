package com.pathmates.application.dto;

import com.pathmates.application.entities.Trip;
import com.pathmates.application.utils.Birthday;
import com.pathmates.application.utils.EmailAddress;
import com.pathmates.application.utils.InstantMessageAddress;
import com.pathmates.application.utils.PhoneNumber;
import com.pathmates.application.utils.PostalAddress;
import com.pathmates.application.utils.UrlAddress;
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

    private String recordID;

    private String backTitle;

    private String company;

    private String displayName;

    private String familyName;

    private String givenName;

    private String middleName;

    private String jobTitle;

    private boolean hasThumbnail;

    private String thumbnailPath;

    private boolean isStarred;

    private String prefix;

    private String suffix;

    private String department;
    private Birthday birthday;

    private List<EmailAddress> emailAddresses;

    private List<PhoneNumber> phoneNumbers;

    private List<PostalAddress> postalAddresses;

    private List<InstantMessageAddress> imAddresses;

    private List<UrlAddress> urlAddresses;

    private String note;
    private boolean isRegistered;
    private Trip trip;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
