package com.hms.user.service;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.user.dto.UserTO;

public interface UserService {

    UserTO saveOrUpdate(UserTO patient);

    UserTO byId(Integer id);

    CommonListTO<UserTO> search(FilterObject filterObject);

}
