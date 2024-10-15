package com.pathmates.application.entities;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.pathmates.application.utils.Birthday;
import com.pathmates.application.utils.EmailAddress;
import com.pathmates.application.utils.InstantMessageAddress;
import com.pathmates.application.utils.PhoneNumber;
import com.pathmates.application.utils.PostalAddress;
import com.pathmates.application.utils.UrlAddress;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends Auditable {
    @Id
    @UuidGenerator
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

    @Embedded
    private Birthday birthday;

    @ElementCollection
    @CollectionTable(name = "email_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    private List<EmailAddress> emailAddresses;

    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "contact_id"))
    private List<PhoneNumber> phoneNumbers;

    @ElementCollection
    @CollectionTable(name = "postal_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    private List<PostalAddress> postalAddresses;

    @ElementCollection
    @CollectionTable(name = "im_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    private List<InstantMessageAddress> imAddresses;

    @ElementCollection
    @CollectionTable(name = "url_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    private List<UrlAddress> urlAddresses;

    private String note;

    private boolean isRegistered;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

}
