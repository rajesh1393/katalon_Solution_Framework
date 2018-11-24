
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import org.openqa.selenium.WebDriver

import org.openqa.selenium.By

import java.lang.String

import java.lang.Integer

import org.apache.poi.hssf.usermodel.HSSFCell


def static "function.CheckPoints.DateFormat"(
    	WebDriver driver	
     , 	By locator	
     , 	String actualFormat	
     , 	String expectedFormat	
     , 	boolean result	) {
    (new function.CheckPoints()).DateFormat(
        	driver
         , 	locator
         , 	actualFormat
         , 	expectedFormat
         , 	result)
}

def static "function.CheckPoints.Delete"(
    	WebDriver driver	
     , 	By delete_btn	
     , 	String alertText	
     , 	String confirmation	
     , 	boolean result	) {
    (new function.CheckPoints()).Delete(
        	driver
         , 	delete_btn
         , 	alertText
         , 	confirmation
         , 	result)
}

def static "function.CheckPoints.confirmationPopup_Accept"(
    	WebDriver driver	
     , 	By locatorAccept	) {
    (new function.CheckPoints()).confirmationPopup_Accept(
        	driver
         , 	locatorAccept)
}

def static "function.CheckPoints.confirmationPopup_Dismiss"(
    	WebDriver driver	
     , 	By locatorDismiss	) {
    (new function.CheckPoints()).confirmationPopup_Dismiss(
        	driver
         , 	locatorDismiss)
}

def static "function.CheckPoints.Popup_gettext"(
    	WebDriver driver	
     , 	By locatorGetValue	
     , 	String expectedInput	) {
    (new function.CheckPoints()).Popup_gettext(
        	driver
         , 	locatorGetValue
         , 	expectedInput)
}

def static "function.CheckPoints.CharacterValidation"(
    	WebDriver driver	
     , 	By firstField	
     , 	By clickBtn	
     , 	String validName	
     , 	String invalidName	) {
    (new function.CheckPoints()).CharacterValidation(
        	driver
         , 	firstField
         , 	clickBtn
         , 	validName
         , 	invalidName)
}

def static "function.CheckPoints.CheckBox"(
    	WebDriver driver	
     , 	By checkBox	
     , 	String checkBoxInput	
     , 	boolean result	) {
    (new function.CheckPoints()).CheckBox(
        	driver
         , 	checkBox
         , 	checkBoxInput
         , 	result)
}

def static "function.CheckPoints.Click"(
    	WebDriver driver	
     , 	By buttonClick	
     , 	boolean result	) {
    (new function.CheckPoints()).Click(
        	driver
         , 	buttonClick
         , 	result)
}

def static "function.CheckPoints.DisabledBtn"(
    	WebDriver driver	
     , 	By locator	
     , 	String actualFormat	
     , 	String expectedFormat	
     , 	boolean result	) {
    (new function.CheckPoints()).DisabledBtn(
        	driver
         , 	locator
         , 	actualFormat
         , 	expectedFormat
         , 	result)
}

def static "function.CheckPoints.DownloadFile"(
    	WebDriver driver	
     , 	By downloadBtn	
     , 	String fileName	
     , 	String downloadPath	
     , 	boolean result	) {
    (new function.CheckPoints()).DownloadFile(
        	driver
         , 	downloadBtn
         , 	fileName
         , 	downloadPath
         , 	result)
}

def static "function.CheckPoints.Dropdown"(
    	WebDriver driver	
     , 	By locator	
     , 	String input	
     , 	boolean result	) {
    (new function.CheckPoints()).Dropdown(
        	driver
         , 	locator
         , 	input
         , 	result)
}

def static "function.CheckPoints.FocusElement"(
    	WebDriver driver	
     , 	String attributeValue	
     , 	String expectValue	) {
    (new function.CheckPoints()).FocusElement(
        	driver
         , 	attributeValue
         , 	expectValue)
}

def static "function.CheckPoints.PageCount"(
    	WebDriver driver	
     , 	By pagePath	
     , 	String input	
     , 	boolean result	) {
    (new function.CheckPoints()).PageCount(
        	driver
         , 	pagePath
         , 	input
         , 	result)
}

def static "function.CheckPoints.RadioBtn"(
    	WebDriver driver	
     , 	By radioBtn	
     , 	String radioInput	
     , 	boolean result	) {
    (new function.CheckPoints()).RadioBtn(
        	driver
         , 	radioBtn
         , 	radioInput
         , 	result)
}

def static "function.CheckPoints.Screenshot"(
    	WebDriver webdriver	
     , 	String DestfilePath	) {
    (new function.CheckPoints()).Screenshot(
        	webdriver
         , 	DestfilePath)
}

def static "function.CheckPoints.ScrollAction"(
    	WebDriver driver	
     , 	By Scroll_locator	
     , 	boolean result	) {
    (new function.CheckPoints()).ScrollAction(
        	driver
         , 	Scroll_locator
         , 	result)
}

def static "function.CheckPoints.Search"(
    	WebDriver driver	
     , 	By locatorSearch	
     , 	String inputSearch	
     , 	boolean result	) {
    (new function.CheckPoints()).Search(
        	driver
         , 	locatorSearch
         , 	inputSearch
         , 	result)
}

def static "function.CheckPoints.Textbox"(
    	WebDriver driver	
     , 	By locatortxtBox	
     , 	String inputtext	
     , 	boolean result	) {
    (new function.CheckPoints()).Textbox(
        	driver
         , 	locatortxtBox
         , 	inputtext
         , 	result)
}

def static "function.CheckPoints.Tooltip"(
    	WebDriver driver	
     , 	By tooltip_element	
     , 	String title	
     , 	String expectedValue	
     , 	boolean result	) {
    (new function.CheckPoints()).Tooltip(
        	driver
         , 	tooltip_element
         , 	title
         , 	expectedValue
         , 	result)
}

def static "function.CheckPoints.UploadFiles"(
    	WebDriver driver	
     , 	By locatorUpload	
     , 	String imageName	
     , 	boolean result	) {
    (new function.CheckPoints()).UploadFiles(
        	driver
         , 	locatorUpload
         , 	imageName
         , 	result)
}

def static "function.CheckPoints.getLocator"(
    	WebDriver driver	
     , 	String attribute	
     , 	String attrval	) {
    (new function.CheckPoints()).getLocator(
        	driver
         , 	attribute
         , 	attrval)
}

def static "function.CheckPoints.DisabledTxt"(
    	WebDriver driver	
     , 	By locator	
     , 	String actualFormat	
     , 	String expectedFormat	
     , 	boolean result	) {
    (new function.CheckPoints()).DisabledTxt(
        	driver
         , 	locator
         , 	actualFormat
         , 	expectedFormat
         , 	result)
}

def static "function.CheckPoints.GetText"(
    	WebDriver driver	
     , 	By locator	
     , 	String gettingText	
     , 	boolean result	) {
    (new function.CheckPoints()).GetText(
        	driver
         , 	locator
         , 	gettingText
         , 	result)
}

def static "function.CheckPoints.GetTextDynamic"(
    	WebDriver driver	
     , 	By locator	
     , 	boolean result	) {
    (new function.CheckPoints()).GetTextDynamic(
        	driver
         , 	locator
         , 	result)
}

def static "function.CheckPoints.GetTextCompare"(
    	WebDriver driver	
     , 	By locator	
     , 	boolean result	) {
    (new function.CheckPoints()).GetTextCompare(
        	driver
         , 	locator
         , 	result)
}

def static "function.CheckPoints.WriteToExcelPass"(
    	Integer sheetCount	
     , 	Integer xcelRow	
     , 	Integer xcelCol1	
     , 	Integer xcelCol	
     , 	String xcelValue	) {
    (new function.CheckPoints()).WriteToExcelPass(
        	sheetCount
         , 	xcelRow
         , 	xcelCol1
         , 	xcelCol
         , 	xcelValue)
}

def static "function.CheckPoints.WriteToExcelFail"(
    	Integer sheetCount	
     , 	Integer xcelRow	
     , 	Integer xcelCol1	
     , 	Integer xcelCol2	
     , 	String xcelValue	
     , 	String xcelReason	) {
    (new function.CheckPoints()).WriteToExcelFail(
        	sheetCount
         , 	xcelRow
         , 	xcelCol1
         , 	xcelCol2
         , 	xcelValue
         , 	xcelReason)
}

def static "function.CheckPoints.splitData"(
    	int Testcaseindex	
     , 	int DataLength	) {
    (new function.CheckPoints()).splitData(
        	Testcaseindex
         , 	DataLength)
}

def static "utils.ExcelUtils.setExcelFile"(
    	String path	
     , 	String Sheetname	) {
    (new utils.ExcelUtils()).setExcelFile(
        	path
         , 	Sheetname)
}

def static "utils.ExcelUtils.getRowCount"(
    	String SheetName	) {
    (new utils.ExcelUtils()).getRowCount(
        	SheetName)
}

def static "utils.ExcelUtils.getCellData"(
    	int rowNum	
     , 	int ColNum	) {
    (new utils.ExcelUtils()).getCellData(
        	rowNum
         , 	ColNum)
}

def static "utils.ExcelUtils.CellToString"(
    	HSSFCell cell	) {
    (new utils.ExcelUtils()).CellToString(
        	cell)
}

def static "utils.ExcelUtils.setCellData"(
    	String Status	
     , 	String Result	
     , 	int RowNum	
     , 	int StatusColNum	
     , 	int ResultColNum	) {
    (new utils.ExcelUtils()).setCellData(
        	Status
         , 	Result
         , 	RowNum
         , 	StatusColNum
         , 	ResultColNum)
}

def static "config.Constants.constantValue"() {
    (new config.Constants()).constantValue()
}
