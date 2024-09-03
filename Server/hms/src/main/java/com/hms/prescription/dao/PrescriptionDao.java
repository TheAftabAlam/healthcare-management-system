package com.hms.prescription.dao;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.prescription.model.Prescription;

public interface PrescriptionDao {
   CommonListTO<Prescription> search(FilterObject filterObject);
}
