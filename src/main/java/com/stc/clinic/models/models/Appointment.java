package com.stc.clinic.models.models;

import com.stc.clinic.constants.enums.PatientStatus;
import com.stc.clinic.models.Patient;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Appointment {

    @Id
    @SequenceGenerator(name = "PATIENT_SEC", allocationSize = 1, sequenceName = "PATIENT_SEC")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_SEC")
    private long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String time;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private PatientStatus status;

    private String cancelReason;

}
