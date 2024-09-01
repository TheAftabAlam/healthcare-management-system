package com.hms.inventory.model;

import com.hms.common.dto.Tables;
import com.hms.common.model.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.INVENTORY)
@Getter
@Setter
public class Inventory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "batch_number")
    private String batchNumber;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    @Column(name = "status")
    private String status;

    @Column(name = "location")
    private String location;

    public void reduceQuantity(int quantity) {
        if (quantity > this.quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        this.quantity -= quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

}

