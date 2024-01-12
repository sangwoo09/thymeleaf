package com.example.manager.controller;

import com.example.manager.dto.WorkSiteDto;
import com.example.manager.repo.WorkSiteRepository;
import com.example.manager.service.WorkSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("worksite/")
public class WorkSiteController {

    @Autowired
    WorkSiteService workSiteService;

    @GetMapping("reg")
    private String reg(){

        return "worksite/reg";
    }

    @PostMapping("reg")
    private String regProc(WorkSiteDto workSiteDto, Model model){

        boolean result = workSiteService.reg(workSiteDto);

        if(result) {
            return "redirect:/detail/" + workSiteDto.getUserId();
        }else {
            model.addAttribute("msg","입력된 값을 확인해 주세요.");
            return "worksite/reg";
        }
    }
}
