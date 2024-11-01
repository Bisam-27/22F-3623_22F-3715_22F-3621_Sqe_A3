package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

	 public List<String[]> readExcel(String filePath) throws IOException {
	        List<String[]> data = new ArrayList<>();
	        try (FileInputStream fis = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheetAt(0);
	            for (Row row : sheet) {
	                String[] rowData = new String[row.getPhysicalNumberOfCells()];
	                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
	                    Cell cell = row.getCell(i);
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            rowData[i] = cell.getStringCellValue();
	                            break;
	                        case BOOLEAN:
	                            rowData[i] = String.valueOf(cell.getBooleanCellValue());
	                            break;
	                        case NUMERIC:
	                            rowData[i] = String.valueOf(cell.getNumericCellValue());
	                            break;
	                        default:
	                            rowData[i] = ""; // handle other types as needed
	                    }
	                }
	                data.add(rowData);
	            }
	        }
	        return data;
	    }

    public void writeResults(String filePath, List<String[]> results) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Results");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Test Case ID");
        header.createCell(1).setCellValue("Function/Class");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Input Data");
        header.createCell(4).setCellValue("Expected Output");
        header.createCell(5).setCellValue("Actual Output");
        header.createCell(6).setCellValue("Status");
        header.createCell(7).setCellValue("Remarks");

        for (int i = 0; i < results.size(); i++) {
            String[] result = results.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < result.length; j++) {
                row.createCell(j).setCellValue(result[j]);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
