package com.stc.clinic.repositories;

import com.stc.clinic.models.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment,Long> , JpaSpecificationExecutor<Appointment> {

    List<Appointment> getAppointmentsByPatient_Email(String email);

    List<Appointment> getAppointmentsByDate(Date date);

}
