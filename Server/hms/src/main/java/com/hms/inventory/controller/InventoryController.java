package com.hms.inventory.controller;

import com.hms.appointment.dto.AppointmentTO;
import com.hms.common.dto.ApiResponse;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.inventory.dto.InventoryTO;
import com.hms.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("inventory")
public class InventoryController {
    @Autowired
    private InventoryService sInventory;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveOrUpdateHandler(@RequestBody InventoryTO inventory){
        inventory = sInventory.saveOrUpdate(inventory);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM01","/inventory","inventory",inventory);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/byId/{id}",method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> byIdHandler(@PathVariable Integer id){
        InventoryTO inventory = sInventory.byId(id);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM01","/byId","inventory",inventory);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> searchHandler(@RequestBody FilterObject filterObject){
        CommonListTO<InventoryTO> inventory = sInventory.search(filterObject);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM04","/search","inventory",inventory);
        return ResponseEntity.ok(response);
    }
}
