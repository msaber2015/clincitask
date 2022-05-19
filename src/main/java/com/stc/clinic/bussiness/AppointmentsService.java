package com.stc.clinic.bussiness;


import com.stc.clinic.constants.enums.PatientStatus;
import com.stc.clinic.constants.vo.AppointmentSearchCriteria;
import com.stc.clinic.models.Patient;
import com.stc.clinic.models.models.Appointment;
import com.stc.clinic.repositories.AppointmentsRepository;
import com.stc.clinic.specifications.AppointmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentsService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    public List<Appointment> getTodayAppointments(Integer page,Integer size,String patient,Date date){

        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size);
        AppointmentSearchCriteria appointmentSearchCriteria =  new AppointmentSearchCriteria();
        appointmentSearchCriteria.setDate(date);
        appointmentSearchCriteria.setPatientName(patient);
        AppointmentSpecification appointmentSpecification = new AppointmentSpecification(appointmentSearchCriteria);
        Page<Appointment> appointments = appointmentsRepository.findAll(appointmentSpecification,pageable);

        return appointments.getContent();
    }

    public void cancelAppointment(Long id,String reason){
        Appointment appointment = appointmentsRepository.findById(id).get();
        appointment.setCancelReason(reason);
        appointment.setStatus(PatientStatus.CANCELED);
        appointmentsRepository.save(appointment);
    }

    public Appointment createAppointment(Appointment appointment){
        return appointmentsRepository.save(appointment);
    }

    public List<Appointment> getPatientAppointments(String email){
        List<Appointment> appointments = appointmentsRepository.getAppointmentsByPatient_Email(email);
        return appointments;
    }

    @PostConstruct
    public void load(){
        /*Appointment appointment =new Appointment();
        appointment.setDate(new Date());
        Patient patient =new Patient();
        patient.setId(1);
        appointment.setPatient(patient);
        appointment.setTime("10:00");
        appointment.setStatus(PatientStatus.ACTIVE);
        appointmentsRepository.save(appointment);*/
    }
}
