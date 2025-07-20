package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Logindata")
	public String [][] getLoginData() throws IOException{
		
		String path = System.getProperty("user.dir") + "/testData/OpenCart_LoginData.xlsx";
		
		ExcelUtility xlUtil = new ExcelUtility(path);
		
		int totalRow = xlUtil.getRowCount("Sheet1");
		int totalCell = xlUtil.getCellCount("Sheet1",1);
		
		String loginData [][] = new String[totalRow][totalCell];
		
		for(int i = 1;i<=totalRow;i++) {
			for(int j = 0;j<totalCell;j++) {
				
				loginData[i-1][j] = xlUtil.getCellData("Sheet1", i, j);
				
				
			}
		}
		return loginData;
	}
	
	// DataProvider 2 
	// DataProvider 3
	// DataProvider 4 

}
