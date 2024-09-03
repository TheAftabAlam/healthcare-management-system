package com.hms.prescription.dto;

import com.hms.prescription.model.Medicine;
import com.hms.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrescriptionTO {

    private Integer id;
    private Integer patientId;
    private User Patient;
    private Integer doctorId;
    private User doctor;
    private String status;
    private String bloodPressure;
    private String bloodSugar;
    private List<MedicineTO> medicines;

}
