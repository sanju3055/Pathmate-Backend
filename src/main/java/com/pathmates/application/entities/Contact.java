package com.pathmates.application.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pathmates.application.utils.Address;
import com.pathmates.application.utils.IMAccount;
import com.pathmates.application.utils.PhoneNumber;
import com.pathmates.application.utils.SocialProfile;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Include
    private String contactId;
    
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nickname;
    private String company;
    private String jobTitle;
    private String department;
    // private LocalDate birthday;
    private String note;

    @ElementCollection
    @CollectionTable(name = "contact_phone_numbers", joinColumns = @JoinColumn(name = "contact_id"))
    @Column(name = "phone_number")
    private List<PhoneNumber> phoneNumbers;

    @ElementCollection
    @CollectionTable(name = "contact_addresses", joinColumns = @JoinColumn(name = "contact_id"))
    private List<Address> addresses;

    @ElementCollection
    @CollectionTable(name = "contact_im_accounts", joinColumns = @JoinColumn(name = "contact_id"))
    private List<IMAccount> imAccounts;

    @ElementCollection
    @CollectionTable(name = "contact_social_profiles", joinColumns = @JoinColumn(name = "contact_id"))
    private List<SocialProfile> socialProfiles;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
