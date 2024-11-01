package com.library;

import util.ExcelReader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryAppTest {
    LibraryService service = new LibraryService();

    @Test
    public void testLibraryService() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<String[]> testCases = excelReader.readExcel("LibraryManagementTestCases.xlsx");
        List<String[]> results = new ArrayList<>();

        for (String[] testCase : testCases) {
            String testCaseID = testCase[0];
            String function = testCase[1].trim(); 
            String description = testCase[2];
            String inputData = testCase.length > 3 ? testCase[3] : ""; 
            String expectedOutput = testCase.length > 4 ? testCase[4] : ""; 

            String actualOutput = "";
            String status = "Pass"; 
            String remarks = "";

            switch (function) {
                case "addBook":
                    String[] bookData = inputData.split(","); 
                    service.addBook(bookData[0].trim(), bookData[1].trim());
                    actualOutput = service.getBook(bookData[0].trim()).getAuthor(); 
                    break;
                case "removeBook":
                    boolean removed = service.removeBook(inputData.trim());
                    actualOutput = removed ? inputData.trim() : "Not Found";
                    break;
                case "registerMember":
                    String[] memberData = inputData.split(",");
                    if (memberData.length < 2) {
                        status = "Fail";
                        remarks = "Invalid input data for registerMember.";
                        actualOutput = "Error";
                        break;
                    }
                    Member member = service.registerMember(memberData[0].trim(), memberData[1].trim());
                    actualOutput = member != null ? member.getName() : "Error"; 
                    break;
                case "borrowBook":
                    boolean borrowed = service.borrowBook(inputData.trim());
                    actualOutput = String.valueOf(borrowed);
                    break;
                case "returnBook":
                    boolean returned = service.returnBook(inputData.trim());
                    actualOutput = returned ? "Available" : "Not Available"; 
                    break;
                case "getTotalBooks":
                    actualOutput = String.valueOf(service.getTotalBooks());
                    break;
                default:
                    status = "Fail";
                    remarks = "Invalid function specified.";
                    actualOutput = "Error";
                    break;
            }

            if (!status.equals("Fail") && !expectedOutput.equals(actualOutput)) {
                status = "Fail";
            }
            results.add(new String[]{testCaseID, function, description, inputData, expectedOutput, actualOutput, status, remarks});
        }

        excelReader.writeResults("LibraryTestResults.xlsx", results);
    }
}