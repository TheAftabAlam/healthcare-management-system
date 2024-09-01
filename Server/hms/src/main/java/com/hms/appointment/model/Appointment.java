package com.hms.appointment.model;

import com.hms.common.dto.Tables;
import com.hms.common.model.Auditable;
import com.hms.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = Tables.APPOINTMENT)
public class Appointment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "token_number")
    private String tokenNumber;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    private String status;

    @Column(name = "reason_for_visit")
    private String reasonForVisit;

    @Column(name = "appointment_type")
    private String appointmentType;

    @Column(name = "reminder_set")
    private boolean reminderSet;

    @Column(name = "reminder_time")
    private Date reminderTime;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

}

