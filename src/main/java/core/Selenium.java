package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

//BEGIN

public class Selenium {
	
	public String[][] dem1() throws IOException {
		
		String csvFile = "./src/main/resources/Title_Validation.csv";
		BufferedReader br = null;
		String line = null;
		String[] column = null;
		int lines = 0;
		int columns = 0;
		String SplitBy = "_";
		String test_case_id = null;
		String url = null;
		String title_expected = null;
		
		//COUNTING LINES AND COLUMNS
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			lines++;
			column = line.split(SplitBy);
			columns = column.length;
		}
		br.close();
		
		String dem2[][] = new String[lines][columns];
		br = new BufferedReader(new FileReader(csvFile));
		//System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new HtmlUnitDriver();
		WebDriver driver = new FirefoxDriver();
		int i = 0;
		while ((line=br.readLine()) != null){
			String[] csv = line.split(SplitBy);
			
			test_case_id = csv[0];
			url = csv[1];
			title_expected = csv[2];
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();
			
			dem2[i][0] = test_case_id;
			dem2[i][1] = title_expected;
			dem2[i][2] = title_actual;
			
			i++;
		}
		
		driver.quit();
		br.close();
		return dem2;
	}
	/*public static void main(String[]args) throws IOException {
		core.Selenium selenium = new core.Selenium();
		selenium.dem1();
	}*/

}
