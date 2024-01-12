package com.example.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WorkSiteDto {

    private String userId;
    private String companyName;
    private String station;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
