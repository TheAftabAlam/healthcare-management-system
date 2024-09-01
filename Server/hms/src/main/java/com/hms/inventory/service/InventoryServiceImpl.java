package com.hms.inventory.service;

import com.hms.common.dao.CommonDao;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import com.hms.inventory.dao.InventoryDao;
import com.hms.inventory.dto.InventoryTO;
import com.hms.inventory.model.Inventory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private CommonDao dCommon;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InventoryDao dInventory;

    @Override
    public InventoryTO saveOrUpdate(InventoryTO inventoryTO) {
        if(!CommonUtils.isNotNullOrEmpty(inventoryTO.getStatus())){
            inventoryTO.setStatus("ACTIVE");
        }
        Inventory inventory = modelMapper.map(inventoryTO,Inventory.class);
        if(inventory.getId()==null){
            inventory=dCommon.persistWithFlush(inventory);
        }else{
            inventory=dCommon.updateWithFlush(inventory);
        }
        return modelMapper.map(inventory,InventoryTO.class);
    }

    @Override
    public InventoryTO byId(Integer id) {
        Inventory inventory = dCommon.findById(Inventory.class,id);
        return modelMapper.map(inventory,InventoryTO.class);
    }

    @Override
    public CommonListTO<InventoryTO> search(FilterObject filterObject) {
        CommonListTO<Inventory> commonList = dInventory.search(filterObject);
        List<InventoryTO> appointments = commonList.getDataList().stream().map(eachAppointment -> modelMapper.map(eachAppointment,InventoryTO.class)).collect(Collectors.toList());
        CommonListTO<InventoryTO> patientTOListWrapper = new CommonListTO<>();
        patientTOListWrapper.setDataList(appointments);
        patientTOListWrapper.setTotalRow(commonList.getTotalRow());
        patientTOListWrapper.setPageCount(commonList.getPageCount());
        return patientTOListWrapper;
    }
}
