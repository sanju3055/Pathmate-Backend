package com.pathmates.application.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PostalAddress {


    private Long id;

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
