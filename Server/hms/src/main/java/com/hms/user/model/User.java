package com.hms.user.model;

import com.hms.Address.model.Address;
import com.hms.common.dto.APP_ENUM.*;
import com.hms.common.dto.Tables;
import com.hms.common.model.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = Tables.USER)
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "doctorFlag")
    private boolean doctorFlag;

    @Column(name = "patient_flag")
    private boolean patientFlag;

    @Column(name = "staff_flag")
    private boolean staffFlag;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "staff_role")
    private String staffRole;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "date_joined")
    private Date dateJoined;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    private String status;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<Address> addresses;

}
