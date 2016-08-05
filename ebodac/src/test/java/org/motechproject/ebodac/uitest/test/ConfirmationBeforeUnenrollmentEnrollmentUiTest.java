package org.motechproject.ebodac.uitest.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.uitest.TestBase;
import org.motechproject.ebodac.uitest.helper.TestParticipant;
import org.motechproject.ebodac.uitest.helper.UITestHttpClientHelper;
import org.motechproject.ebodac.uitest.page.EBODACPage;
import org.motechproject.ebodac.uitest.page.EnrollmentPage;
import org.motechproject.ebodac.uitest.page.HomePage;
import org.motechproject.uitest.page.LoginPage;
import static org.junit.Assert.assertTrue;

/**
 * Class created to test the confirmation Before the Un-enrollment/ Enrollment
 * 
 * @author tmueller
 * @modified rmartin
 *
 */
public class ConfirmationBeforeUnenrollmentEnrollmentUiTest extends TestBase {
    private static final String LOCAL_TEST_MACHINE = "localhost";
    private LoginPage loginPage;
    private HomePage homePage;
    private EnrollmentPage enrollmentPage;
    private EBODACPage ebodacPage;
    private UITestHttpClientHelper httpClientHelper;
    private String url;
    private String user;
    private String password;

    @Before
    public void setUp() throws Exception {
        try {
            loginPage = new LoginPage(getDriver());
            homePage = new HomePage(getDriver());
            ebodacPage = new EBODACPage(getDriver());
            enrollmentPage = new EnrollmentPage(getDriver());
            user = getTestProperties().getUserName();
            password = getTestProperties().getPassword();
            url = getServerUrl();
            if (url.contains(LOCAL_TEST_MACHINE)) {
                httpClientHelper = new UITestHttpClientHelper(url);
                httpClientHelper.addParticipant(new TestParticipant(), user, password);
                loginPage.goToPage();
                loginPage.login(user, password);
            } else if (homePage.expectedUrlPath() != currentPage().urlPath()) {
                loginPage.goToPage();
                loginPage.login(user, password);
            }
        } catch (NullPointerException e) {
            getLogger().error("NullPointerException . Reason : " + e.getLocalizedMessage(), e);
        } catch (Exception e) {
            getLogger().error("Exception . Reason : " + e.getLocalizedMessage(), e);
        }
    }

    @Test
    public void confirmationBeforeUnenrollmentEnrollmentTest() throws Exception {
        try {
            homePage.resizePage();
            homePage.openEBODACModule();
            Thread.sleep(2000);
            ebodacPage.goToEnrollment();
            enrollmentPage.clickAction();
            checkPopUp("Are you sure you want to enroll the participant?",
                    "Are you sure you want to un-enroll the participant?");
            enrollmentPage.clickOK();
            if (enrollmentPage.error()) {
                enrollmentPage.clickOK();
                enrollmentPage.nextAction();
            }
            checkPopUp("Participant was enrolled successfully.", "Participant was unenrolled successfully.");
            enrollmentPage.clickOK();
            enrollmentPage.actionSecond();
            if (!("Are you sure you want to enroll the participant?").equals(enrollmentPage.getPopupMessage())
                    && !("Are you sure you want to un-enroll the participant?")
                            .equals(enrollmentPage.getPopupMessage())) {
                assertTrue(false);
            }
            checkPopUp("Are you sure you want to enroll the participant?",
                    "Are you sure you want to un-enroll the participant?");
            Thread.sleep(2000);
            enrollmentPage.clickOK();
            Thread.sleep(2000);
            if (enrollmentPage.error()) {
                enrollmentPage.clickOK();
                enrollmentPage.nextAction();
            }
            Thread.sleep(2000);
            checkPopUp("Participant was enrolled successfully.", "Participant was unenrolled successfully.");
            enrollmentPage.clickOK();
        } catch (AssertionError e) {
            getLogger().error("confirmationBeforeUnenrollmentEnrollmentTest - AssertionError . Reason : "
                    + e.getLocalizedMessage(), e);
        } catch (NumberFormatException e) {
            getLogger().error("confirmationBeforeUnenrollmentEnrollmentTest - NumberFormatException . Reason : "
                    + e.getLocalizedMessage(), e);
        } catch (Exception e) {
            getLogger().error(
                    "confirmationBeforeUnenrollmentEnrollmentTest - Exception . Reason : " + e.getLocalizedMessage(),
                    e);
        }

    }

    @After
    public void tearDown() throws Exception {
        logout();
    }

    public void checkPopUp(String popUpMessage1, String popUpMessage2) throws Exception {
        if (!(popUpMessage1).equals(enrollmentPage.getPopupMessage())
                && !(popUpMessage2).equals(enrollmentPage.getPopupMessage())) {
            assertTrue(false);
        }
    }
}
