package com.hms.appointment.controller;

import com.hms.appointment.dto.AppointmentTO;
import com.hms.appointment.service.AppointmentService;
import com.hms.common.dto.ApiResponse;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService sAppointment;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveOrUpdateHandler(@RequestBody AppointmentTO appointment){
        appointment = sAppointment.saveOrUpdate(appointment);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM01","/patient","appointment",appointment);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/byId/{id}",method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> byIdHandler(@PathVariable Integer id){
        AppointmentTO appointment = sAppointment.byId(id);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM01","/patient","appointment",appointment);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> searchHandler(@RequestBody FilterObject filterObject){
        CommonListTO<AppointmentTO> appointment = sAppointment.getAppointmentList(filterObject);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM04","/search","appointments",appointment);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/update-fields",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateStatusHandler(@RequestBody FilterObject filterObject){
        String updated = sAppointment.updateAppointment(filterObject);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM04","/search","appointments",updated);
        return ResponseEntity.ok(response);
    }
}
