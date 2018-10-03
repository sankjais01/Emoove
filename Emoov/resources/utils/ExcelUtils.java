package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	
	
	private XSSFWorkbook excelWorkBook;
	private XSSFSheet excelSheet;
	

	public ExcelUtils(String path, String sheet) {
		try {
			
			FileInputStream excelFile = new FileInputStream(path);
			excelWorkBook = new XSSFWorkbook(excelFile);
			excelSheet = excelWorkBook.getSheet(sheet);

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("please check file location");
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid file selected");
			System.err.println(e);
			e.printStackTrace();
		}

	}

	public int excel_get_rows() throws Exception {
		try {
			return excelSheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public int excel_get_cols() throws Exception {
		try {
			return excelSheet.getRow(0).getLastCellNum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public String getCellDataAsString(int rowNum, int colNum) throws Exception {
		try {
			String celData = excelSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			return celData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Errors in Getting Cell Data";
		}

	}

	public double getCellDataAsInteger(int rowNum, int ColNum) throws Exception {
		try {
			double celData = excelSheet.getRow(rowNum).getCell(ColNum).getNumericCellValue();
			return celData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.00;
		}
	}

}