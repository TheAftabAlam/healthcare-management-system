package com.hms.inventory.dao;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.inventory.model.Inventory;

public interface InventoryDao {
    CommonListTO<Inventory> search(FilterObject filterObject);
}
