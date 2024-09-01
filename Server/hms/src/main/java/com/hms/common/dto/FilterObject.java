package com.hms.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FilterObject {
    private int limit;
    private int page;

    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private Integer patientId;
    private Integer doctorId;
    private String status;

    private boolean doctorFlag;
    private boolean patientFlag;

    private String itemName;
    private String itemCode;
    private Date appointmentDate;
    private String appointmentType;
    private Integer id;

}
