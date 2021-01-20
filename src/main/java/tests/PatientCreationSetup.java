package tests;

import org.testng.annotations.Test;
import pages.BaseSetupPage;
import pages.HomePage;
import pages.OasisNursePage;
import pages.PatientProfilePage;

import java.util.HashMap;
import java.util.Map;

public class PatientCreationSetup extends BaseSetupPage {
    private Map<String, String> patientData = new HashMap<String, String>();
    private final String firstName = "QA_Automation";
    private String lastName = "Bot_";
    private String city = "San Jose";
    private String state = " CA";
    private String street = "300 Park Ave";
    private String zipcode = "94087";
    private String day = "1";
    private String month = "Jan";
    private String year = "1970";
    private String phone = "408-408-4088";
    private String ssn = "111223333";
    private String gender = "1";
    private String physician = "auto_physician_LN_1, auto_physician_FN_1";
    private String referrer = "auto_referrerLN, auto_referrer_FN";
    private String rNValue = "auto_nurse_lastName_1, auto_nurse_firstName_1 (RN)";
    private String agency = "DeVero, Inc.";

    @Test
    public void testMethod() {
        String referralDate = getCurrentDate("MM/dd/yyyy");
        lastName = lastName.concat(getTimestamp());

        //1. Login
        login();

        //2.Click Create new Patient Link
        HomePage homePage = new HomePage();
        homePage.selectNewPatient();

        //3. Edit Fields
        switchToIframeIfPresent();
        PatientProfilePage patientProfileForm = new PatientProfilePage();
        patientData.put(PatientProfilePage.PatientInformation.FIRST_NAME.getName(),firstName);
        patientData.put(PatientProfilePage.PatientInformation.LAST_NAME.getName(), lastName);
        patientData.put(PatientProfilePage.PatientInformation.CITY.getName(),city);
        patientData.put(PatientProfilePage.PatientInformation.STATE.getName(),state);
        patientData.put(PatientProfilePage.PatientInformation.ZIPCODE.getName(),zipcode);
        patientData.put(PatientProfilePage.PatientInformation.STREET.getName(),street);
        patientData.put(PatientProfilePage.PatientInformation.SOC_SEC.getName(),ssn);
        patientData.put(PatientProfilePage.PatientInformation.PHONE.getName(),phone);
        patientData.put(PatientProfilePage.PatientInformation.AGENCY_DROPDOWN.getName(),agency);
//        patientData.put(PatientProfilePage.PatientInformation.SOC_DATE.getName(), referralDate);
//        patientData.put(PatientProfilePage.PatientInformation.REFERRERL_DATE.getName(), referralDate);

        patientData.put(PatientProfilePage.PatientInformation.BIRTH_DATE.getName(),day);
        patientData.put(PatientProfilePage.PatientInformation.BIRTH_MONTH.getName(), month);
        patientData.put(PatientProfilePage.PatientInformation.BIRTH_YEAR.getName(), year);
        patientData.put(PatientProfilePage.PatientInformation.SEX.getName(), gender);

        patientData.put(PatientProfilePage.PatientInformation.PRIMARY_REFERRING_PHYSICIAN_DROPDOWN.getName(),physician);
        patientData.put(PatientProfilePage.PatientInformation.REFERRER_DROPDOWN.getName(),referrer);
        patientData.put(PatientProfilePage.PatientInformation.RN.getName(), rNValue);

        patientProfileForm.editField(patientData);
        patientProfileForm.savePatient();
        switchBackFromIframeIfPresent();

        //4.create Form with all fields
        homePage = new HomePage();
//        homePage.clickLinkUsingAbsoluteTextOnUI3("Patient Profile");
//        switchToIframeIfPresent();
//        OasisNursePage nurseSOC = new OasisNursePage();
//        nurseSOC.fillTextFields();
//        nurseSOC.saveForm();

    }
}
