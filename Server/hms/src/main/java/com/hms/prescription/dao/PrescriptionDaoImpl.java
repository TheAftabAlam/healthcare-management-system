package com.hms.prescription.dao;

import com.hms.appointment.model.Appointment;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import com.hms.prescription.dto.PrescriptionDTO;
import com.hms.prescription.model.Prescription;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrescriptionDaoImpl implements PrescriptionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CommonListTO<Prescription> search(FilterObject filterObject) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();

        // Query for Appointments
        CriteriaQuery<Prescription> criteriaQuery = queryBuilder.createQuery(Prescription.class);
        Root<Prescription> entityRoot = criteriaQuery.from(Prescription.class);
        List<Predicate> searchFilter = new ArrayList<>();

        if (filterObject != null) {
            if (filterObject.getPatientId() != null) {
                searchFilter.add(queryBuilder.equal(
                        entityRoot.get("patientId"),
                        filterObject.getPatientId()
                ));
            }
            if (filterObject.getDoctorId() != null) {
                searchFilter.add(queryBuilder.equal(
                        entityRoot.get("doctorId"),
                        filterObject.getDoctorId()
                ));
            }
        }
        criteriaQuery.where(queryBuilder.and(searchFilter.toArray(new Predicate[0])));
        criteriaQuery.orderBy(queryBuilder.desc(entityRoot.get("id")));
        TypedQuery<Prescription> query = entityManager.createQuery(criteriaQuery);

        // Pagination
        CommonListTO<Prescription> data = new CommonListTO<>();
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
        Root<Prescription> countRoot = countQuery.from(Prescription.class);
        countQuery.select(queryBuilder.count(countRoot)).where(queryBuilder.and(searchFilter.toArray(new Predicate[0])));

        Long count = entityManager.createQuery(countQuery).getSingleResult();
        data.setTotalRow(count);
        if (limit != 0) {
            data.setPageCount((count.intValue() + limit - 1) / limit);
        } else {
            data.setPageCount(1);
        }

        return data;
    }
}
