package com.amazon.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

    private static final String EXCEL_PATH = "./src/test/resources/TestData.xlsx";

    public static Object[][] getTestData( String sheetName) {
        Object[][] data = null;
        Map<String, Object> rowData = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream( EXCEL_PATH );
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet( sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new Object[rowCount-1] [1];//excluding header row
            for(int i=1; i<rowCount; i++){ //start from 1 to skip header
                for(int j=0; j<colCount; j++){//APACHE POI INDEX START WITH 0 FOR ROW 1
                    String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
                    Cell cell = sheet.getRow(i).getCell(j);
                    Object cellValue = converCellToObject(cell);
                    rowData.put(columnName, cellValue);
                }
                data[i-1][0] = rowData; //storing the map in first column of data array

            }
            workbook.close();
            fis.close();

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Implementation for reading test data from Excel
    }

    private static Object converCellToObject(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return null;
        }
    }
}
