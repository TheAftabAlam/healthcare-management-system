package com.hms.appointment.dao;

import com.hms.appointment.model.Appointment;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;

import java.util.Date;

public interface AppointmentDao {
    CommonListTO<Appointment> getAppointmentList(FilterObject filterObject);

    String generateTokenNumber(Date appointmentDate);

    String updateAppointmentFields(FilterObject filterObject);
}
