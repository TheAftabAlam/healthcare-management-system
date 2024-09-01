package com.hms.appointment.dto;

import com.hms.user.dto.UserTO;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class AppointmentTO {
    private Integer id;
    private Date appointmentDate;
    private String tokenNumber;
    private String status;
    private Integer patientId;
    private UserTO patient;
    private Integer doctorId;
    private UserTO doctor;
    private String reasonForVisit;
    private String appointmentType;
    private boolean reminderSet;
    private Date reminderTime;
    private String cancellationReason;
}
