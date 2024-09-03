package com.hms.prescription.model;

import com.hms.common.dto.Tables;
import com.hms.common.model.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = Tables.PRESCRIPTION)
@Getter
@Setter
public class Prescription extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer doctorId;

    private Integer patientId;

    private Date prescriptionDate;

    private String status;

    private String bloodPressure;

    private String bloodSugar;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "prescription_id")
    private List<Medicine> medicines;


}
