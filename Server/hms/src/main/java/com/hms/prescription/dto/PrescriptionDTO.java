package com.hms.prescription.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PrescriptionDTO {

    private Integer id;
    private String patientName;
    private String doctorName;
    private Date prescriptionDate;
    private String note;
    private String status;
    private String bloodPressure;
    private String bloodSugar;
}
