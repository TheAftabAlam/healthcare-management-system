package com.hms.user.service;

import com.hms.appointment.dto.AppointmentTO;
import com.hms.appointment.service.AppointmentService;
import com.hms.common.dao.CommonDao;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import com.hms.user.dao.UserDao;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private CommonDao dCommon;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDao dUser;

    @Autowired
    private AppointmentService sAppointment;

    @Override
    public UserTO saveOrUpdate(UserTO userTO) {
        if(!CommonUtils.isNotNullOrEmpty(userTO.getStatus())){
           userTO.setStatus("ACTIVE");
        }
        User user = modelMapper.map(userTO, User.class);
        if(userTO.getId() != null){
            user =dCommon.updateWithFlush(user);
        }
        else{
            user =dCommon.persistWithFlush(user);
        }
        return modelMapper.map(user, UserTO.class);
    }

    @Override
    public UserTO byId(Integer id) {
        User user = dCommon.findById(User.class,id);
        FilterObject filterObject =new FilterObject();
        UserTO userTO = modelMapper.map(user, UserTO.class);
        if(user.isDoctorFlag()){
            filterObject.setDoctorId(user.getId());
            CommonListTO<AppointmentTO> commonListTO = sAppointment.getAppointmentList(filterObject);
            userTO.setAppointmentsAsDoctor(commonListTO.getDataList());
        } else if (user.isPatientFlag()) {
            filterObject.setPatientId(user.getId());
            CommonListTO<AppointmentTO> commonListTO = sAppointment.getAppointmentList(filterObject);
            userTO.setAppointmentsAsPatient(commonListTO.getDataList());
        }

        return userTO;
    }

    @Override
    public CommonListTO<UserTO> search(FilterObject filterObject) {
        CommonListTO<User> commonList = dUser.search(filterObject);

        List<UserTO> userTOList = commonList.getDataList().stream()
                .map(patient -> modelMapper.map(patient, UserTO.class))
                .collect(Collectors.toList());

        CommonListTO<UserTO> userTOListWrapper = new CommonListTO<>();
        userTOListWrapper.setDataList(userTOList);
        userTOListWrapper.setTotalRow(commonList.getTotalRow());
        userTOListWrapper.setPageCount(commonList.getPageCount());

        return userTOListWrapper;
    }



}
