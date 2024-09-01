package com.hms.common.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CommonDaoImpl implements CommonDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <E> E persistWithFlush(E model) {
        entityManager.persist(model);
        entityManager.flush();
        entityManager.clear();
        return model;
    }

    @Override
    public <E> E updateWithFlush(E model) {
        E merge = entityManager.merge(model);
        entityManager.flush();
        entityManager.clear();
        return merge;
    }

    @Transactional
    @Override
    public <E> E persistWithFlushBySession(E model) {
        Session session = entityManager.unwrap(Session.class);
        session.save(model);
        session.flush();
        session.clear();
        return model;
    }

    @Transactional
    @Override
    public <E> E updateWithFlushBySession(E model) {
        Session session = entityManager.unwrap(Session.class);
        E mergedEntity = (E) session.merge(model);
        session.flush();
        session.clear();
        return mergedEntity;
    }

    @Override
    public <E> E deleteWithFlush(E model) {
        entityManager.remove(model);
        entityManager.flush();
        entityManager.clear();
        return model;
    }

    @Override
    public <E> E findById(Class<E> entityClass, Integer id) {
        return entityManager.find(entityClass, id);
    }

}
