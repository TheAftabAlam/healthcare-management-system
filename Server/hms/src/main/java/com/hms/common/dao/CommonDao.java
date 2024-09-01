package com.hms.common.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommonDao{
    <E> E persistWithFlush(E model);

    <E> E updateWithFlush(E model);

    @Transactional
    <E> E persistWithFlushBySession(E model);

    @Transactional
    <E> E updateWithFlushBySession(E model);

    <E> E deleteWithFlush(E model);

    <E> E findById(Class<E> entityClass, Integer id);
}
