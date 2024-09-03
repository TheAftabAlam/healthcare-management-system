package com.hms.prescription.model;

import com.hms.common.dto.Tables;
import com.hms.inventory.model.Inventory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = Tables.MEDICINE)
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id" ,insertable = false, updatable = false)
    private Inventory inventory;


    @Column(name = "dosage")
    private String dosage;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "duration")
    private String duration;

    @Column(name = "note")
    private String note;
}
