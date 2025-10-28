package com.example.poi_dummy.handler;

import com.example.poi_dummy.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ExcelProcessor {

    private static final Pattern EMP_ID_PATTERN = Pattern.compile("^E\\d{4}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public List<Employee> processExcelData(MultipartFile file) throws Exception {
        List<Employee> employees = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Error style (red background)
            CellStyle errorStyle = workbook.createCellStyle();
            errorStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            errorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            boolean hasError = false;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header

                Employee emp = new Employee();

                String empId = getCellValue(row.getCell(0));
                String name = getCellValue(row.getCell(1));
                String email = getCellValue(row.getCell(2));
                String doj = getCellValue(row.getCell(3));
                String salary = getCellValue(row.getCell(4));

                // 1️ Validate EmployeeId
                if (isEmpty(empId) || !EMP_ID_PATTERN.matcher(empId).matches()) {
                    markError(row.getCell(0), "Invalid Employee ID (must be like E1001)", errorStyle);
                    hasError = true;
                } else {
                    emp.setEmployeeId(empId);
                }

                // 2️ Validate Name
                if (isEmpty(name)) {
                    markError(row.getCell(1), "Name is mandatory", errorStyle);
                    hasError = true;
                } else {
                    emp.setName(name);
                }

                // 3️ Validate Email
                if (isEmpty(email) || !EMAIL_PATTERN.matcher(email).matches()) {
                    markError(row.getCell(2), "Invalid email format", errorStyle);
                    hasError = true;
                } else {
                    emp.setEmail(email);
                }

                // 4️ Validate Date of Joining
                try {
                    emp.setDateOfJoining(LocalDate.parse(doj));
                } catch (DateTimeParseException e) {
                    markError(row.getCell(3), "Invalid date format (expected yyyy-MM-dd)", errorStyle);
                    hasError = true;
                }

                // 5️ Validate Salary
                try {
                    double sal = Double.parseDouble(salary);
                    if (sal <= 0) throw new NumberFormatException();
                    emp.setSalary(sal);
                } catch (NumberFormatException e) {
                    markError(row.getCell(4), "Salary must be a positive number", errorStyle);
                    hasError = true;
                }

                if (!hasError) {
                    employees.add(emp);
                }
            }

            if (hasError){
                // If any errors → create an error file
                String errorFilePath = "Error_File.xlsx";
                try (FileOutputStream out = new FileOutputStream(errorFilePath)) {
                    workbook.write(out);
                }
            }

        }

        return employees;
    }

//    public byte [] generateErrorFile(Workbook workbook)  {
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            workbook.write(out);
//            return out.toByteArray();
//        }
//        catch (Exception e) {
//            log.error("Error_File.xlsx", e);
//        }
//        return null;
//    }

    // Helper to read any cell as string safely
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue().toLocalDate().toString();
            }
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return cell.getStringCellValue().trim();
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static void markError(Cell cell, String message, CellStyle errorStyle) {
        if (cell == null) return;
        cell.setCellStyle(errorStyle);
        Sheet sheet = cell.getSheet();
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        CreationHelper factory = sheet.getWorkbook().getCreationHelper();
        ClientAnchor anchor = factory.createClientAnchor();
        anchor.setCol1(cell.getColumnIndex());
        anchor.setRow1(cell.getRowIndex());
        Comment comment = drawing.createCellComment(anchor);
        comment.setString(factory.createRichTextString(message));
        cell.setCellComment(comment);
    }
}

