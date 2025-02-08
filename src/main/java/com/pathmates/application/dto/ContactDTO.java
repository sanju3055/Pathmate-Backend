package com.pathmates.application.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.pathmates.application.utils.Address;
import com.pathmates.application.utils.IMAccount;
import com.pathmates.application.utils.PhoneNumber;
import com.pathmates.application.utils.SocialProfile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactDTO {
    
    private Long id;
    private String contactId;
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
    private List<PhoneNumber> phoneNumbers;
    private List<Address> addresses;
    private List<IMAccount> imAccounts;
    private List<SocialProfile> socialProfiles;
}





