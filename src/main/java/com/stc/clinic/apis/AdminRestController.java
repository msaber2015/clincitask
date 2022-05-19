package com.stc.clinic.apis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stc.clinic.bussiness.AppointmentsService;
import com.stc.clinic.models.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    private AppointmentsService appointmentsService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getTodayAppointments(@RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size,
                                                  @RequestParam(required = false) String patient,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return appointmentsService.getTodayAppointments(page,size,patient,date);
    }

    @PostMapping("/appointment/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelAppointment(@RequestParam Long id,@RequestParam String reason){
        appointmentsService.cancelAppointment(id,reason);
    }

    @PostMapping("/appointment")
    @ResponseStatus(HttpStatus.OK)
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentsService.createAppointment(appointment);
    }

    @GetMapping("/appointment/patient/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getPatientAppointments(@PathVariable String email){
        return appointmentsService.getPatientAppointments(email);
    }
}
