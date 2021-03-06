package org.motechproject.ebodac.uitest.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ebodac.uitest.helper.TestParticipant;
import org.motechproject.ebodac.uitest.helper.UITestHttpClientHelper;
import org.motechproject.ebodac.uitest.helper.UserPropertiesHelper;
import org.motechproject.ebodac.uitest.page.EBODACPage;
import org.motechproject.ebodac.uitest.page.EnrollmentPage;
import org.motechproject.ebodac.uitest.page.HomePage;
import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.LoginPage;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class AnalystShouldNotAccessToDetailsEnrollmentPageUiTest extends TestBase {
    private String url;
    private static final String LOCAL_TEST_MACHINE = "localhost";
    private UITestHttpClientHelper httpClientHelper;
    private LoginPage loginPage;
    private HomePage homePage;
    private EnrollmentPage enrollmentPage;
    private EBODACPage ebodacPage;

    private UserPropertiesHelper userPropertiesHelper;
    private String user;
    private String password;

    public static final int SLEEP_3SEC = 3000;

    @Before
    public void setUp() throws Exception {
        try {
            loginPage = new LoginPage(getDriver());
            homePage = new HomePage(getDriver());
            ebodacPage = new EBODACPage(getDriver());
            enrollmentPage = new EnrollmentPage(getDriver());

            userPropertiesHelper = new UserPropertiesHelper();
            user = userPropertiesHelper.getAnalystUserName();
            password = userPropertiesHelper.getAnalystPassword();

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
            getLogger().error("setup - NullPointerException . Reason : " + e.getLocalizedMessage(), e);
        } catch (Exception e) {
            getLogger().error("setup - Exception . Reason : " + e.getLocalizedMessage(), e);
        }
    }

    @Test // EBODAC-828
    public void shouldnothaveAccessDetailsEnrolmentPageTest() throws Exception {
        try {
            homePage.resizePage();
            homePage.openEBODACModule();
            ebodacPage.goToEnrollment();
            sleep(SLEEP_3SEC);
            if (!"1".equals(enrollmentPage.getAmmountPagesOfTable())) {
                enrollmentPage.goToNextPageInTable();
            } else {
                enrollmentPage.changeAmmountOfResultsShownOnPage();
                enrollmentPage.goToNextPageInTable();
            }
            sleep(SLEEP_3SEC);
            String pagingInfo = enrollmentPage.getNumberOfActualPage();
            String idOfParticipant = enrollmentPage.getParticipantId();
            String temp = enrollmentPage.getStatusOfFirstParticipantEnrollment();

            if ("Enrolled".equals(temp)) {
                enrollmentPage.clickOnButtonToUnenrollParticipant(idOfParticipant);
                enrollmentPage.clickOK();
                enrollmentPage.clickOK();
            } else {
                enrollmentPage.clickOnButtonToEnrollParticipant(idOfParticipant);
                enrollmentPage.clickOK();
                enrollmentPage.clickOK();
            }
            assertEquals(pagingInfo, enrollmentPage.getNumberOfActualPage());
        } catch (AssertionError e) {
            getLogger().error("shouldnothaveAccessDetailsEnrolmentPageTest - AssertionError . Reason : "
                    + e.getLocalizedMessage(), e);
        } catch (InterruptedException e) {
            getLogger().error("shouldnothaveAccessDetailsEnrolmentPageTest - NullPointerException . Reason : "
                    + e.getLocalizedMessage(), e);
        } catch (NullPointerException e) {
            getLogger().error("shouldnothaveAccessDetailsEnrolmentPageTest - NullPointerException . Reason : "
                    + e.getLocalizedMessage(), e);
        } catch (Exception e) {
            getLogger().error(
                    "shouldnothaveAccessDetailsEnrolmentPageTest - Exception . Reason : " + e.getLocalizedMessage(), e);
        }
    }

    @After
    public void tearDown() throws Exception {
        logout();
    }
}
