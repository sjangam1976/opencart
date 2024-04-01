package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	static public WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups= {"sanity", "regression", "master"})
	@Parameters({"browser"})
	public void setup(String br) throws IOException 
	
	{
		//loading properties file
			
		
			FileReader file=new FileReader(".//src/test/resources/config.properties");
			prop= new Properties();
			prop.load(file);
			
		
		//loading log4j2 file
			logger = LogManager.getLogger(this.getClass());
		
		
			if(prop.getProperty("execution_env").equals("remote")) 
			{
				DesiredCapabilities capabilities=new DesiredCapabilities();
				
				//os
				
				//browser
				switch(br.toLowerCase()) {
				case "chrome" : capabilities.setBrowserName("chrome"); break;
				case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
				default: System.out.println("no matching records"); return;
				
				}
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			}
			
			
			else if(prop.getProperty("execution_env").equals("local")) {
				//launch browser based on condition-locally
				switch(br.toLowerCase()) 
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			default:System.out.println("No Matching browser...");
			
				return;
			}
			}

		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL"));
	}
	
	@AfterClass(groups= {"sanity", "regression", "master"})
	public void teardown() {
		
		driver.close();
	}
public String randomeString() {
		
		String generateString=RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	public String randomNumber() {
		
		String generateString = RandomStringUtils.randomNumeric(10);
		return generateString;
	}
	
	public String randomAlphaNumeric() {
		
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return(str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
