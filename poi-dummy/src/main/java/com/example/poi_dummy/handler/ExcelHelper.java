package com.example.poi_dummy.handler;

import com.example.poi_dummy.entity.Employee;
import com.example.poi_dummy.exception.ExcelException;
import com.example.poi_dummy.exception.ExceptionCodes;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ExcelHelper {

    List<String> headers = List.of("EmployeeId", "Name", "Email", "DateOfJoining", "Salary","Address");

    public  byte[] createExcelTemplate() {
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Template");

            CellStyle headerStyle = wb.createCellStyle();
            Font bold = wb.createFont();
            bold.setBold(true);
            headerStyle.setFont(bold);
            headerStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            Row header = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell c = header.createCell(i);
                c.setCellValue(headers.get(i));
                c.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
            }

            Row sample = sheet.createRow(1);
            sample.createCell(0).setCellValue("E1001");
            sample.createCell(1).setCellValue("John Doe");
            sample.createCell(2).setCellValue("john.doe@example.com");
            sample.createCell(3).setCellValue("2024-01-15");
            sample.createCell(4).setCellValue(50000);

            // Auto-size
            for (int i = 0; i < headers.size(); i++) sheet.autoSizeColumn(i);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            wb.write(out);
            return out.toByteArray();
        }
        catch (IOException ioException){
            throw new RuntimeException(ioException.getMessage());
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<Employee> processExcelTemplate(MultipartFile file) throws Exception {

        if (this.validateExcelHeader(file)) {
            throw new ExcelException(ExceptionCodes.WRONG_HEADERS);
        }
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        if (noOfColumns < 1) {
            throw new ExcelException(ExceptionCodes.INVALID_EXCEL_FILE);
        }
        List<Employee> employees = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            Employee employee = new Employee();
            for (Cell cell : row) {
                employee.setEmployeeId(cell.getStringCellValue());
                employee.setName(cell.getStringCellValue());
                employee.setEmail(cell.getStringCellValue());
                employee.setDateOfJoining(LocalDate.parse(cell.getStringCellValue()));
                employee.setSalary(Double.parseDouble(cell.getStringCellValue()));
            }
            employees.add(employee);

        }
        return employees;
    }


    public boolean validateExcelHeader(MultipartFile file) throws Exception {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (!cell.getCellType().toString().equals(headers.get(cell.getColumnIndex()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
