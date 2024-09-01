package com.hms.user.dao;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.user.model.User;

public interface UserDao {
    CommonListTO<User> search(FilterObject filterObject);
}
