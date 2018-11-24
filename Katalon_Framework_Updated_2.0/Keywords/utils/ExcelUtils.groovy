package utils

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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
public class ExcelUtils {
	public static FileInputStream ExcelFile;
	public static HSSFWorkbook ExcelWBook;
	public static HSSFSheet ExcelWSheet;
	public static HSSFRow Row;
	public static HSSFCell Cell;
	public static HSSFCell Cell1;
	public static HSSFCell Cell2;
	public static HSSFCellStyle my_style;
	@Keyword
	public static void setExcelFile(String path,String Sheetname) throws Exception
	{
		ExcelFile=new FileInputStream(path);
		ExcelWBook=new HSSFWorkbook(ExcelFile);
	}
	@Keyword
	public static int getRowCount(String SheetName)
	{
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number=ExcelWSheet.getLastRowNum()+1;
		return number;
	}

	/*public static int getRowCount(String SheetName)
	{
		  ExcelWSheet = ExcelWBook.getSheet(SheetName);
		  //int number = ExcelWSheet.getPhysicalNumberOfRows();
		  int number=1000;
		  //System.out.println("Physical no.of rows:"+number);
		  int count = 0;
		  boolean notEmpty = true;
		  int emptyCount = 0;
		  while (count < number && emptyCount <= 5 )
		  {
			  
			   if (ExcelWSheet.getRow(count) != null)
			   {
					++count;
					//System.out.println("Count value is:"+count);
				   emptyCount = 0;
			   }
			   else {
					   ++count;
					++emptyCount;
					//System.out.println("Count value with empty"+count);
				   //System.out.println("empty count value:"+emptyCount);
			   }
		  }
		  
		  if(emptyCount>5)
		  {
			  count=count-emptyCount;
		  }
		  
		  return count;
	}*/
	@Keyword
	public static String getCellData(int rowNum,int ColNum) throws Exception
	{
		Cell=ExcelWSheet.getRow(rowNum).getCell(ColNum);
		String cellData=CellToString(Cell);
		return cellData;
	}
	@Keyword
	public static String CellToString(HSSFCell cell)
	{
		  String result = "";
		  try
		  {
			  if(cell==null)
			  {
				  result="";
			  }
			  else if(cell.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING)
			  {
				  result=cell.getStringCellValue();
			  }
			  else if(cell.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC)
			  {
				  result  = String.valueOf(cell.getNumericCellValue());
				  return result;
			  }
			  else if(cell.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK)
			  {
				  result="";
			  }
			  else if(cell.getCellType()==org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN)
			  {
				  result=String.valueOf(cell.getBooleanCellValue());
			  }
			  else
			  {
				  System.out.println("Out of world.");
				  throw new RuntimeException("Out of world.");
			  }
		   }
		 
		  catch (Exception e)
		  {
			  System.out.println("Exception:"+e);
		  }
		return result;
	  }
	@Keyword
	public static void setCellData(String Status, String Result,int RowNum, int StatusColNum,int ResultColNum) throws Exception
	{
		try
		{
			my_style = ExcelWBook.createCellStyle();
			  Row  = ExcelWSheet.getRow(RowNum);
			  Cell1 = Row.getCell(StatusColNum,Row.RETURN_BLANK_AS_NULL);
			  Cell2 = Row.getCell(ResultColNum,Row.RETURN_BLANK_AS_NULL);
			if (Cell1 == null)
			{
				Cell1 = Row.createCell(StatusColNum);
				Cell1.setCellValue(Status);
			}
			else
			{
				Cell1.setCellValue(Status);
			}
			
			if (Cell2 == null)
			{
				Cell2 = Row.createCell(ResultColNum);
				Cell2.setCellValue(Result);
			}
			else
			{
				Cell2.setCellValue(Result);
			}
			
			if(Status=="Pass")
			{
				my_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
				my_style.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
				my_style.setFillBackgroundColor(new HSSFColor.GREEN().getIndex());
				Cell1.setCellStyle(my_style);
			}
			else if(Status=="Fail")
			{
				my_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
				my_style.setFillForegroundColor(new HSSFColor.RED().getIndex());
				my_style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
				Cell1.setCellStyle(my_style);
			}
			else
			{
				my_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
				my_style.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
				my_style.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
				Cell1.setCellStyle(my_style);
			}
			
			FileOutputStream fileOut = new FileOutputStream('config.Constants.constantValue'().Path_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch(Exception e)
		{
			throw (e);
		}
	}

}
