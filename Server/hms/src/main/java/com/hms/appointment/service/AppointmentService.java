package com.hms.appointment.service;

import com.hms.appointment.dto.AppointmentTO;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;

public interface AppointmentService {
    AppointmentTO saveOrUpdate(AppointmentTO appointmentTO);
    AppointmentTO byId(Integer id);
    CommonListTO<AppointmentTO> getAppointmentList(FilterObject filterObject);
    String updateAppointment(FilterObject filterObject);
}
