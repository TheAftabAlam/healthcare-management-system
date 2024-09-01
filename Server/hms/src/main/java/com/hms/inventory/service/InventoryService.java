package com.hms.inventory.service;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.inventory.dto.InventoryTO;

public interface InventoryService {
    InventoryTO saveOrUpdate(InventoryTO inventory);
    InventoryTO byId(Integer id);
    CommonListTO<InventoryTO> search(FilterObject filterObject);

}
