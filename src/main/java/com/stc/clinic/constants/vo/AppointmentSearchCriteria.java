package com.stc.clinic.constants.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentSearchCriteria {
    private Date date;
    private String patientName;
}
