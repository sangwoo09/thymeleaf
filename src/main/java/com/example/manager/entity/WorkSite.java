package com.example.manager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worksite")
public class WorkSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String userId;
    private String companyName;
    private String station;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
