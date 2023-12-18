package StepDefinitions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private String excelFilePath;

    public ExcelReader(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public String[] readFirstRow() {
        String[] variables = new String[2];

        try {
            FileInputStream fis = new FileInputStream(excelFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            // Read the first column (index 0) of the first row into a string variable
            Cell firstCell = sheet.getRow(0).getCell(0);
            variables[0] = firstCell.toString();

            // Read the second column (index 1) of the first row into another string variable
            Cell secondCell = sheet.getRow(0).getCell(1);
            variables[1] = secondCell.toString();

            workbook.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return variables;
    }
}