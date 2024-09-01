package com.hms.user.dao;

import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.user.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CommonListTO<User> search(FilterObject filterObject) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();

        // Query for Patients
        CriteriaQuery<User> criteriaQuery = queryBuilder.createQuery(User.class);
        Root<User> entityRoot = criteriaQuery.from(User.class);
        List<Predicate> searchFilter = new ArrayList<>();

        searchFilter.add(queryBuilder.isFalse(entityRoot.get("deletedFlag")));

        if (filterObject != null) {
            // Build predicates
            if (filterObject.getFirstName() != null) {
                searchFilter.add(queryBuilder.like(
                        queryBuilder.lower(entityRoot.get("firstName")),
                        "%" + filterObject.getFirstName().toLowerCase() + "%"
                ));
            }
            if (filterObject.getLastName() != null) {
                searchFilter.add(queryBuilder.like(
                        queryBuilder.lower(entityRoot.get("lastName")),
                        "%" + filterObject.getLastName().toLowerCase() + "%"
                ));
            }
            if (filterObject.isDoctorFlag()) {
                searchFilter.add(queryBuilder.isTrue(
                       entityRoot.get("doctorFlag")
                ));
            }
            if (filterObject.isPatientFlag()) {
                searchFilter.add(queryBuilder.isTrue(
                        entityRoot.get("patientFlag")
                ));
            }
        }
        criteriaQuery.where(queryBuilder.and(searchFilter.toArray(new Predicate[0])));
        criteriaQuery.orderBy(queryBuilder.desc(entityRoot.get("id")));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        // Pagination
        CommonListTO<User> data = new CommonListTO<>();
        int limit = filterObject.getLimit();
        int page = filterObject.getPage();
        if (page > 0 && limit > 0) {
            query.setFirstResult((page - 1) * limit);
            query.setMaxResults(limit);
        }

        try {
            data.setDataList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace(); // Improved logging
        }

        // Count Query
        CriteriaQuery<Long> countQuery = queryBuilder.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class);
        countQuery.select(queryBuilder.count(countRoot)).where(queryBuilder.and(searchFilter.toArray(new Predicate[0])));

        Long count = entityManager.createQuery(countQuery).getSingleResult();
        data.setTotalRow(count);

        // Page Count Calculation
        if (limit != 0) {
            data.setPageCount((count.intValue() + limit - 1) / limit);
        } else {
            data.setPageCount(1);
        }

        return data;
    }





}
