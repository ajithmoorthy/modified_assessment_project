package org.atmecs.website.testscripts;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.atmecs.website.constants.FileConstants;
import org.atmecs.website.pages.InsightsPage;
import org.atmecs.website.testbase.TestBase;
import org.atmecs.website.utils.ExcelReader;
import org.atmecs.website.utils.PropertiesReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Insights extends TestBase {
	InsightsPage insight=new InsightsPage();
	ExcelReader excelread=new ExcelReader();
	PropertiesReader property=new PropertiesReader();
	@DataProvider(name = "insightsdata")
	public String[][] getInsightsData() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.excelDataProviderArray(FileConstants.INSIGHTS_TEST_DATE_PATH);
		return Excelarray;
	}
	@Test(priority=2,dataProvider="insightsdata")
	public void validateBlogs(String title1,String title2,String title3,String title4,String title5,String title6) throws IOException, InterruptedException, ParseException {
	driver.get("http://www.atmecs.com/");
	String titlestring=title1+","+title2+","+title3+","+title4+","+title5+","+title6;
	String[] titlearray=titlestring.split(",");
	//String contentstring=content1+","+content2+","+content3+","+content4+","+content5+","+content6;
	//String[] contentarray=contentstring.split(",");
	String[] contentarray=null;
	Properties propObject=property.KeyValueLoader(FileConstants.INSIGHT_PATH);
	logger=extentObject.startTest("Validate Blogs and Comments");
	insight.validateBlogs(driver, propObject,titlearray,contentarray);
	}
}
