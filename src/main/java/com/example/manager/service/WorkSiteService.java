package com.example.manager.service;

import com.example.manager.dto.WorkSiteDto;
import com.example.manager.entity.WorkSite;
import com.example.manager.repo.WorkSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkSiteService {

    private final WorkSiteRepository workSiteRepository;

    public boolean reg(WorkSiteDto workSiteDto){
        try{
            WorkSite workSite = WorkSite.builder()
                    .userId(workSiteDto.getUserId())
                    .companyName(workSiteDto.getCompanyName())
                    .station(workSiteDto.getStation())
                    .projectName(workSiteDto.getProjectName())
                    .startDate(workSiteDto.getStartDate())
                    .endDate(workSiteDto.getEndDate())
                    .status(workSiteDto.getStatus())
                    .build();

            workSiteRepository.save(workSite);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
