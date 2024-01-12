package com.example.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private LocalDate birth;
    private LocalDate registration;
    private LocalDate joindate;
    @Column(name = "is_active")
    private String isActive;
    @OneToMany
    @JoinColumn(name = "userId")
    private List<WorkSite> workSiteList = new ArrayList<>();
}
