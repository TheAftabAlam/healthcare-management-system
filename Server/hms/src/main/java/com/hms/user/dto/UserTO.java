package com.hms.user.dto;
import com.hms.Address.model.Address;
import com.hms.appointment.dto.AppointmentTO;
import com.hms.appointment.model.Appointment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;

    private boolean doctorFlag;

    private boolean patientFlag;

    private boolean staffFlag;

    private String specialization;

    private String staffRole;

    private String emergencyContact;

    private Date dateJoined;

    private String gender;

    private boolean deletedFlag;

    private String status;

    private List<Address> addresses;

    private List<AppointmentTO> appointmentsAsPatient;

    private List<AppointmentTO> appointmentsAsDoctor;
}
