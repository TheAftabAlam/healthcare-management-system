package com.hms.user.dto;

import com.hms.appointment.dto.DoctorViewTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserAppointmentTO {
    private Integer id;
    private Date appointmentDate;
    private String status;
    private DoctorViewTO doctor;
}
