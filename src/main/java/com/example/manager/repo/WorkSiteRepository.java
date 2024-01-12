package com.example.manager.repo;

import com.example.manager.entity.WorkSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkSiteRepository extends JpaRepository<WorkSite, String> {
}
