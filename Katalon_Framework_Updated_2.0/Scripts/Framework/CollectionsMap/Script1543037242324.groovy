import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
def TestCase = findTestData('Data Files/Framework/TestCase')
def Configuration = findTestData('Data Files/Framework/Configuration')
ArrayList<?> Testcase_numbers_index = new ArrayList<>();
ArrayList<?> Testcase_numbers_values = new ArrayList<>();
ArrayList<?> config_numbers_values = new ArrayList<>();
ArrayList<?> config_numbers_index = new ArrayList<>();
ArrayList<?> testcase_index = new ArrayList<>();
ArrayList<?> testcase_value = new ArrayList<>();
ArrayList<?> config_useraction_values = new ArrayList<>();
ArrayList<?> config_useraction_index = new ArrayList<>();

//----------------------------To remove the blank data in the module name in Testcase sheet and set the index of Same--------------------------------------//
Map<Integer, String> hmap = new TreeMap<>();
Map<Integer, String> hmapuserAction = new TreeMap<>();
for (int getTestCaseData = 1; getTestCaseData <= TestCase.getRowNumbers(); getTestCaseData++) {
	def scenarioData = TestCase.getValue('Modules', getTestCaseData);
	def useractionData = TestCase.getValue('UserAction', getTestCaseData);
	if(!(scenarioData.empty)) {
		hmap.put(getTestCaseData, scenarioData);
		hmapuserAction.put(getTestCaseData, useractionData);
	}
	   //values.add(scenarioData);
}
while(((hmap.values().remove("")) || (hmap.values().remove(null)))) {
	
}
Set<Map.Entry<Integer, String>> mappinghmap = hmap.entrySet();
Set<Map.Entry<Integer, String>> mappinghmapusrAct = hmapuserAction.entrySet();
println("Set of Keys and Values using entrySet() : ${mappinghmap}");
for(Map.Entry<Integer, String> entries:mappinghmap) {
	println ("entries${entries}")
	Testcase_numbers_values.add(entries.getValue());
	Testcase_numbers_index.add(entries.getKey());
}
for(Map.Entry<Integer, String> entriesuseract:mappinghmapusrAct) {
	println ("entries${entriesuseract}")
	testcase_value.add(entriesuseract.getValue());
	testcase_index.add(entriesuseract.getKey());
}
System.out.println("Last numbers_values : "+Testcase_numbers_values);
System.out.println("Last numbers_index : "+Testcase_numbers_index);
System.out.println("Last numbers_values : "+testcase_value);
System.out.println("Last numbers_index : "+testcase_index);
System.out.println("hmap : "+hmap);
System.out.println("hmapuserAction : "+hmapuserAction);
def sizeCountValue = Testcase_numbers_values.size()
def sizeCountIndex = Testcase_numbers_index.size()
def testcasesizeCountValue = testcase_value.size()
def testcasesizeCountIndex = testcase_index.size()		
println ( "sizeCountValue :${sizeCountValue}")
println ( "sizeCountIndex :${sizeCountIndex}")
println ( "testcasesizeCountValue :${testcasesizeCountValue}")
println ( "testcasesizeCountIndex :${testcasesizeCountIndex}")
//------------------------To get the value and index of modules in Configuration Sheet for the execution status as Yes----------------------------------//
//ArrayList<String> configvalues=new ArrayList<String>();
Map<Integer, String> hmapconfig = new TreeMap<>();
Map<Integer, String> hmapconfiguserAction = new TreeMap<>();
for (int getConfigData = 1; getConfigData <= Configuration.getRowNumbers(); getConfigData++) {
	 def scenarioData = Configuration.getValue('Module_Name', getConfigData)
	 println("scenarioData:$scenarioData")
	'Getting the User Action Data from Configuration sheet'
	def UserActionData = Configuration.getValue('UserAction', getConfigData)
	println("UserActionData:$UserActionData")
	'Getting the ExecutionStatus Data from Configuration sheet'
	def executionStatus = Configuration.getValue('ExecutionStatus', getConfigData)
	if(executionStatus.equals("YES")) {
		hmapconfig.put(getConfigData, scenarioData);
		hmapconfiguserAction.put(getConfigData, UserActionData);
	}
	else if(executionStatus.equals("NO")) {
		CustomKeywords.'function.CheckPoints.WriteToExcelFail'(0, getConfigData, 5, 4, 'Skipped in the Execution', 'Skipped')
		println ("executionStatus contains of 'NO' ${executionStatus}")
	}
}
while(((hmapconfig.values().remove("")) || (hmapconfig.values().remove(null)))) {
}
Set<Map.Entry<Integer, String>> mappingconfighmap = hmapconfig.entrySet();
Set<Map.Entry<Integer, String>> mappingconfusracthmap = hmapconfiguserAction.entrySet();
println("Set of Keys and Values using entrySet() : ${mappingconfighmap}");
for(Map.Entry<Integer, String> mappingentries:mappingconfighmap){
	println ("entries${mappingentries}")
	config_numbers_values.add(mappingentries.getValue());
	config_numbers_index.add(mappingentries.getKey());
}
for(Map.Entry<Integer, String> mappingusractentries:mappingconfusracthmap) {
	println ("entries${mappingusractentries}")
	config_useraction_values.add(mappingusractentries.getValue());
	config_useraction_index.add(mappingusractentries.getKey());
}
System.out.println("GlobalVariable.config_numbers_value : "+config_numbers_values);
System.out.println("config_numbers_index : "+config_numbers_index);
System.out.println("config_useraction_values : "+config_useraction_values);
System.out.println("config_useraction_index : "+config_useraction_index);
System.out.println("hmapconfig : "+hmapconfig);
System.out.println("hmapconfiguserAction : "+hmapconfiguserAction);
def configsizeCountValue = config_numbers_values.size()
def configsizeCountIndex = config_numbers_index.size()
def useractionconfigsizeCountValue = config_useraction_values.size()
def useractionconfigsizeCountIndex = config_useraction_index.size()
println ( "configsizeCountValue :${configsizeCountValue}")
println ( "configsizeCountIndex :${configsizeCountIndex}")
println ( "useractionconfigsizeCountValue :${useractionconfigsizeCountValue}")
println ( "useractionconfigsizeCountIndex :${useractionconfigsizeCountIndex}")
//----------------------------Comparing the Values in the configuration sheet with Test case sheet----------------------------------
for (int i = 0; i < configsizeCountValue; i++) {
	for (int j = 0; j < sizeCountValue; j++) {
		if ((config_numbers_values.get(i)).equals((Testcase_numbers_values.get(j)))) {
			if((config_useraction_values.get(i)).equals((testcase_value.get(j)))) {
				GlobalVariable.ActualTCindex.add(Testcase_numbers_index.get(j))
				GlobalVariable.ActualTCmodulevalues.add(Testcase_numbers_values.get(j))
				GlobalVariable.ActualTCuseractvalues.add(testcase_value.get(j))
				GlobalVariable.ActualConfigindex.add(config_numbers_index.get(i))
				GlobalVariable.ActualConfigmodulevalues.add(config_numbers_values.get(i))
				GlobalVariable.ActualConfiguseractvalues.add(config_useraction_values.get(i))
			}
		}
	}
}
println ("GlobalVariable.ActualTCindex:${GlobalVariable.ActualTCindex}");
println ("GlobalVariable.ActualTCmodulevalues:${GlobalVariable.ActualTCmodulevalues}");
println ("GlobalVariable.ActualTCuseractvalues:${GlobalVariable.ActualTCuseractvalues}");
println ("GlobalVariable.ActualConfigindex:${GlobalVariable.ActualConfigindex}");
println ("GlobalVariable.ActualConfigmodulevalues:${GlobalVariable.ActualConfigmodulevalues}");
println ("GlobalVariable.ActualConfiguseractvalues:${GlobalVariable.ActualConfiguseractvalues}");
def ActualindexCount = GlobalVariable.ActualTCindex.size()
def ActualmodulevalueCount = GlobalVariable.ActualTCmodulevalues.size()
def ActualuseractvalueCount = GlobalVariable.ActualTCuseractvalues.size()
println ( "ActualindexCount :${ActualindexCount}")
println ( "ActualmodulevalueCount :${ActualmodulevalueCount}")
println ( "ActualuseractvalueCount :${ActualuseractvalueCount}")