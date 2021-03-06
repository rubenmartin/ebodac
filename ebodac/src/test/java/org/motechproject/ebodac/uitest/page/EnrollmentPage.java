package org.motechproject.ebodac.uitest.page;

import org.motechproject.uitest.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static java.lang.Thread.sleep;

public class EnrollmentPage extends AbstractBasePage {
    private static final String BUTTON_NG_CLICK_ENROLL = "//button[@ng-click='enroll(\"";
    private static final String BUTTON_NG_CLICK_UNENROLL = "//button[@ng-click='unenroll(\"";
    private static final String ID_ENROLLMENT_TABLE = "//*[@id='enrollmentTable']/tbody/tr[@id='1']/td[2]";
    private static final String PARTICIPANT_WAS_UNENROLLED_SUCCESSFULLY = "Participant was unenrolled successfully.";
    private static final String INNERHTML = "innerHTML";
    private static final String TITLE_ADVANCE_ENROLLMENT = "//*[@id='main-content']/div/div/h4";
    private static final String EBODAC_ENROLLMENT_ADVANCED = "EBODAC - Enrollment Advanced";
    public static final String URL_PATH = "home#/ebodac/enrollment";
    static final By ACTION = By.xpath("//table[@id='enrollmentTable']/tbody/tr[2]/td[6]/button");
    static final By POPUP_OK = By.id("popup_ok");
    static final By POPUP_CONTENT = By.id("popup_content");
    static final By ENROLLMENT_RECORD = By.xpath("//table[@id='enrollmentTable']/tbody/tr[2]");
    static final By ENROLLMENT_ADVANCED = By.id("enrollmentAdvanced");
    static final int LAST_ENROLL = 2;
    static final int TIMEOUT_500MLSEC = 500;
    static final int TIMEOUT_2SEC = 2000;
    static final By AMMOUNT_OF_PAGES = By.id("sp_1_pageEnrollmentTable");
    static final By NUBMER_OF_ACTUAL_PAGE = By.className("ui-paging-info");
    static final By POPUP_MESSAGE = By.id("popup_message");
    static final By NEXT_PAGE_BUTTON = By.id("next_pageEnrollmentTable");
    static final By AMMOUNT_OF_RESULTS = By.xpath("//select[@title='Records per Page']");
    static final By FIRST_PARTICIPANT_STATUS = By.xpath("//*[@id=\"1\"]/td[5]");
    static final By FIRST_PARTICIPANT_ID = By.xpath("//*[@id=\"1\"]/td[2]");
    private static final long TIMEOUT_5SEC = 5000;

    public EnrollmentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String expectedUrlPath() {
        return getServerURL() + URL_PATH;
    }

    @Override
    public void goToPage() {

    }

    public String getPopupMessage() throws InterruptedException {
        sleep(TIMEOUT_2SEC);
        return findElement(POPUP_MESSAGE).getText();
    }

    public void clickAction() throws InterruptedException {
        sleep(TIMEOUT_500MLSEC);
        clickWhenVisible(ACTION);
    }

    public void clickOK() throws InterruptedException {
        sleep(TIMEOUT_500MLSEC);
        clickWhenVisible(POPUP_OK);
    }

    public boolean error() {
        try {
            WebElement popUpContent = findElement(POPUP_CONTENT);
            if (popUpContent.getText()
                    .contains("Error occurred during enrolling Participant: Cannot enroll Participant")
                    || popUpContent.getText()
                            .contains("Error occurred during unenrolling Participant: Cannot unenroll Participant")) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean enrolled() {
        try {
            return findElement(POPUP_CONTENT).getText().contains("Participant was enrolled successfully.");
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean unenrolled() {
        try {
            return findElement(POPUP_CONTENT).getText().contains(PARTICIPANT_WAS_UNENROLLED_SUCCESSFULLY);
        } catch (Exception ex) {
            return false;
        }
    }

    public void nextAction() throws InterruptedException {
        do {

            try {
                actionSecond();
                clickOn(POPUP_OK);
            } catch (Exception e) {
                clickOn(POPUP_OK);
            }
        } while (error());
    }

    public void actionSecond() {
        WebElement action = findElement(
                By.xpath("//table[@id='enrollmentTable']/tbody/tr[" + LAST_ENROLL + "]/td[6]/button"));
        action.click();
    }

    public boolean enrollmentDetailEnabled() throws InterruptedException {
        boolean status = false;
        try {
            sleep(TIMEOUT_2SEC);
            status = findElement(By.xpath(TITLE_ADVANCE_ENROLLMENT)).getAttribute(INNERHTML)
                    .startsWith(EBODAC_ENROLLMENT_ADVANCED);
        } catch (NullPointerException e) {
            status = false;
            getLogger().error(" enrollmentDetailEnabled : NPE " + e.getLocalizedMessage(), e);

        } catch (Exception e) {
            status = false;
            getLogger().error(" enrollmentDetailEnabled : Exception . Reason : " + e.getLocalizedMessage(), e);

        }
        return status;
    }

    public boolean checkEnroll() throws InterruptedException {
        sleep(TIMEOUT_2SEC);
        findElement(By.xpath("//tr[@id='1']/td[2]")).click();
        try {
            findElement(By.cssSelector("td[title=\"Boost Vaccination Third Follow-up visit\"]")).click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getAmmountPagesOfTable() {
        return findElement(AMMOUNT_OF_PAGES).getText();
    }

    public String getNumberOfActualPage() {
        return findElement(NUBMER_OF_ACTUAL_PAGE).getText();
    }

    public void goToNextPageInTable() throws InterruptedException {
        clickWhenVisible(NEXT_PAGE_BUTTON);
    }

    public void changeAmmountOfResultsShownOnPage() throws InterruptedException {
        Select dropdown = new Select(findElement(AMMOUNT_OF_RESULTS));
        dropdown.selectByVisibleText("10");
    }

    public String getStatusOfFirstParticipantEnrollment() {
        return findElement(FIRST_PARTICIPANT_STATUS).getAttribute("title");
    }

    public String getParticipantId() {
        return findElement(FIRST_PARTICIPANT_ID).getText();
    }

    public void clickOnButtonToEnrollParticipant(String idOfParticipant) {
        findElement(By.xpath(BUTTON_NG_CLICK_ENROLL + idOfParticipant + "\")']")).click();
    }

    public void clickOnButtonToUnenrollParticipant(String idOfParticipant) {
        findElement(By.xpath(BUTTON_NG_CLICK_UNENROLL + idOfParticipant + "\")']")).click();
    }


    public void clickOnFirstRow() {
        try {
            sleep(TIMEOUT_5SEC);
            findElement(By.xpath(ID_ENROLLMENT_TABLE)).click();
        } catch (Exception e) {
            getLogger().error("clickOnFirstRow - Exception . Reason :" + e.getLocalizedMessage(), e);
        }

    }



    public boolean checkIfParticipantWasEnrolledOrUnenrolledSuccessfully() {
        boolean status = false;
        String popup = findElement(POPUP_MESSAGE).getText();
        if ((PARTICIPANT_WAS_UNENROLLED_SUCCESSFULLY.equals(popup)) || ("Participant was enrolled successfully.".equals(popup))) {
            status = true;
        } else {
            status = false;
        }
        
        return status;
    }

}
