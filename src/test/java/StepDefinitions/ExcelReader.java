package StepDefinitions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class ExcelReader {

    public static String readDataFromExcel(String filePath, String sheetName, int rowIndex, int columnIndex) {
        try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet != null) {
                Row row = sheet.getRow(rowIndex);

                if (row != null) {
                    Cell cell = row.getCell(columnIndex);

                    if (cell != null) {
                        return cell.getStringCellValue();
                    } else {
                        System.out.println("Cell is null at columnIndex: " + columnIndex);
                    }
                } else {
                    System.out.println("Row is null at rowIndex: " + rowIndex);
                }
            } else {
                System.out.println("Sheet is null with name: " + sheetName);
            }

            return null; // or throw an exception based on your requirements
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
