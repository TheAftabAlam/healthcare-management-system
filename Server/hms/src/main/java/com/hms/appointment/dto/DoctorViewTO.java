package com.hms.appointment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorViewTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String specialization;

    private String email;
}
