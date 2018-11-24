package function
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import static org.junit.Assert.*
import org.testng.Assert as Assert
import java.util.regex.Pattern
import static org.apache.commons.lang3.StringUtils.join
import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.lang.String
import java.lang.Integer
import org.openqa.selenium.Keys
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.testdata.ExcelData as ExcelData

public class CheckPoints {
	
	/*---------------GetValue functionality-----------------*/
	@Keyword	
	public static Boolean DateFormat( WebDriver driver,By locator,String actualFormat, String expectedFormat,boolean result ) {	
		String valueFormat = driver.findElement(locator).getAttribute( actualFormat );
		println ( "GetValue${valueFormat}" )	
		if ( valueFormat.equals( expectedFormat ) ) {
			println ( "GetValue PASSED" )			
			result= true;
		}
		else {
		   println ( "GetValue FAILED" )		   
		   result= false;
		}
		return result;
	}
	/*---------------Delete functionality-----------------*/	
	@Keyword
	public static Boolean Delete( WebDriver driver, By delete_btn,String alertText,String confirmation,boolean result ) {
		WebElement deleteElement= driver.findElement( delete_btn );
		if( deleteElement.displayed.TRUE ) {
			deleteElement.click();
			WebDriverWait wait = new WebDriverWait( driver, 300 );
			if( wait.until( ExpectedConditions.alertIsPresent() )==null){
				println( "alert was not present" );
				result = false;
			}
			else {
				println ( "alert was present" );
				Alert alertbox=driver.switchTo().alert();
				Thread.sleep( 2000 )
				String actualAlerttext= alertbox.getText();
				Thread.sleep( 2000 )
				if ( actualAlerttext.equals( alertText ) ) {
					println "Alert text is: ${actualAlerttext}"
					if ( confirmation.equalsIgnoreCase( "YES" ) ) {
							println ( "confirmation:${confirmation}" )
							Thread.sleep( 2000 )
							alertbox.accept();
							println "Alert accepted and its get deleted"
							result = true;
					}
					else if ( confirmation.equalsIgnoreCase( "NO" ) ) {
						Thread.sleep(2000)
						alertbox.dismiss();
						println "Alert dismissed and its not get deleted"
						result = true;
					}
					else {
						Thread.sleep( 2000 )
						println ( "Element is not displayed" )						
						result = false;
					}
				}							
			}			
		}
		else {
			Thread.sleep( 2000 )
			println ( "Delete element is not displayed" )			
			result = false;
		}
		return result;
	}
	/*------------------PopUp Accept-------------------*/
	@Keyword
	public static void confirmationPopup_Accept( WebDriver driver, By locatorAccept ) {
		driver.findElement( locatorAccept ).click();
		Alert alertAccept = driver.switchTo().alert();
		String alertText = alertAccept.getText();
		println( alertText );
		Thread.sleep( 2000 );
		alertAccept.accept();
	}
	/*------------------PopUp Dismiss-------------------*/
	@Keyword
	public static void confirmationPopup_Dismiss( WebDriver driver, By locatorDismiss ) {
		driver.findElement(locatorDismiss).click();
		Alert alertDismiss = driver.switchTo().alert();
		String alertText = alertDismiss.getText();
		println(alertText);
		Thread.sleep(2000);
		alertDismiss.dismiss();
	}
	/*------------------PopUp GetText-------------------*/
	@Keyword
	public static void Popup_gettext( WebDriver driver, By locatorGetValue,String expectedInput ) {
		driver.findElement( locatorGetValue ).click();
		Alert alertTextValue = driver.switchTo().alert();
		String alertText = alertTextValue.getText();
		println( alertText );
		if ( alertText.equals( expectedInput ) ) {
			alertTextValue.accept();			
		}
		else {
			println ( "Text Message as:${expectedInput}" )
			
		}
		Thread.sleep( 2000 );	
	}	
	/*------------------PopUp CharacterValidation-------------------*/
	@Keyword
	public static CharacterValidation( WebDriver driver,By firstField,By clickBtn,String validName, String invalidName ) {
		for ( String invalid : invalidName ) {
			driver.findElement( firstField ).clear();
			driver.findElement( firstField ).sendKeys( validName + invalidName );
			driver.findElement( clickBtn ).click();
			String alertMessage = driver.switchTo().alert().getText();
			println ( invalidName );
			if ( alertMessage.equals( "First name Should not contain Special Characters" ) ) {
				println ( "Error displayed: First name Should not contain Special Characters" );
				driver.switchTo().alert().dismiss();				
			}
			else {
				println("Accepted");				
			}
		}
		driver.findElement(firstField).sendKeys(validName);
		driver.findElement(clickBtn).click();
		String alertMessage = driver.switchTo().alert().getText();
		if (alertMessage.equals("First name Should not contain Special Characters")) {
			println("Error displayed: First name Should not contain Special Characters");
			driver.switchTo().alert().dismiss();
			internal.GlobalVariable.listing.add("PASS");
			println ("list:${internal.GlobalVariable.listing}")
		}
		else {
			println("Accepted");
			internal.GlobalVariable.listing.add("FAIL");
			println ("list:${internal.GlobalVariable.listing}")
		}		
	}
	/*------------------CheckBox Functionality-------------------*/
	@Keyword
	public static Boolean CheckBox( WebDriver driver,By checkBox, String checkBoxInput,boolean result ) {
		def checkValueSplitted = checkBoxInput.split(";")
		ArrayList<String> getResultValue=new ArrayList<String>();
		for ( int checkValue = 0; checkValue<checkValueSplitted; checkValue++ ) {
			List<WebElement> chkbox = driver.findElements( checkBox );
			int size = chkbox.size();
			println ( "Total no of Check boxes :"+size );
			for ( int i=0; i< size; i++ ) {
				String textValue = chkbox.get(i).getText();
				println( "Check Box Name "+textValue );
				if ( textValue.equalsIgnoreCase( checkValueSplitted[checkValue] ) ) {
					chkbox.get(i).click();
					println ( "Success:${checkValueSplitted[checkValue]}" )
					getResultValue.addAll( "PASS" )
				}
				else {
					println ( "Failed:${checkValueSplitted[checkValue]}" )
					getResultValue.addAll( "FAIL" )
				}
			}
		}
		if ( getResultValue.indexOf( "FAIL" )<0 ) {
			result = true;
		}
		else {
			result = false;
		}
		return result
	}
	/*------------------Click Functionality-------------------*/
	@Keyword
	public static Boolean Click( WebDriver driver,By buttonClick,boolean result ) {
		println ( "Inside data:${buttonClick}" )
		Thread.sleep(3000)
		WebElement field = driver.findElement( buttonClick );
		println ( "Inside Field:${field}" )
		if ( field.isDisplayed() ) {
		 println ( "PASSED Button" )
		 field.click();		
		 result= true;
		}
		else {
			println ( "FAILED Button" )			
			result=false;
		}
		return result;
	}
	/*------------------Disabled Button Functionality-------------------*/
	@Keyword
//	public static Boolean DisabledBtn( WebDriver driver,By locatorElement, boolean result ) {	
//		WebElement chkElement = driver.findElement( locatorElement );	
//		if( chkElement.isEnabled() ) {
//			println ( "It has been Enabled" )			
//			result=true;
//		}
//		else {
//			println ( "It has been Disabled" )			
//			result= false;
//		}
//		return result;
//	 }
	
	
	public static Boolean DisabledBtn( WebDriver driver,By locator,String actualFormat, String expectedFormat,boolean result ) {
			if ( (actualFormat.equalsIgnoreCase("readonly")) || ( actualFormat.equalsIgnoreCase("disabled"))) {
				if ( !(expectedFormat.empty)) {
					String disabledBtn = driver.findElement(locator).getAttribute( actualFormat );
					println ( "dateFormat${disabledBtn}" )
					if ( disabledBtn.equals( expectedFormat ) ) {
						println ( "disabledBtn PASSED" )
						result= true;
					}
					else {
						println ( "disabledBtn FAILED" )
						result= false;
					}			
				}
				else {
					WebElement disabledBtn = driver.findElement(locator).getAttribute( actualFormat );
					if ( disabledBtn.isDisplayed() ) {
						result= true;
					}
					else {
						result= false;
					}				
				}
			}
	
			/*else if ( actualFormat.equalsIgnoreCase("disabled") ) {
				if ( !(expectedFormat.empty)) {
					String disabledBtn = driver.findElement(locator).getAttribute( actualFormat );
					println ( "dateFormat${disabledBtn}" )
					if ( disabledBtn.equals( expectedFormat ) ) {
						println ( "disabledBtn PASSED" )
						result= true;
					}
					else {
						println ( "disabledBtn FAILED" )
						result= false;
					}
				}
				else {
					WebElement disabledBtn = driver.findElement(locator).getAttribute( actualFormat );
					if ( disabledBtn.isDisplayed() ) {
						result= true;				
					}
					else {
						result= false;
					}		
				}
		   }*/
			else {
				result= false;
			}
		   return result;
	}
	
	/*------------------Download files Functionality-------------------*/
	 @Keyword
	 public static Boolean DownloadFile( WebDriver driver,By downloadBtn, String fileName,String downloadPath, boolean result ) {
		String fileExt = fileName.substring( fileName.lastIndexOf(".") );
		println ( "arlTest:${fileExt}" )
		File dir = new File( downloadPath )
		'Creating an Array where it will store all the files from that folder'
		File[] dir_contents = dir.listFiles()
		println ( 'Total Files Available in the folder are : ' + dir_contents.length )
		'Iterating a loop for number of files available in the folder to verify file name in the folder'
		for ( int i = 0; i < dir_contents.length; i++ ) {
			println ( 'File Name at 0 is : ' + dir_contents[i].getName() )
			'Verifying the file name is available in the folder '
			if ( dir_contents[i].getName().equals( fileName ) ) {
				'If the file is found then it will return a value as true'
				dir_contents[i].delete();				
				break;
			}
			else {
				println ( "NO SUCH FILE NOT FOUND" )				
			}			
		}
		if ( ( fileExt.equals(".csv") ) || ( fileExt.equals(".xls") ) || ( fileExt.equals(".pdf") ) || ( fileExt.equals(".xlsx") ) || ( fileExt.equals(".xml") ) ) {
			driver.findElement( downloadBtn ).click();
			Thread.sleep( 7000 )			
			'Wait for Some time so that file gets downloaded and Stored in user defined path'
			WebUI.delay(10)
			'Verifying the file is download in the User defined Path'
			Assert.assertTrue( isFileDownloaded( downloadPath,fileName ), 'Failed to download Expected document' );			
			result= true;
		}
		else {
			println ( "EXPECTED FILE NOT FOUND" )			
			result= false;
		}
		return result;
	}
	static boolean isFileDownloaded( String downloadPath, String actualfileName ) {
		println ( "Filename:${actualfileName}" )
		boolean flag = false
		'Creating an object for File and passing the download Path as argument'
		File dir = new File( downloadPath )
		'Creating an Array where it will store all the files from that folder'
		File[] dir_contents = dir.listFiles()		
		println ( 'Total Files Available in the folder are : ' + dir_contents.length )
		'Iterating a loop for number of files available in the folder to verify file name in the folder'
		for ( int i = 0; i < dir_contents.length; i++ ) {
			println ( 'File Name at 0 is : ' + dir_contents[i].getName() )
			'Verifying the file name is available in the folder '
			if ( dir_contents[i].getName().equals( actualfileName ) ) {				
			}
			'If the file is found then it will return a value as true'
			return flag = true			
		}
		return flag
	}
	/*------------------DropDown Functionality-------------------*/
	@Keyword
	public static Boolean Dropdown( WebDriver driver,By locator, String input,boolean result ) {
		Select oSelect = new Select( driver.findElement( locator ) );
		oSelect.selectByVisibleText( input );
		Thread.sleep( 2000 );
		List<WebElement> oSize = oSelect.getOptions();
		int iListSize = oSize.size();
		println iListSize
		for ( int i =0; i < iListSize ; i++ ){
			String sValue = oSelect.getOptions().get(i).getText();
			println ( sValue );
			if ( sValue.equals( input ) ){
				oSelect.selectByIndex( i );
				println ( "Success${input}" )				
				result=true;
				break;
			}
			else {				
				result=false;
			}
		}
		return result;
	}
	/*------------------Focus Element-------------------*/
	@Keyword
	public static void FocusElement( WebDriver driver,String attributeValue,String expectValue ) {	
		println ( "expectEle:${expectValue}" )
		WebElement element =driver.switchTo().activeElement();
		String Check = element.getAttribute( attributeValue );
		println ( "Check${Check}" )
		Assert.assertEquals( expectValue,Check )	
	}
	/*------------------Page Listed Total Count-------------------*/
	@Keyword
	public static Boolean PageCount( WebDriver driver,By pagePath,String input,boolean result ) {
		List<WebElement> rowCount = driver.findElements( pagePath );
		int totalCount = rowCount.size();
		println ( "totalCount${totalCount}" )
			int results = Integer.parseInt( input );
		if ( totalCount<=results ) {
			println ( "PASSED Page Count" )			
			result=true;
		}
		else {
			println ( "FAILED PAGE Count" )			
			result=false;
		}
		return result;
	}
	/*------------------Radio Button Functionality-------------------*/
	@Keyword
	public static Boolean RadioBtn( WebDriver driver,By radioBtn, String radioInput,boolean result ) {
		List<WebElement> rdBtn = driver.findElements( radioBtn );		
		int size = rdBtn.size();
		println ( "Total no of radio button :"+size );
		for ( int i=0; i< size; i++ )		{
			//String sValue = rdBtn.get(i).getAttribute("value");			 
			String radioOptionText = rdBtn.get(i).getText(); 
			println ( "Radio button Name "+radioOptionText );
			if ( radioOptionText.equals( radioInput ) ){
				rdBtn.get(i).click();
				println ( "Success radioOption :${radioInput}" )				
				result=true;
				break;	
			}
			else {
				println ( "Failed radioOption :${radioInput}" )
				result=false;
			}
		}
		return result;
	}
	/*------------------Screenshots Functionality-------------------*/
	@Keyword
	public static void Screenshot( WebDriver webdriver,String DestfilePath ) throws Exception {
		TakesScreenshot scrShot =( ( TakesScreenshot )webdriver );
		File SrcFile=scrShot.getScreenshotAs( OutputType.FILE );
		File DestFile=new File( DestfilePath );
		FileUtils.copyFile( SrcFile, DestFile );
	}
	/*------------------ScrollUp/Down Functionality-------------------*/
	@Keyword
	public static Boolean ScrollAction( WebDriver driver,By Scroll_locator,boolean result ) throws Exception {
	   JavascriptExecutor je = ( JavascriptExecutor ) driver;
	   WebElement scroll_to_element = driver.findElement( Scroll_locator );
	   
		   je.executeScript( "arguments[0].scrollIntoView(true);",scroll_to_element );
		   //if ( scroll_to_element.isDisplayed() ) {
		  // println ( scroll_to_element.getText() );
		   result = true;
	   //}
	  /*else {
		   println ( "Failed to scroll" );
		   result = false;
	   }*/
	   return result;
	}
	/*------------------Search Functionality-------------------*/
	@Keyword
	public static Boolean Search( WebDriver driver,By locatorSearch, String inputSearch,boolean result ) {
		
		WebElement searchResults = driver.findElement( locatorSearch);
		searchResults.sendKeys( inputSearch );
		
		if ( searchResults.isDisplayed() ) {
			searchResults.sendKeys(Keys.ENTER);
			println ( "Search PASSED" )			
			result=true;
		}
		else {		
			println ( "Search FAILED" )			
			result=false;
		}
		return result;
	}
	/*------------------TextBox Functionality-------------------*/
	@Keyword
	public static Boolean Textbox( WebDriver driver,By locatortxtBox, String inputtext,boolean result ) {
		WebElement field = driver.findElement( locatortxtBox );
		if ( field.isDisplayed() ) {
			 println ( "TextBox PASSED" )
			 field.clear();
			 field.sendKeys( inputtext );			
			 result= true;
		}
		else {
			println ( "TextBox FAILED" )			
			result= false;
		}
		return result;
	}
	/*------------------Tool tip Functionality-------------------*/
	@Keyword
	public static Boolean Tooltip( WebDriver driver,By tooltip_element, String title,String expectedValue,boolean result ) {
		Actions builder=new Actions( driver );
		Thread.sleep( 1000 )
		WebElement username_tooltip=driver.findElement( tooltip_element );
		Thread.sleep( 1000 )
		builder.moveToElement( username_tooltip ).perform();
		Thread.sleep( 1000 )
		String tooltip_msg=username_tooltip.getAttribute( title );
		if ( expectedValue.equals( tooltip_msg ) ) {
			println ( "Tooltip/ Help message is "+tooltip_msg );		
			result=true;
		}
		else {
			println ( "Tool tip FAILED" )		
			result= false;
		}
		return result;
	 }
	/*------------------UploadFiles Functionality-------------------*/
	@Keyword
	public static Boolean UploadFiles( WebDriver driver,By locatorUpload, String imageName,boolean result )	{		
		String userDir = System.getProperty( "user.dir" )
		String filePath = userDir+"/"+ imageName
		WebElement uploadElement = driver.findElement( locatorUpload );
		
			uploadElement.sendKeys( filePath )
			result = true;
		
		return result;
	}	
	/*------------------Locator Functionality-------------------*/
	@Keyword
	public static By getLocator( WebDriver driver,String attribute, String attrval ) throws Exception {
		switch( attribute ) {
			case "id":
				return By.id( attrval );
			case "name":
				return By.name( attrval );
			case "xpath":
				return By.xpath( attrval );
			case "css":
				return By.cssSelector( attrval );
			case "class":
				return By.className( attrval );
			case "link":
				return By.linkText( attrval );				
			default:
				throw new Exception( "Unknown selector type " + attribute );
			}
	}
	/*------------------Disabled Text Functionality-------------------*/
	@Keyword
//	public static Boolean DisabledTxt( WebDriver driver,By locatorElement,boolean result ) {
//		WebElement chkElement = driver.findElement( locatorElement );
//		if ( chkElement.isEnabled() ){
//			println ( "It has been Enabled" )			
//			result=true;
//		}
//		else {
//			println ( "It has been Disabled" )			
//			result=false;
//		}
//		return result;
//	 }
	public static Boolean DisabledTxt( WebDriver driver,By locator,String actualFormat, String expectedFormat,boolean result ) {
		if ( (actualFormat.equalsIgnoreCase("readonly")) || ( actualFormat.equalsIgnoreCase("disabled") )) {
			if ( !(expectedFormat.empty)) {
				String disabledTxt = driver.findElement(locator).getAttribute( actualFormat );
				println ( "dateFormat${disabledTxt}" )
				if ( disabledTxt.equalsIgnoreCase("true") ) {
					println ( "disabledTxt PASSED" )
					result= true;
				}
				else {
					println ( "disabledTxt FAILED" )
					result= false;
				}
			}
			else {
				WebElement disabledTxt = driver.findElement(locator).getAttribute( actualFormat );
				if ( disabledTxt.isDisplayed() ) {
					result= true;
				}
				else {
					result= false;
				}
			}
		}

		/*else if ( actualFormat.equalsIgnoreCase("disabled") ) {
			if ( !(expectedFormat.empty)) {
				String disabledTxt = driver.findElement(locator).getAttribute( actualFormat );
				println ( "dateFormat${disabledTxt}" )
				if ( disabledTxt.equalsIgnoreCase("true") ) {
					println ( "disabledTxt PASSED" )
					result= true;
				}
				else {
					println ( "disabledTxt FAILED" )
					result= false;
				}
			}
			else {
				WebElement disabledTxt = driver.findElement(locator).getAttribute( actualFormat );
				if ( disabledTxt.isDisplayed() ) {
					result= true;
				}
				else {
					result= false;
				}
			}
	   }*/
	   else {
		   result= false;
	   }
	   return result;
}
	/*------------------Get Text Functionality-------------------*/
	@Keyword
	public static Boolean GetText( WebDriver driver,By locator,String gettingText, boolean result ) {
		String gettingvalue = driver.findElement( locator ).getText();
		println ( "gettingvalue::${gettingvalue}" )
		if ( gettingvalue.equals( gettingText ) ) {
			println ( "Get Text PASSED" )
			result=true;
		}
		else {
		   println ( "Get Text FAILED" )
		   result=false;
		}
	return result;
	}
	/*------------------Get Text Dynamic Functionality-------------------*/
	static ArrayList<String> gettingDynamicvalue =new ArrayList<String>();
	@Keyword
	public static Boolean GetTextDynamic( WebDriver driver,By locator, boolean result ) {
		
		String totalCount = driver.findElement( locator ).getText();
		println ( "totalCount::${totalCount}" )
		gettingDynamicvalue.addAll(totalCount)
		int txtLength = gettingDynamicvalue.size()
		println ( "gettingvalue::${gettingDynamicvalue}" )
	return result;
	}
	/*------------------Get Text Compare Functionality-------------------*/
	static ArrayList<String> gettingComparevalue =new ArrayList<String>();
	@Keyword
	public static Boolean GetTextCompare( WebDriver driver,By locator, boolean result ) {
		
		String totalCount = driver.findElement( locator ).getText();
		println ( "totalCount::${totalCount}" )
		gettingComparevalue.addAll(totalCount)
		println ( "gettingComparevalue::${gettingComparevalue}" )
		for( int getData = 0; getData<gettingDynamicvalue.size(); )
	return result;
	}
	/*------------------Write to Excel for Pass-------------------*/
	@Keyword
	public static void WriteToExcelPass( Integer sheetCount, Integer xcelRow, Integer xcelCol1,Integer xcelCol, String xcelValue ) {
	   String userDir = System.getProperty( "user.dir" )
	   String filePath = userDir+"/"+ "Frame.xlsx"
	   println ( "File:${filePath}" )
	   println ( "sheetCount:${sheetCount}" )
	   println ( "xcelRow:${xcelRow}" )
	   println ( "xcelCol:${xcelCol}" )
	   println ( "xcelValue:${xcelValue}" )
	   FileInputStream file = new FileInputStream ( new File( filePath ) )
	   XSSFWorkbook workbook = new XSSFWorkbook( file );
	   XSSFSheet sheet = workbook.getSheetAt( sheetCount );
	   'Write data to excel'
	   sheet.getRow( xcelRow ).createCell(xcelCol).setCellValue( xcelValue );
	   sheet.getRow( xcelRow ).createCell( xcelCol1 ).setCellValue( "Working as Expected" );
	   XSSFCellStyle my_style = workbook.createCellStyle();
	   XSSFCell Cell1;
	   XSSFCell Cell2;
	   XSSFRow Row;
	   Row  = sheet.getRow( xcelRow );
	   Cell1 = Row.getCell( xcelCol );
	   Cell2 = Row.getCell( xcelCol1 );
	   //my_style.setFillPattern(XSSFCellStyle.FINE_DOTS );
	   my_style.setFillForegroundColor( IndexedColors.LIGHT_GREEN.getIndex() );
	   my_style.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
	   Cell1.setCellStyle( my_style );
	   Cell2.setCellStyle( my_style );
	   FileOutputStream outFile =new FileOutputStream( new File( filePath ) );
	   workbook.write( outFile );
	   outFile.close();
   }
	/*------------------Write to Excel for Fail-------------------*/
	@Keyword
	public static void WriteToExcelFail( Integer sheetCount, Integer xcelRow, Integer xcelCol1,Integer xcelCol2, String xcelValue,String xcelReason ) {
	   String userDir = System.getProperty( "user.dir" )
	   String filePath = userDir+"/"+ "Frame.xlsx"
	   println ( "File:${filePath}" )
	   println ( "sheetCount:${sheetCount}" )
	   println ( "xcelRow:${xcelRow}" )
	   println ( "xcelCol:${xcelCol1}" )
	   println ( "xcelValue:${xcelValue}" )
	   FileInputStream file = new FileInputStream ( new File( filePath ) )
	   XSSFWorkbook workbook = new XSSFWorkbook( file );
	   XSSFSheet sheet = workbook.getSheetAt( sheetCount );
	   'Write data to excel'
	   sheet.getRow( xcelRow ).createCell( xcelCol1 ).setCellValue( xcelValue );
	   sheet.getRow( xcelRow ).createCell( xcelCol2 ).setCellValue( xcelReason );
	   XSSFCellStyle my_style = workbook.createCellStyle();
	   XSSFCell Cell1;
	   XSSFCell Cell2;
	   XSSFRow Row;
	   Row  = sheet.getRow( xcelRow );
	   Cell1 = Row.getCell( xcelCol1 );
	   Cell2=Row.getCell( xcelCol2 );		 
	   //my_style.setFillPattern(XSSFCellStyle.FINE_DOTS );
	   if(xcelReason.equals("FAIL")){
		   my_style.setFillForegroundColor( IndexedColors.RED.getIndex() );
		   my_style.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
	   }
	   else if (xcelReason.equals("Error")) {
		my_style.setFillForegroundColor( IndexedColors.BROWN.getIndex() );
		   my_style.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
	   }
	   else if (xcelReason.equals("Skipped")) {
		   my_style.setFillForegroundColor( IndexedColors.GREY_25_PERCENT.getIndex() );
			  my_style.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
		  }
	   else{
		   my_style.setFillForegroundColor( IndexedColors.RED.getIndex() );
		   my_style.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );
		   println ("Else_part")
	   }
	   /*my_style.setFillForegroundColor( IndexedColors.GREY_50_PERCENT.getIndex() );
	   my_style.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND );*/
	   Cell1.setCellStyle( my_style );
	   Cell2.setCellStyle( my_style );
	   FileOutputStream outFile =new FileOutputStream( new File( filePath ) );
	   workbook.write( outFile );
	   outFile.close();
   }
	/*------------------Getting the maximum input size from the excel input data-------------------*/
	def Testcases = findTestData( 'Data Files/Framework/TestCase' )
	@Keyword	 
	public int splitData( int Testcaseindex,int DataLength ) {
		ArrayList<String> getSplitData =new ArrayList<String>();
		def UserActionTest = "Start";
		for ( int getData =Testcaseindex; !( UserActionTest.equals( "END" ) ); getData++ ) {
			UserActionTest = Testcases.getValue( 'UserAction', getData )
			println( "UserActionTest:${UserActionTest}")
			def fieldTypeAll = Testcases.getValue( 'FieldType', getData )
			println( "fieldTypeAll:${fieldTypeAll}" )
			getSplitData.addAll(UserActionTest)
			String TestDataAll = Testcases.getValue( 'TestData', getData )
			println ( "TestDataAll:${TestDataAll.length()}" )	
			println ( "TestDataAll,,TestDataAll:--${TestDataAll}" )				
			def tset = TestDataAll.length();
			def testingDataa =  TestDataAll.split(";");
			println ( "testingDataa:${testingDataa}" );
			if (!(TestDataAll.empty)){	
				println ( "TestDataAll:${TestDataAll}" )	
				if ( DataLength<testingDataa.size() ){	
				DataLength = testingDataa.size();
				println ( "Data Length PASSED" )
				}
			}
			else if ( UserActionTest.equals( "End" ) ) {			
				UserActionTest ="END";
			}	
			else {
				println ( "EMPTY" )
			}
			if ( getData == Testcases.getRowNumbers() ){
				UserActionTest = 'END'
				println('Breaking statement')
				println ("getData${getData}")
		}
		}	
		return DataLength;
	}
	
	
}
