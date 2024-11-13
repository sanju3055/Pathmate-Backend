package com.pathmates.application.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contact {

    @Id
    @UuidGenerator
    @EqualsAndHashCode.Include
    private String contactId;

    private String recordID;
    private String backTitle;
    private String company;
    private String displayName;
    private String givenName;
    private String familyName;
    private String middleName;
    private String jobTitle;
    private String department;
    private String prefix;
    private String suffix;
    private String note;
    private boolean hasThumbnail;
    private String thumbnailPath;
    private boolean isStarred;
    private boolean isRegistered = false;

    @Embedded
    private Birthday birthday;

    @ElementCollection
    @CollectionTable(name = "email_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "label", column = @Column(name = "email_label")),
            @AttributeOverride(name = "email", column = @Column(name = "email_address"))
    })
    private List<EmailAddress> emailAddresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "label", column = @Column(name = "phone_label")),
            @AttributeOverride(name = "number", column = @Column(name = "phone_number"))
    })
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "postal_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "label", column = @Column(name = "postal_label")),
            @AttributeOverride(name = "formattedAddress", column = @Column(name = "formatted_address")),
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "pobox", column = @Column(name = "pobox")),
            @AttributeOverride(name = "neighborhood", column = @Column(name = "neighborhood")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "region", column = @Column(name = "region")),
            @AttributeOverride(name = "state", column = @Column(name = "state")),
            @AttributeOverride(name = "postCode", column = @Column(name = "post_code")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private List<PostalAddress> postalAddresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "im_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "im_username")),
            @AttributeOverride(name = "service", column = @Column(name = "im_service"))
    })
    private List<InstantMessageAddress> imAddresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "url_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "url")),
            @AttributeOverride(name = "label", column = @Column(name = "url_label"))
    })
    private List<UrlAddress> urlAddresses = new ArrayList<>();

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmailAddress {
        private String label;
        private String email;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PhoneNumber {
        private String label;
        private String number;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostalAddress {
        private String label;
        private String formattedAddress;
        private String street;
        private String pobox;
        private String neighborhood;
        private String city;
        private String region;
        private String state;
        private String postCode;
        private String country;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InstantMessageAddress {
        private String username;
        private String service;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Birthday {
        private int day;
        private int month;
        private int year;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UrlAddress {
        private String url;
        private String label;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
