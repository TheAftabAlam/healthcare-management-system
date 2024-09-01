package com.hms.appointment.service;

import com.hms.appointment.dao.AppointmentDao;
import com.hms.appointment.dto.AppointmentTO;
import com.hms.appointment.model.Appointment;
import com.hms.common.dao.CommonDao;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import com.hms.user.dto.UserTO;
import com.hms.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private CommonDao dCommon;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppointmentDao dAppointment;


    @Override
    @Transactional
    public AppointmentTO saveOrUpdate(AppointmentTO appointmentTO) {
        if(!CommonUtils.isNotNullOrEmpty(appointmentTO.getAppointmentType())){
            appointmentTO.setAppointmentType("SCHEDULED");
        }
        if(!CommonUtils.isNotNullOrEmpty(appointmentTO.getStatus())){
            appointmentTO.setStatus("ACTIVE");
        }
        appointmentTO.setTokenNumber(dAppointment.generateTokenNumber(appointmentTO.getAppointmentDate()));
        Appointment appointment = modelMapper.map(appointmentTO, Appointment.class);
        if(appointmentTO.getDoctorId()!=null){
            User doctor = dCommon.findById(User.class, appointmentTO.getDoctorId());
            appointment.setDoctor(doctor);
        }
        if(appointmentTO.getPatientId()!=null){
            User patient = dCommon.findById(User.class, appointmentTO.getPatientId());
            appointment.setPatient(patient);
        }
        if(appointmentTO.getId() != null){
            appointment =dCommon.updateWithFlushBySession(appointment);
        }
        else{
            appointment =dCommon.persistWithFlushBySession(appointment);
        }
        return modelMapper.map(appointment, AppointmentTO.class);
    }

    @Override
    public AppointmentTO byId(Integer id) {
        Appointment appointment = dCommon.findById(Appointment.class,id);
        AppointmentTO appointmentTO = modelMapper.map(appointment,AppointmentTO.class);
        if(appointment.getDoctor() != null){
            appointmentTO.setDoctorId(appointment.getDoctor().getId());
        }
        if(appointment.getPatient() != null){
            appointmentTO.setPatientId(appointment.getPatient().getId());
        }
        return appointmentTO;
    }

    @Override
    public CommonListTO<AppointmentTO> getAppointmentList(FilterObject filterObject){
        CommonListTO<Appointment> commonList = dAppointment.getAppointmentList(filterObject);
        List<AppointmentTO> appointments = commonList.getDataList().stream().map(eachAppointment -> modelMapper.map(eachAppointment,AppointmentTO.class)).collect(Collectors.toList());
        CommonListTO<AppointmentTO> patientTOListWrapper = new CommonListTO<>();
        patientTOListWrapper.setDataList(appointments);
        patientTOListWrapper.setTotalRow(commonList.getTotalRow());
        patientTOListWrapper.setPageCount(commonList.getPageCount());
        return patientTOListWrapper;
    }

    @Override
    public String updateAppointment(FilterObject filterObject){
       return dAppointment.updateAppointmentFields(filterObject);
    }





}
