package com.hms.prescription.controller;

import com.hms.common.dto.ApiResponse;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.prescription.dto.PrescriptionDTO;
import com.hms.prescription.dto.PrescriptionTO;
import com.hms.prescription.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService sPrescription;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveOrUpdateHandler(@RequestBody PrescriptionTO prescriptionTO){
        prescriptionTO = sPrescription.saveOrUpdate(prescriptionTO);
        ApiResponse apiResponse = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM01","/prescription","prescription",prescriptionTO);
        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> byIdHandler(@PathVariable Integer id){
        PrescriptionTO prescriptionTO = sPrescription.byId(id);
        ApiResponse apiResponse = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM03","/byId","prescription",prescriptionTO);
        return ResponseEntity.ok(apiResponse);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> searchHandler(@RequestBody FilterObject filterObject){
        CommonListTO<PrescriptionDTO> commonListTO = sPrescription.search(filterObject);
        ApiResponse apiResponse = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM03","/search","prescription",commonListTO);
        return ResponseEntity.ok(apiResponse);
    }
}
