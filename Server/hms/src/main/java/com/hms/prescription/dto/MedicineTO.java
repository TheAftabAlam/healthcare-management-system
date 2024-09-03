package com.hms.prescription.dto;

import com.hms.inventory.dto.InventoryTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineTO {

    private Integer id;
    private Integer inventoryId;
    private InventoryTO inventory;
    private String dosage;
    private String frequency;
    private String duration;
    private String note;
}
