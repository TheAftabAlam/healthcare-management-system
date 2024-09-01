package com.hms.appointment.dao;

import com.hms.appointment.model.Appointment;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.CommonUtils;
import com.hms.common.dto.FilterObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class AppointmentDaoImpl implements AppointmentDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public CommonListTO<Appointment> getAppointmentList(FilterObject filterObject) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();

        // Query for Appointments
        CriteriaQuery<Appointment> criteriaQuery = queryBuilder.createQuery(Appointment.class);
        Root<Appointment> entityRoot = criteriaQuery.from(Appointment.class);
        List<Predicate> searchFilter = new ArrayList<>();

        if (filterObject != null) {
            // Build predicates
            if (filterObject.getPatientId() != null) {
                searchFilter.add(queryBuilder.equal(
                        entityRoot.get("patient"),
                        filterObject.getPatientId()
                ));
            }
            if (filterObject.getDoctorId() != null) {
                searchFilter.add(queryBuilder.equal(
                        entityRoot.get("doctor"),
                        filterObject.getDoctorId()
                ));
            }

            if (CommonUtils.isNotNullOrEmpty(filterObject.getAppointmentDate())) {
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(filterObject.getAppointmentDate());
                searchFilter.add(queryBuilder.equal(
                       queryBuilder.function("DATE", java.sql.Date.class, entityRoot.get("appointmentDate")),
                       java.sql.Date.valueOf(formattedDate)));
            }

            if (CommonUtils.isNotNullOrEmpty(filterObject.getStatus())) {
                searchFilter.add(queryBuilder.like(
                        queryBuilder.lower(entityRoot.get("status")),
                        "%" + filterObject.getStatus().toLowerCase() + "%"
                ));
            }
            if (CommonUtils.isNotNullOrEmpty(filterObject.getAppointmentType())) {
                searchFilter.add(queryBuilder.like(
                        queryBuilder.lower(entityRoot.get("appointmentType")),
                        "%" + filterObject.getAppointmentType().toLowerCase() + "%"
                ));
            }

        }
        criteriaQuery.where(queryBuilder.and(searchFilter.toArray(new Predicate[0])));
        if(!CommonUtils.isNotNullOrEmpty(filterObject.getAppointmentDate())){
            criteriaQuery.orderBy(queryBuilder.desc(entityRoot.get("id")));
        }

        TypedQuery<Appointment> query = entityManager.createQuery(criteriaQuery);

        // Pagination
        CommonListTO<Appointment> data = new CommonListTO<>();
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
        Root<Appointment> countRoot = countQuery.from(Appointment.class);
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

    @Override
    public String generateTokenNumber(Date appointmentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String datePrefix = dateFormat.format(appointmentDate);

        String lastTokenQuery = "SELECT pa.tokenNumber FROM Appointment pa WHERE FUNCTION('DATE', pa.appointmentDate) = FUNCTION('DATE', :appointmentDate) ORDER BY pa.tokenNumber DESC";

        String lastTokenNumber = entityManager.createQuery(lastTokenQuery, String.class)
                .setParameter("appointmentDate", appointmentDate)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElse(null);

        int nextNumber = 1;
        if (lastTokenNumber != null) {
            String lastNumberStr = lastTokenNumber.substring(lastTokenNumber.indexOf('-') + 1);
            nextNumber = Integer.parseInt(lastNumberStr) + 1;
        }
        String tokenNumberSuffix = String.format("%03d", nextNumber);

        return datePrefix + "-" + tokenNumberSuffix;
    }

    @Override
    public String updateAppointmentFields(FilterObject filterObject) {
        String updateQuery = "UPDATE Appointment SET ";

        List<String> setClauses = new ArrayList<>();
        Map<String, Object> parameters = new HashMap<>();

        if (CommonUtils.isNotNullOrEmpty(filterObject.getStatus())) {
            setClauses.add("status = :status");
            parameters.put("status", filterObject.getStatus());
        }

        if (CommonUtils.isNotNullOrEmpty(filterObject.getAppointmentType())) {
            setClauses.add("appointmentType = :appointmentType");
            parameters.put("appointmentType", filterObject.getAppointmentType());
        }

        if (!setClauses.isEmpty()) {
            updateQuery += String.join(", ", setClauses);
        }

        updateQuery += " WHERE id = "+filterObject.getId();

        Query query = entityManager.createQuery(updateQuery);

        parameters.forEach(query::setParameter);

        int rowsUpdated = query.executeUpdate();

        return rowsUpdated + " rows updated";
    }






}
