package com.stc.clinic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stc.clinic.models.models.Appointment;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Patient {

    @Id
    @SequenceGenerator(name = "PATIENT_SEC", allocationSize = 1, sequenceName = "PATIENT_SEC")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_SEC")
    private long id;

    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Appointment> appointments;

}
