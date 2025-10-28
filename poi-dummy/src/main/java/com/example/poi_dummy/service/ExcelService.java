package com.example.poi_dummy.service;



import com.example.poi_dummy.entity.Employee;
import com.example.poi_dummy.handler.ExcelHelper;
import com.example.poi_dummy.handler.ExcelProcessor;
import com.example.poi_dummy.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {


    private static final Logger log = LoggerFactory.getLogger(ExcelService.class);
    @Autowired
    ExcelHelper excelHelper;

    @Autowired
    ExcelProcessor excelProcessor;

    @Autowired
    EmployeeRepository employeeRepository;

    public  byte[] getExcelTemplate() throws IOException {
        return excelHelper.createExcelTemplate();
    }

    public void uploadExcelTemplate(MultipartFile file)  {
        try {
            List<Employee> employeeList = excelProcessor.processExcelData(file);
            employeeRepository.saveAll(employeeList);
        } catch (Exception e) {
           log.error(e.getMessage());
        }

    }
}
