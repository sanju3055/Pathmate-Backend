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

    @Embedded
    private Birthday birthday;

    @ElementCollection
    @CollectionTable(name = "email_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "label", column = @Column(name = "email_label")),
            @AttributeOverride(name = "email", column = @Column(name = "email_address"))
    })
    private List<EmailAddress> emails = new ArrayList<>();

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
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "region", column = @Column(name = "region")),
            @AttributeOverride(name = "postcode", column = @Column(name = "postcode")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private List<PostalAddress> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "url_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "url")),
            @AttributeOverride(name = "label", column = @Column(name = "url_label"))
    })
    private List<UrlAddress> urls = new ArrayList<>();

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
        private String street;
        private String city;
        private String region;
        private String postcode;
        private String country;
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
