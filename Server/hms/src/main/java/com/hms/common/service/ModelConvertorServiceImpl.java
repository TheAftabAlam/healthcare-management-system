package com.hms.common.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelConvertorServiceImpl implements ModelConvertorService{

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public <D, T> D map(final T entity, Class<D> outClass) {
        if (entity != null) {
            return modelMapper.map(entity, outClass);
        }
        return null;
    }
}
