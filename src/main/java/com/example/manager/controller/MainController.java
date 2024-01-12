package com.example.manager.controller;


import com.example.manager.dto.UserDto;
import com.example.manager.entity.User;
import com.example.manager.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;

    @GetMapping("main")
    private String mains(Model model){

        List<User> userList = userRepository.findAllByIsActive("0");
        model.addAttribute("userList", userList);

        return "main";
    }

    @GetMapping("detail/{id}")
    private String detail(@PathVariable String id, Model model){
        User user = userRepository.findById(id).orElse(null);

        model.addAttribute("user", user);

        return "detail";
    }

    @GetMapping("modify/{id}")
    private String modify(@PathVariable String id, Model model){
        User user = userRepository.findById(id).orElse(null);

        model.addAttribute("user", user);

        return "modify";
    }

    @PostMapping("modify")
    private String modifyProc(UserDto userDto){
        User user = User.builder()
                .id(userDto.getId())
                .password(userDto.getPassword1())
                .name(userDto.getName())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .birth(userDto.getBirth())
                .registration(userDto.getRegistration())
                .joindate(userDto.getJoindate())
                .isActive(userDto.getIsActive())
                .build();

        userRepository.save(user);

        return "redirect:/detail/" + userDto.getId();
    }

    @GetMapping("delete/{id}")
    private String delete(@PathVariable String id){
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            user.setIsActive("1");
            userRepository.save(user);
        }

        return "redirect:/main";
    }

    @GetMapping("printExcel/{id}")
    public void printExcel(@PathVariable String id, HttpServletResponse response){
        User user = userRepository.findById(id).orElse(null);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("현장정보시트");

        String fileName = "excel/test_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss")) + ".xlsx";

        String[] header = {"id","name"};
        Row row = sheet.createRow(0);

        for(int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i+1);
            cell.setCellValue(header[i]);
        }

        row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue(user.getId());
        cell = row.createCell(2);
        cell.setCellValue(user.getName());

        try {
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=userInfo.xls");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
