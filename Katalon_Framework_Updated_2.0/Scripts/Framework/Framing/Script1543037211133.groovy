import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.stringtemplate.v4.compiler.CodeGenerator.region_return as region_return
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.eclipse.jetty.util.thread.ThreadClassLoaderScope as ThreadClassLoaderScope
import org.junit.After
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver

WebUI.openBrowser('')
'Getting the Login sheet Data from Data files'
def loginData = findTestData('Data Files/Framework/Configuration')
'Getting the Url Data from Testcase sheet'
def url = loginData.getValue('URL', 1)
if ( !(url.isEmpty() ) ) {
	'Navigate the URL'
	WebUI.navigateToUrl(url)
	def driver = DriverFactory.getWebDriver()
	driver.manage().window().maximize()
	'Getting the Configuration sheet Data from Data files'
	def Configuration = findTestData('Data Files/Framework/Configuration')
	'Getting the Testcase sheet Data from Data files'
	def Testcases = findTestData('Data Files/Framework/TestCase')
	def Testcaseindex ;
	def endPoint = 'Start'
	def moduleName
	ArrayList<String> getTestColumn = new ArrayList<String>()
	ArrayList<String> getSplitData = new ArrayList<String>()
	ArrayList<String> noOfTabs
	println ("Configuration.getRowNumbers(),${Configuration.getRowNumbers()}")
	def setData=0;
	def sizeOfData=GlobalVariable.ActualConfigindex.size();
	for(setData;setData<sizeOfData;setData++) {	
		def configIndex=GlobalVariable.ActualConfigindex[setData]
		def tcIndex=GlobalVariable.ActualTCindex[setData]
		def getConfigData = configIndex
		'Looping for N- no.of input from Configuration sheet'
		println("getConfigData:$getConfigData")
		println ("Configuration.getRowNumbers(),${Configuration.getRowNumbers()}")
		'Getting the Module_Name Data from Configuration sheet'
		def scenarioData = Configuration.getValue('Module_Name', getConfigData)	
		println("scenarioData:$scenarioData")	
		'Getting the User Action Data from Configuration sheet'
		def UserActionData = Configuration.getValue('UserAction', getConfigData)	
		println("UserActionData:$UserActionData")	
		'Getting the ExecutionStatus Data from Configuration sheet'
		def executionStatus = Configuration.getValue('ExecutionStatus', getConfigData)
		Testcaseindex = tcIndex
		'Looping for N- no.of input from Testcase sheet'
		println("getConfigData_inside:$getConfigData")	
		println("Testcases.getRowNumbers():${Testcases.getRowNumbers()}")
		'Getting the Modules Data from Testcase sheet'
		def Scenario = Testcases.getValue('Modules', Testcaseindex)	
		'Condition for Module data field should not be empty'
		if (!(Scenario.empty)) {
			'Push Module Data in the array-->getTestColumn'
			getTestColumn.addAll(Scenario)

			'Getting last index of the array value'
			moduleName = getTestColumn.get(getTestColumn.size() - 1)

			println("TestcaseindexNumber:$Testcaseindex")

			println("columntest:$getTestColumn")

			println("testtest:$moduleName")

			'Getting the UserAction Data from Testcase sheet'
			def UserAction = Testcases.getValue('UserAction', Testcaseindex)

			println("Scenario:$Scenario")

			println("UserAction:$UserAction")

			try {
				'Condition for checking the Module Name and User Action are same in both Configuration and Testcase Sheets'
				if ((scenarioData.equalsIgnoreCase(moduleName) && UserActionData.equalsIgnoreCase(UserAction)) && executionStatus.equals(
					'YES')) {
					if (moduleName.equalsIgnoreCase('Login') && UserAction.equals('SecondWindow')) {
						JavascriptExecutor jse = ((driver) as JavascriptExecutor)

						jse.executeScript('window.open()')

						noOfTabs = new ArrayList<String>(driver.getWindowHandles())

						//ArrayList<String> noOfTabs = new ArrayList<String> (driver.getWindowHandles());
						driver.switchTo().window(noOfTabs.get(1))

						Thread.sleep(2000)

						def secondWindow = loginData.getValue('URL', 2)

						driver.get(secondWindow //ArrayList<String> noOfTabs = new ArrayList<String> (driver.getWindowHandles());
							)
					} else if (moduleName.equalsIgnoreCase('Login') && UserAction.equals('ThirdWindow')) {
						JavascriptExecutor jse = ((driver) as JavascriptExecutor)

						jse.executeScript('window.open()')

						noOfTabs = new ArrayList<String>(driver.getWindowHandles())

						driver.switchTo().window(noOfTabs.get(2))

						Thread.sleep(2000)

						def thirdWindow = loginData.getValue('URL', 3)

						driver.get(thirdWindow)
					} else if (UserAction.equals('SecondWindow')) {
						driver.switchTo().window(noOfTabs.get(1))

						Thread.sleep(1000)
					} else if (UserAction.equals('ThirdWindow')) {
						driver.switchTo().window(noOfTabs.get(2))

						Thread.sleep(1000)
					} else {
					}
					
					int DataLength = 0

					'Custom keyword used for split and getting the maximum length for the InputData cell from the Testcase sheet'
					int dataValues = CustomKeywords.'function.CheckPoints.splitData'(Testcaseindex, DataLength)

					println("DATA:$dataValues")

					'Looping for N- no.of input for InputData cell from the Testcase sheet'
					for (int getSplitedData = 0; getSplitedData < dataValues; getSplitedData++) {
						endPoint = 'continue'

						println("getSplitedData:$getSplitedData")

						int index = Testcaseindex

						'Looping for the Application Scenario to run sequentially'
						for (index; endPoint != 'BreakNow'; index++) {
							println("index:$index")
							def testDatas = index + 1
							println("testDatas:${testDatas}")
							'Getting the UserAction Data from Testcase sheet'
							def UserActions = Testcases.getValue('UserAction', testDatas)
							'Getting the FieldType Data from Testcase sheet'
							def fieldType = Testcases.getValue('FieldType', testDatas)
							println("fieldType:$fieldType")
							'Getting the LocatorType Data from Testcase sheet'
							String locatorType = Testcases.getValue('LocatorType', testDatas)
							println("LocatorType:$locatorType")
							'Getting the AttributeValue Data from Testcase sheet'
							String attributeValue = Testcases.getValue('AttributeValue', testDatas)
							'Getting the LocatorValue Data from Testcase sheet'
							String locatorValue = Testcases.getValue('LocatorValue', testDatas)
							println("LocatorValue:$locatorValue")
							'Getting the TestData Data from Testcase sheet'
							def TestData = Testcases.getValue('TestData', testDatas)
							println("TestData:$TestData")
							'Getting the validationInput Data from Testcase sheet'
							def validationData = Testcases.getValue('validationInput', testDatas)
							println("validationData:$validationData")
							boolean result
							println("getSplitedDatainside:$getSplitedData")
							'Splitting the Multiple Input data'
							def TestDataSplited = TestData.split(';')
							def assignValue
							def validationDataSplited = validationData.split(';')
							def validateValue
							'Splitted Data assigning to the assignValue variable'
							if (getSplitedData < TestDataSplited.size()) {
								assignValue = (TestDataSplited[getSplitedData])
								println ("assignValue:${assignValue}")
							}
							'Splitted Data assigning to the validateValue variable'
							if (getSplitedData < validationDataSplited.size()) {
								validateValue = (validationDataSplited[getSplitedData])
								println ("validateValue:${validateValue}")
							}
							'Switch case used to run Scenario sequentially'
							switch (UserActions) {
								case 'End':
									endPoint = 'BreakNow'
									println('Breaking switch')
									break
								case 'End_SecondWindow':
									//Switch windows parent
									println('Breaking switch')
									driver.switchTo().window(noOfTabs.get(0))
									break
								case 'End_ThirdWindow':
									//Switch windows parent
									println('Breaking switch')
									driver.switchTo().window(noOfTabs.get(0))
									break;
								case 'Click':
									switchclick(driver,fieldType,locatorType,locatorValue,assignValue,result,testDatas,getSplitedData,attributeValue,validateValue)
									break;
								case 'Text':
									switchtext(driver,fieldType,locatorType,locatorValue,assignValue,result,testDatas,getSplitedData,attributeValue,validateValue)
									break;
								case 'Select':
									switchselect(driver,fieldType,locatorType,locatorValue,assignValue,result,testDatas,getSplitedData,attributeValue,validateValue)
									break;
								case 'Scroll':
									scrollAction ( driver, locatorType, locatorValue, result, testDatas, getSplitedData )
									break
							    case 'UploadFiles':
									   uploadFiles ( driver,fieldType,locatorType,locatorValue,assignValue,result,testDatas,getSplitedData,attributeValue,validateValue )
									break;
								case 'DownloadFiles':
									try {
										String downloadPath = assignValue
										String fileName = validateValue
										'Custom keyword for combining the Locator Value and Locator Type for DownloadFiles functionality'
										By downloadBtn = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType, locatorValue)
										Thread.sleep(4000)
										'Custom keyword : Boolean value for DownloadFiles Functionality Pass'
										if (CustomKeywords.'function.CheckPoints.DownloadFile'(driver, downloadBtn, fileName,
											downloadPath, result).booleanValue() == true) {
											println('DownloadFiles pass')
											'Write Pass Status in the Excel Sheet'
											CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 + getSplitedData, 'PASS' )
										}
										else if (CustomKeywords.'function.CheckPoints.DownloadFile'(driver, downloadBtn, fileName, downloadPath, result).booleanValue() == false) {
											println('DownloadFiles fail')
											'Write Fail Status in the Excel Sheet'
											CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, 'Download files option is not working properly', 'FAIL')
										}
									}
									catch (Exception e) {
										println("exceptione:$e")
										'Write in the Excel Sheet : Run Time Error for DownloadFiles Functionality'
										CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, "Runtime Error:$e", 'Error')
									}
									break;
								 case 'PageCount':
									try {
										String input = assignValue
										'Custom keyword for combining the Locator Value and Locator Type for PageCount functionality'
										By pagePath = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType, locatorValue)
										'Custom keyword : Boolean value for PageCount Functionality Pass'
										if (CustomKeywords.'function.CheckPoints.PageCount'(driver, pagePath, input, result).booleanValue() == true) {
											println('PageCount pass')
											'Write Pass Status in the Excel Sheet'
											CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 + getSplitedData, 'PASS' )
										}
										else if (CustomKeywords.'function.CheckPoints.PageCount'(driver, pagePath, input, result).booleanValue() == false) {
											println('PageCount fail')
											'Write Fail Status in the Excel Sheet'
											CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, 'Page count is not displayed properly', 'FAIL')
										}
									}
									catch (Exception e) {
										println("exceptione:$e")
										'Write in the Excel Sheet: Run Time Error for PageCount Functionality'
										CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, "Runtime Error:$e", 'Error')
									}
									break;
								  case 'Screenshot':
										String File_path = assignValue
										CustomKeywords.'function.CheckPoints.Screenshot'(driver, File_path)
										break;
								  case 'Search':
									searchAction( driver,fieldType,locatorType,locatorValue,assignValue,result,testDatas,getSplitedData)
									break
									case 'BrowserAlert':
									browserAlert(driver,fieldType,result,testDatas,getSplitedData,validateValue)
									break;
								  case 'Delay':
									if ( !( TestData.empty ) ) {
										def timeLine = Integer.parseInt(TestData)
										Thread.sleep(timeLine)
									}
									else {
									   Thread.sleep ( 1000 )
									}
									break;
								  default:
								  break;
							 }
							if ( UserActions.equals( 'End' ) && ( getSplitedData == (dataValues - 1) ) ) {
								Testcaseindex = (Testcases.getRowNumbers()) + 1
							}
							println("index:${index}")
							println("Testcases.getRowNumbers() :${Testcases.getRowNumbers()}")
							def validate=Testcases.getRowNumbers();
							println ("testDatastestDatas:${testDatas}")
							if ( testDatas == validate ){
								endPoint = 'BreakNow'
								println('Breaking statement')
							if ( UserActions.equals( 'End' ) && ( getSplitedData == (dataValues - 1) ) ) {
								Testcaseindex = (Testcases.getRowNumbers()) + 1
							}                       }
							//Testcaseindex = (Testcases.getRowNumbers() + 1)
						}
					}
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(0, getConfigData, 5, 4, 'Valid Data')
				}
				else if ((scenarioData.equalsIgnoreCase(moduleName) && UserActionData.equalsIgnoreCase(UserAction)) &&
				executionStatus.equals('NO')) {
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(0, getConfigData, 5, 4, 'Skipped in the Execution', 'Skipped')
					Testcaseindex = (Testcases.getRowNumbers() + 1)
					println("Testcaseindex:$Testcaseindex")
				}
				else {
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(0, getConfigData, 5, 4, 'Not available in the Testcase sheet', 'InvalidData')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(0, getConfigData, 5, 4, "Runtime Error:$e", 'Exception')
			}
		}
	}
}
else {
	'Write URL Fail Status in the Excel Sheet'
	CustomKeywords.'function.CheckPoints.WriteToExcelFail'(0, 1, 8, 7, 'URL is not provided in the excel sheet', 'No URL Available')

}
def searchAction( WebDriver driver,def fieldType,def locatorType,def locatorValue,def assignValue,boolean result,def testDatas,def getSplitedData) {
	try {
		String inputSearch = assignValue
		'Custom keyword for combining the Locator Value and Locator Type for Search functionality'
		By locatorSearch = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
		'Custom keyword : Boolean value for Search Functionality Pass'
		if (CustomKeywords.'function.CheckPoints.Search'(driver, locatorSearch, inputSearch,result).booleanValue() == true) {
			println('Search pass')
			'Write Pass Status in the Excel Sheet'
			CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 + getSplitedData, 'PASS')
		}
		else if (CustomKeywords.'function.CheckPoints.Search'(driver, locatorSearch, inputSearch,result).booleanValue() == false) {
			println('Search fail')
			'Write Fail Status in the Excel Sheet'
			CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, 'Search field is not working properly', 'FAIL')
		}
	}
	catch (Exception e) {
		println("exceptione:$e")
		'Write in the Excel Sheet : Run Time Error for Search Functionality'
		CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData,"Runtime Error:$e", 'Error')
	}
}
def scrollAction( WebDriver driver,def locatorType,def locatorValue,boolean result,def testDatas,def getSplitedData) {
	try {
		'Custom keyword for combining the Locator Value and Locator Type for Scroll functionality'
		By scrollType = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
		Thread.sleep(4000)
		'Custom keyword : Boolean value for Scroll Functionality Pass'
		if (CustomKeywords.'function.CheckPoints.ScrollAction'(driver, scrollType, result).booleanValue() == true) {
			println('Scroll pass')
			'Write Pass Status in the Excel Sheet'
			CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 + getSplitedData, 'PASS')
		}
		else if (CustomKeywords.'function.CheckPoints.ScrollAction'(driver, scrollType,result).booleanValue() == false) {
			println('Scroll fail')
			'Write Fail Status in the Excel Sheet'
			CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, 'Scroll is not working properly', 'FAIL')
		}
	}
	catch (Exception e) {
		println("exceptione:$e")
		'Write in the Excel Sheet : Run Time Error for Scroll Functionality'
		CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData,"Runtime Error:$e", 'Error')
	}
}
def uploadFiles( WebDriver driver,def fieldType,def locatorType,def locatorValue,def assignValue,boolean result,def testDatas,def getSplitedData,def attributeValue,def validateValue) {
	try {
		String imageName = assignValue
		'Custom keyword for combining the Locator Value and Locator Type for UploadFiles functionality'
		By locatorUpload = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
		Thread.sleep(4000)
		'Custom keyword : Boolean value for UploadFiles Functionality Pass'
		if (CustomKeywords.'function.CheckPoints.UploadFiles'(driver, locatorUpload, imageName,
			result).booleanValue() == true) {
			println('UploadFiles pass')
			'Write Pass Status in the Excel Sheet'
			CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 +getSplitedData, 'PASS')
		}
		else if (CustomKeywords.'function.CheckPoints.UploadFiles'(driver, locatorUpload,imageName, result).booleanValue() == false) {
			println('UploadFiles fail')
			'Write Fail Status in the Excel Sheet'
			CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 +getSplitedData, 'Upload files option is not working properly', 'FAIL')
		}
	}
	catch (Exception e) {
		println("exceptione:$e")
		'Write in the Excel Sheet : Run Time Error for UploadFiles Functionality'
		CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData,"Runtime Error:$e", 'Error')
	}
	
}
def browserAlert(WebDriver driver,def fieldType,boolean result,def testDatas,def getSplitedData,def validateValue) {
	 try {
		 Boolean checkAlertToAccept = WebUI.verifyAlertPresent(10)
		 if ( checkAlertToAccept == true ) {
			 if ( fieldType.equals("BrowserAcceptAlert") ) {
			 WebUI.acceptAlert()
			 'Write Pass Status in the Excel Sheet'
			 CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 + getSplitedData, 'PASS')
			 }
			 else if (fieldType.equals("BrowserDismissAlert") ) {
			 WebUI.dismissAlert()
			 'Write Pass Status in the Excel Sheet'
			 CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 +getSplitedData, 'PASS')
			 }
			 else if ( fieldType.equals("BrowserGetTextAlert") ) {
				 def actualAlertText = WebUI.getAlertText()
				 if (actualAlertText.equalsIgnoreCase(validateValue)) {
					 'Write Pass Status in the Excel Sheet'
					 CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas, 10, 11 + getSplitedData, 'PASS')
				 }
				 else {
					 'Write Fail Status in the Excel Sheet'
					 CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, "Actual Alert Text :${actualAlertText}", 'FAIL')
				 }
			 }
			 else {
				 'Write Fail Status in the Excel Sheet'
				 CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData,'Browser Alert is not displayed in the page to Accept', 'FAIL')
			 }
		 }
		 else {
			 'Write Fail Status in the Excel Sheet'
			 CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, "Browser Alert is not displayed in the page to Accept","FAIL")
		 }
	 
	 }
	 catch (Exception e) {
		 'Write in the Excel Sheet : Run Time Error for AcceptAlert Functionality'
		 CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10, 11 + getSplitedData, "Runtime Error:${e}","Error")
	 }
}
def switchtext(WebDriver driver,def fieldType,def locatorType,def locatorValue,def assignValue,boolean result,def testDatas,def getSplitedData,def attributeValue,def validateValue) {
	switch (fieldType) {
		case 'TextBox':
			try {
				Thread.sleep(1000)
				'Custom keyword for combining the Locator Value and Locator Type for Delete functionality'
				By titletxtBox = CustomKeywords.'function.CheckPoints.getLocator'(driver,locatorType, locatorValue)
				'Custom keyword : Boolean value for TextBox Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.Textbox'(driver, titletxtBox, assignValue,result).booleanValue() == true) {
					println('TextBox pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.Textbox'(driver, titletxtBox,assignValue, result).booleanValue() == false) {
					println('TextBox fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'TextBox is not working properly', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for TextBox Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'GetText':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for GetText functionality'
				By locator = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
				String gettingText = validateValue
				println("gettingText:$gettingText")
				Thread.sleep(2000)
				'Custom keyword : Boolean value for GetText Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.GetText'(driver, locator, gettingText,result).booleanValue() == true) {
					println('GetText pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.GetText'(driver, locator,
					gettingText, result).booleanValue() == false) {
					println('GetText fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Caption Text is incorrect', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for GetText Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		 case 'GetTextDynamic':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for GetText functionality'
				By locator = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
				String gettingText = validateValue
				println("gettingText:$gettingText")
				Thread.sleep(2000)
				'Custom keyword : Boolean value for GetText Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.GetText'(driver, locator,result).booleanValue() == true) {
					println('GetText pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.GetText'(driver, locator,gettingText, result).booleanValue() == false) {
					println('GetText fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Caption Text is incorrect', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for GetText Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'GetTextCompare':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for GetText functionality'
				By locator = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
				String gettingText = validateValue
				println("gettingText:$gettingText")
				Thread.sleep(2000)
				'Custom keyword : Boolean value for GetText Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.GetText'(driver, locator,result).booleanValue() == true) {
					println('GetText pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.GetText'(driver, locator,
					gettingText, result).booleanValue() == false) {
					println('GetText fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Caption Text is incorrect', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for GetText Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'ToolTip':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for ToolTip functionality'
				By tooltipLocator = CustomKeywords.'function.CheckPoints.getLocator'(driver,locatorType, locatorValue)
				String title = attributeValue
				String Expected_value = validateValue
				Thread.sleep(3000)
				'Custom keyword : Boolean value for ToolTip Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.Tooltip'(driver, tooltipLocator,title, Expected_value, result).booleanValue() == true) {
					println('ToolTip pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.Tooltip'(driver, tooltipLocator,title, Expected_value, result).booleanValue() == false) {
					println('ToolTip fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Tooltip is not working properly', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for ToolTip Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'GetValue':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for DateFormat functionality'
				By locator = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
				String expectedFormat = validateValue
				String actualFormat = attributeValue
				'Custom keyword : Boolean value for GetValue Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.DateFormat'(driver, locator, actualFormat,expectedFormat, result).booleanValue() == true) {
					println('GetValue pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				 else if (CustomKeywords.'function.CheckPoints.DateFormat'(driver, locator,actualFormat, expectedFormat, result).booleanValue() == false) {
					println('Gertvalue fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Data format is not displayed properly','FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for DateFormat Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'DisableText':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for DisableText functionality'
				By locatorElement = CustomKeywords.'function.CheckPoints.getLocator'(driver,locatorType, locatorValue)
				'Custom keyword : Boolean value for DisableText Functionality Pass'
				
				if (CustomKeywords.'function.CheckPoints.DisabledTxt'(driver,locatorElement, attributeValue,  validateValue, result).booleanValue() == true) {
					println('DisableText pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.DisabledTxt'(driver, locatorElement,
					result).booleanValue() == false) {
					println('DisableText fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Disabled Text is not working properly','FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for DisableText Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		default:
		    'Write in the Excel Sheet : Run Time Error for DisableText Functionality'
			CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Field Type value is not provided in the sheet", 'MissOut')
			break;
	}
}
def switchselect(WebDriver driver,def fieldType,def locatorType,def locatorValue,def assignValue,boolean result,def testDatas,def getSplitedData,def attributeValue,def validateValue) {
	switch (fieldType) {
		case 'DropDown':
			try {
				'Custom keyword for combining the Locator Value and Locator Type for DropDown functionality'
				By Type = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
				String inputType = assignValue
				'Custom keyword : Boolean value for DropDown Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.Dropdown'(driver, Type, inputType,result).booleanValue() == true) {
					println('DropDown pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				 else if (CustomKeywords.'function.CheckPoints.Dropdown'(driver, Type,inputType, result).booleanValue() == false) {
					println('DropDown fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'DropDown is not working properly', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for DropDown Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'CheckBox':
			try {
				String checkBoxInput = TestData
				'Custom keyword for combining the Locator Value and Locator Type for CheckBox functionality'
				By checkBox = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType,locatorValue)
				'Custom keyword : Boolean value for CheckBox Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.CheckBox'(driver, checkBox, checkBoxInput,
					result).booleanValue() == true) {
					println('CheckBox pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS' )
				}
				else if (CustomKeywords.'function.CheckPoints.CheckBox'(driver, checkBox,
					checkBoxInput, result).booleanValue() == false) {
					println('CheckBox fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'CheckBox is not working properly', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")

				'Write in the Excel Sheet : Run Time Error for CheckBox Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		case 'RadioButton':
			try {
				String radioUploadType = assignValue
				'Custom keyword for combining the Locator Value and Locator Type for RadioButton functionality'
				By radioBtnUploadType = CustomKeywords.'function.CheckPoints.getLocator'(driver, locatorType, locatorValue)
				'Custom keyword : Boolean value for RadioButton Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.RadioBtn'(driver, radioBtnUploadType,radioUploadType, result).booleanValue() == true) {
					println('RadioButton pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS' )
				}
				 else if (CustomKeywords.'function.CheckPoints.RadioBtn'(driver, radioBtnUploadType,radioUploadType, result).booleanValue() == false) {
					println('RadioButton fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Radio Button is not working properly','FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for RadioButton Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		default:
			'Write in the Excel Sheet : Run Time Error for DisableText Functionality'
			CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Field Type value is not provided in the sheet", 'MissOut')
			break;
	}
}
def switchclick(WebDriver driver,def fieldType,def locatorType,def locatorValue,def assignValue,boolean result,def testDatas,def getSplitedData,def attributeValue,def validateValue) {
	switch (fieldType) {
		case 'Button':
			try {
				println('Button selected success')
				Thread.sleep(3000)
				'Custom keyword : combining the Locator Value and Locator Type for Button Functionality'
				By buttonClick = CustomKeywords.'function.CheckPoints.getLocator'(driver,locatorType, locatorValue)
				println("buttonClick:$buttonClick")
				Thread.sleep(2000)
				'Custom keyword : Boolean value for Button Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.Click'(driver, buttonClick, result).booleanValue() == true) {
					println('Button pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,
						10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.Click'(driver, buttonClick,result).booleanValue() == false) {
					println('Button Fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Button is not working properly', 'FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for Button Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break;
		case 'Delete':
			try {
				String confirmation = assignValue
				String alertText = validateValue
				'Custom keyword : combining the Locator Value and Locator Type for Delete functionality'
				By delete_btn = CustomKeywords.'function.CheckPoints.getLocator'(driver,locatorType, locatorValue)
				'Custom keyword : Boolean value for Delete Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.Delete'(driver, delete_btn, alertText,
					confirmation, result).booleanValue() == true) {
					println('Delete pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.Delete'(driver, delete_btn,alertText, confirmation, result).booleanValue() == false) {
					println('Delete fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'Delete button is not working properly','FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for Delete Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break;
		case 'DisableButton':
			try {
				'Custom keyword : combining the Locator Value and Locator Type for Delete functionality'
				By locatorElement = CustomKeywords.'function.CheckPoints.getLocator'(driver,locatorType, locatorValue)
				'Custom keyword : Boolean value for DisableButton Functionality Pass'
				if (CustomKeywords.'function.CheckPoints.DisabledBtn'(driver, locatorElement,
					result).booleanValue() == true) {
					println('DisableButton pass')
					'Write Pass Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelPass'(1, testDatas,10, 11 + getSplitedData, 'PASS')
				}
				else if (CustomKeywords.'function.CheckPoints.DisabledBtn'(driver, locatorElement,result).booleanValue() == false) {
					println('DisableButton fail')
					'Write Fail Status in the Excel Sheet'
					CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas,10, 11 + getSplitedData, 'DisabledButton is not working properly','FAIL')
				}
			}
			catch (Exception e) {
				println("exceptione:$e")
				'Write in the Excel Sheet : Run Time Error for DisableButton Functionality'
				CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Runtime Error:$e", 'Error')
			}
			break
		default:
			'Write in the Excel Sheet : Run Time Error for DisableText Functionality'
			CustomKeywords.'function.CheckPoints.WriteToExcelFail'(1, testDatas, 10,11 + getSplitedData, "Field Type value is not provided in the sheet", 'MissOut')
			break;
	}
}