package com.hms.inventory.dao;

import com.hms.appointment.model.Appointment;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import com.hms.inventory.model.Inventory;
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
public class InventoryDaoImpl implements InventoryDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CommonListTO<Inventory> search(FilterObject filterObject) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Inventory> criteriaQuery = queryBuilder.createQuery(Inventory.class);
        Root<Inventory> entityRoot = criteriaQuery.from(Inventory.class);
        List<Predicate> searchFilter = new ArrayList<>();
        searchFilter.add(queryBuilder.isFalse(
                entityRoot.get("deletedFlag")
        ));
        if (filterObject != null) {
            // Build predicates
            if (CommonUtils.isNotNullOrEmpty(filterObject.getItemName())) {
                searchFilter.add(queryBuilder.equal(
                        entityRoot.get("itemName"),
                        filterObject.getItemName()
                ));
            }
            if (CommonUtils.isNotNullOrEmpty(filterObject.getItemCode())) {
                searchFilter.add(queryBuilder.equal(
                        entityRoot.get("itemCode"),
                        filterObject.getItemCode()
                ));
            }
            if (CommonUtils.isNotNullOrEmpty(filterObject.getStatus())) {
                searchFilter.add(queryBuilder.like(
                        queryBuilder.lower(entityRoot.get("status")),
                        "%" + filterObject.getStatus().toLowerCase() + "%"
                ));
            }

        }
        criteriaQuery.where(queryBuilder.and(searchFilter.toArray(new Predicate[0])));
        criteriaQuery.orderBy(queryBuilder.desc(entityRoot.get("id")));

        TypedQuery<Inventory> query = entityManager.createQuery(criteriaQuery);

        // Pagination
        CommonListTO<Inventory> data = new CommonListTO<>();
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
        Root<Inventory> countRoot = countQuery.from(Inventory.class);
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
