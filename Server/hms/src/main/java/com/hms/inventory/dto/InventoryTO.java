package com.hms.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class InventoryTO {

    private Integer id;
    private String itemName;
    private String itemCode;
    private int quantity;
    private double price;
    private Date expiryDate;
    private String supplierName;
    private String batchNumber;
    private String location;
    private boolean deletedFlag;
    private String status;
}
