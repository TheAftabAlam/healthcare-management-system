package com.hms.prescription.services;

import com.hms.common.dao.CommonDao;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import com.hms.prescription.dao.PrescriptionDao;
import com.hms.prescription.dto.PrescriptionDTO;
import com.hms.prescription.dto.PrescriptionTO;
import com.hms.prescription.model.Prescription;
import com.hms.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService{

    @Autowired
    private CommonDao dCommon;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrescriptionDao dPrescription;
    @Override
    public PrescriptionTO saveOrUpdate(PrescriptionTO prescriptionTO) {
        Prescription prescription =  modelMapper.map(prescriptionTO,Prescription.class);
        if(CommonUtils.isNotNullOrEmpty(prescription.getId())){
            dCommon.updateWithFlush(prescription);
        }else{
            dCommon.persistWithFlush(prescription);
        }
        return modelMapper.map(prescription,PrescriptionTO.class);
    }

    @Override
    public PrescriptionTO byId(Integer id) {
        PrescriptionTO prescription = modelMapper.map(dCommon.findById(Prescription.class,id),PrescriptionTO.class);
        if(CommonUtils.isNotNullOrEmpty(prescription.getPatientId())){
            User user = dCommon.findById(User.class, prescription.getPatientId());
            prescription.setPatient(user);
        }if(CommonUtils.isNotNullOrEmpty(prescription.getDoctorId())){
            User user = dCommon.findById(User.class, prescription.getDoctorId());
            prescription.setDoctor(user);
        }
        return prescription;
    }

    @Override
    public CommonListTO<PrescriptionDTO> search(FilterObject filterObject) {
        CommonListTO<PrescriptionDTO> patientTOListWrapper = new CommonListTO<>();
        CommonListTO<Prescription> commonListTO = dPrescription.search(filterObject);
        if(CommonUtils.isNotNullOrEmpty(commonListTO)){
            List<PrescriptionDTO> prescriptions=new ArrayList<>();
            for(Prescription prescription : commonListTO.getDataList()){
                PrescriptionDTO prescriptionDTO =modelMapper.map(prescription,PrescriptionDTO.class);
                if(CommonUtils.isNotNullOrEmpty(prescription.getPatientId())){
                    User user = dCommon.findById(User.class, prescription.getPatientId());
                    prescriptionDTO.setPatientName(user.getFirstName()+" "+user.getLastName());
                }
                if(CommonUtils.isNotNullOrEmpty(prescription.getDoctorId())){
                    User user = dCommon.findById(User.class, prescription.getDoctorId());
                    prescriptionDTO.setDoctorName(user.getFirstName()+" "+user.getLastName());
                }
                prescriptions.add(prescriptionDTO);
            }
            patientTOListWrapper.setDataList(prescriptions);
            patientTOListWrapper.setTotalRow(commonListTO.getTotalRow());
            patientTOListWrapper.setPageCount(commonListTO.getPageCount());
        }
        return patientTOListWrapper;
    }
}
