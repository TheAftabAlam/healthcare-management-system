package com.hms.prescription.services;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.prescription.dto.PrescriptionDTO;
import com.hms.prescription.dto.PrescriptionTO;

public interface PrescriptionService {

    PrescriptionTO saveOrUpdate(PrescriptionTO prescription);
    PrescriptionTO byId(Integer id);

    CommonListTO<PrescriptionDTO> search(FilterObject filterObject);
}
