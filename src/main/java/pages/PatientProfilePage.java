package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;

import static pages.BaseSetupPage.webDriver;

public class PatientProfilePage extends BasePage {

    private String SAVE_BUTTON_LOCATOR = "//input[contains(@value,'Save')]";
    private static final By MAIN_LOCATOR = By.xpath("//td[contains(.,'PATIENT PROFILE')]");
    private static final String MAIN_NAME = "Patient profile page";
    private static final String EDITABLE_COMBO_BOX_IMG = "//input[contains(@class, 'libraryNameText') and contains(@name, '%1$s')]/following-sibling::img";
    private static final String LIBRARY_VALUE_TEMPLATE = "//div[@class='name' and contains(.,'%1$s')]";
    private static final String LIBRARY_TEMPLATE = "//input[contains(@class, 'libraryNameText') and contains(@name, '%1$s')]";

    /**
     * Parameterized constructor
     *
     * @param formLocator
     * @param title
     */
    public PatientProfilePage() {
        super(MAIN_LOCATOR, MAIN_NAME);
    }

    /**
     * Templates for patient information
     */
    public enum PatientInformation {
        FIRST_NAME("value(M0040FirstName)"),
        LAST_NAME("value(M0040LastName)"),
        MIDDLE_INITIAL("value(M0040MiddleInit)"),
        SOC_DATE_V4("value(StartOfCareDate)"),
        //SOC_DATE("value(StartOfCareDate)"),
        FIRST_VISIT_DATE("value(FirstVisitDate)"),
        SOC_DATE("value(StartOfCareDate)"),
        HOSPICE_SOC_DATE_V4("value(HospiceStartOfCareDate)"),
        HOSPICE_SOC_DATE("value(HospiceSocDate)"),
        MR("value(MedicalRecordNo)"),
        BENEFIT_NUM("value(BenefitNumber)"),
        BENEFIT_NUM_HIDDEN("hiddenHospiceSocOrBenefitNumber"),
        HOSPICE_SOC_REASON_V4("value(HospiceStartOfCareReason)"),
        HOSPICE_SOC_REASON("value(HospiceSocReason)"),
        PRIMARY_INSURANCE("value(PrimaryInsuranceID)"),
        SECONDARY_INSURANCE("value(SecondaryInsuranceID)"),
        AGENCY("value(CPCAgencyID)"),
        STATUS("value(CPCStatusID)"),
        PRIMARY_INSURANCE_AUTH("value(PrimaryAuthRequired)"),
        SECONDARY_INSURANCE_AUTH("value(SecondaryAuthRequired)"),
        STREET("value(M0040Address)"),
        REFERRER_FN("value(reffname)"),
        REFERRER_LN("value(reflname)"),
        PRIMARY_REFERRING_PHYSICIAN("value(PhysicianID)"),
        PRIMARY_PHYSICIAN("value(PhysicianID)"),
        CITY("value(M0040City)"),
        STATE("value(M0050)"),
        ZIPCODE("value(M0060ZipCode)"),
        BIRTH_MONTH("value(M0066Month)"),
        BIRTH_DATE("value(M0066Day)"),
        BIRTH_YEAR("value(M0066Year)"),
        SEX("value(Gender)"),
        MEDICAL_RECORD_NO("value(MedicalRecordNo)"),
        OTHER_PHYSICIAN("value(PhysicianOtherID)"),
        CERTIFYING_PHYSICIAN("value(CertifyingPhysicianID)"),
        PHONE("value(M0040Phone)"),
        SOC_SEC("value(M0064SSN)"),
        PT("value(PTID)"),
        RN("value(RNID)"),
        HHA("value(HHAID)"),
        CM("value(CaseManagerID)"),
        VISIT_DATE("value(VisitDate)"),
        LANGUAGE("languageSearchText_value(LanguageID)"),
        RACE("raceSearchText_value(RaceID)"),
        PRIORITY_DISASTER_CODE("priorityDisasterCodeSearchText_value(PriorityDisasterCodeID)"),
        REFERRER_DROPDOWN("referrerSearchText_value(ReferrerID)"),
        REFERRAL_SOURCE("facilitySearchText_value(FacilityID)"),
        PRIMARY_REFERRING_PHYSICIAN_DROPDOWN("physicianSearchText_value(PhysicianID)"),
        CERTIFYING_PHYSICIAN_DROPDOWN("physicianSearchText_value(CertifyingPhysicianID)"),
        OTHER_PHYSICIAN_DROPDOWN("physicianSearchText_value(PhysicianOtherID)"),
        PRIMARY_INSURANCE_DROPDOWN("insuranceSearchText_value(PrimaryInsuranceID)"),
        SECONDARY_INSURANCE_DROPDOWN("insuranceSearchText_value(SecondaryInsuranceID)"),
        AGENCY_DROPDOWN("agencySearchText_value(CPCAgencyID)"),
        VETERAN("value(cpc_Veteran_Rdo)"),
        REFERRERL_DATE("value(ReferralDate)"),
        ADMISSION_SOURCE_DROPDOWN("value(AdmissionSourceID)"),
        COUNTY("value(CountyID)"),
        OASIS_SUBMITTER("value(OasisSubmitterID)"),
        OASIS_SUBMITTER_1("oasisSubmitterSearchText_value(OasisSubmitterID)"),
        DISCHARGE_REASON("value(DischargeReasonID)"),
        NON_SKILLED_HHA("value(HHA_Non_Skilled_Services_Only)"),
        CASE_MANAGER("value(CaseManagerID)"),
        EMERGENCY_CONTACT("value(EmergencyContact)"),
        ADMISSION_TYPE("value(selectedAdmissionTypeId)"),
        DC_DATE("value(cpc_dcDate_Txt)"),
        AGE("value(Age)");




        private String name;

        /**
         * Define PatientInformation
         * @param name - value of attribute
         */
        private PatientInformation(String name) {
            this.name = name;
        }

        /**
         * Get name of attribute
         * @return name
         */
        public String getName() {
            return name;
        }
    }

    /**
     * To Edit the TextFields in Patient Profile Form Page
     *
     * @param params
     */
    public void editTextFields(Map<String, String> params) {

        for (Map.Entry<String, String> entry : params.entrySet()) {
            WebElement txTextField = (webDriver.findElement(By.xpath(String.format("//input[contains(@name, '%1$s')]", entry.getKey()))));
            txTextField.sendKeys(entry.getValue());

        }
    }

    /**
     * To Save the Patient
     */
    public void savePatient() {
        WebElement saveButton = (webDriver.findElement(By.xpath(String.format(SAVE_BUTTON_LOCATOR))));
        saveButton.isDisplayed();
        saveButton.click();
    }

    public void editField(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (webDriver.findElements(By.xpath(String.format("//input[contains(@name, '%1$s')]", name))).size() != 0) {
                WebElement txTextField = webDriver.findElement(By.xpath(String.format("//input[contains(@name, '%1$s')]", name)));
                if (txTextField.getAttribute("class").equals("libraryNameText text-field")) {//searchable combobox

                    WebElement txtSelect = webDriver.findElement(By.xpath(String.format(LIBRARY_TEMPLATE, name)));
                    txtSelect.sendKeys(value);
                    WebDriverWait wait = new WebDriverWait(webDriver, 60);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LIBRARY_VALUE_TEMPLATE, value))));
                    WebElement lblValue = webDriver.findElement(By.xpath(String.format(LIBRARY_VALUE_TEMPLATE, value)));
                    clickViaAction(lblValue);

                } else {
                    txTextField.sendKeys(value);
                }
            } else {
                Select cb = new Select(webDriver.findElement(By.xpath(String.format("//select[contains(@name, '%1$s')]", name))));
                WebElement select = webDriver.findElement(By.xpath(String.format("//select[contains(@name, '%1$s')]", name)));
                if (select.isDisplayed()) {
                    cb.selectByVisibleText(value);
                } else {
                    Assert.assertTrue(false, "Field is not present");
                }
            }


        }
    }

}
