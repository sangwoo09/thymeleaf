package com.example.manager.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Poi {

    public static void poiTest() {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("현장정보시트");

        String fileName = "excel/test_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss")) + ".xlsx";

        String[] header = {"no","id","name"};
        Row row = sheet.createRow(0);

        for(int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i+1);
            cell.setCellValue(header[i]);
        }

        try {
            OutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        poiTest();
    }
}