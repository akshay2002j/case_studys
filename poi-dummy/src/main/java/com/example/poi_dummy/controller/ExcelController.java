package com.example.poi_dummy.controller;

import com.example.poi_dummy.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@Slf4j
@RestController
@RequestMapping("/api/")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("template/download")
    public ResponseEntity<byte[]> downloadTemplate() throws Exception {
        byte[] bytes = excelService.getExcelTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee_template.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(bytes);
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadTemplate(@RequestParam("file") MultipartFile file)  {
        log.info("processing data");
        excelService.uploadExcelTemplate(file);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("error/download")
    public ResponseEntity<byte[]> downloadErrorFile() throws Exception {
        File file = new File("D:\\assignments\\case_studys\\poi-dummy\\Error_File.xlsx");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Error_File.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(fileContent);
    }
}