package com.hms.appointment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientViewTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;
}
