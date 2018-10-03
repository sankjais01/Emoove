package main;

import utils.ExcelUtils;

public class TestData {

	public String USERNAME;
	public String PASSWORD;

	public void readExcelData() {
		ExcelUtils tdata = new ExcelUtils(System.getProperty("user.dir") + "/resources/utils/User.xlsx", "Users");
		try {
			int rows = tdata.excel_get_rows();
			int cols = tdata.excel_get_cols();
			//System.out.println("Total rows- " + rows + " Total Cols- " + cols);
			String data[][] = new String[rows][cols];
			for (int r = 1; r <= 1; r++) {
				for (int c = 0; c < cols; c++) {
					data[r][c] = tdata.getCellDataAsString(r, c);
					//System.out.println(data[r][c]);
				}
			}

			USERNAME = data[1][0];
			PASSWORD = data[1][1];

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
