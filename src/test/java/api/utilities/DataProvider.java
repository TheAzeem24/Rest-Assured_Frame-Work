package api.utilities;

public class DataProvider {
	
	@org.testng.annotations.DataProvider(name="Data")
	public String[][] getAllData() throws Exception
	{
		String path = System.getProperty("user.dir")+"//Testdata//Userdata.xlsx";
		ExcelUtility excel = new ExcelUtility(path);
		
		int rowCount = excel.getRowCount(path, "userdata");
		int cellCount = excel.getCellCount(path, "userdata", rowCount);
		
		String apidata[][] = new String[rowCount][cellCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			for(int j=0;j<=cellCount-1;j++)
			{
				apidata[i-1][j] = excel.getCellData(path, "userdata", i, j);
			}
		}
		return apidata;
	}
	
	@org.testng.annotations.DataProvider(name="userNames")
	public String[] getUserName() throws Exception
	{
		String path = System.getProperty("user.dir")+"//Testdata//Userdata.xlsx";
		ExcelUtility excel = new ExcelUtility(path);
		
		int rowCount = excel.getRowCount(path, "userdata");
		
		String apidata[] = new String[rowCount];
		
		for(int i=0;i<=rowCount-1;i++)
		{
			apidata[i] = excel.getCellData(path, "userdata", i+1, 1);
			System.out.println(i+"  "+apidata[i]);
		}
		return apidata;
	}
}
