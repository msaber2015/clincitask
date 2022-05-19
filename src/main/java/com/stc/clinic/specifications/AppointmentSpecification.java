package com.stc.clinic.specifications;

import com.stc.clinic.constants.vo.AppointmentSearchCriteria;
import com.stc.clinic.models.models.Appointment;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSpecification implements Specification<Appointment> {

    private final AppointmentSearchCriteria request;

    public AppointmentSpecification(AppointmentSearchCriteria request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        if(this.request.getPatientName() != null && !this.request.getPatientName().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("patient").get("name"),request.getPatientName()));
        }
        if(this.request.getDate() != null){
            predicates.add(criteriaBuilder.equal(root.get("date"),request.getDate()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
