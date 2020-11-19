import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import utils.DataFactory

WebUI.verifyElementVisible( findTestObject('Object Repository/Booking_Page/h2_Make Appointment') )

DataFactory dataFactory = new DataFactory()

String facility , Program , visitDate, randomComment

for(def row=1; row <= findTestData('Bookings').getRowNumbers(); row ++)
{
	 facility =  findTestData('Bookings').getValue("Facility", row)
	 
	 Program = findTestData('Bookings').getValue("Program", row)
	 
	 CustomKeywords.'utils.WebKeywords.SelectDropdownitemByText'( findTestObject('Object Repository/Booking_Page/Combo_facility') , facility)
	 
	 WebUI.click( CustomKeywords.'utils.WebKeywords.getCheckboxByLabel'("Apply for") )
	 
	 WebUI.click( CustomKeywords.'utils.WebKeywords.getRadioButtonByLabel'(Program) )
	  
	 CustomKeywords.'utils.WebKeywords.selectDay'()
	 
	 WebUI.click( findTestObject('Object Repository/Booking_Page/txt_comment')  )
	 
	 visitDate = WebUI.getAttribute( findTestObject('Object Repository/Booking_Page/visit_date') , 'value')
	 
	 WebUI.clearText(findTestObject('Object Repository/Booking_Page/txt_comment')  )
	 
	 randomComment = dataFactory.generateRandomComment()
	 
	 WebUI.sendKeys( findTestObject('Object Repository/Booking_Page/txt_comment') , randomComment)
	 
	 WebUI.click( findTestObject('Object Repository/Booking_Page/btn_Book Appointment')  )
	 WebUI.callTestCase(findTestCase('Test Cases/ConfirmBooking'), [('facility'):facility,('program'):Program,('visitDate'):visitDate,('comment'):randomComment])
	 
}