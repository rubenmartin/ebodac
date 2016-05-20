package org.motechproject.ebodac.uitest.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motech.page.LoginPage;
import org.motech.test.TestBase;
import org.motechproject.ebodac.uitest.page.BookingAppPage;
import org.motechproject.ebodac.uitest.page.BookingAppScreeningPage;
import org.motechproject.ebodac.uitest.page.HomePage;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class BookingApplicationScreeningCreationUiTest extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    private BookingAppPage bookingAppPage;
    private BookingAppScreeningPage bookingAppScreeningPage;
    private String user;
    private String password;

    @Before
    public void setUp() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        bookingAppPage = new BookingAppPage(driver);
        bookingAppScreeningPage = new BookingAppScreeningPage(driver);
        user = properties.getUserName();
        password = properties.getPassword();
        if (homePage.expectedUrlPath() != currentPage().urlPath()) {
            loginPage.login(user , password);
        }
    }

    @Test
    public void bookingApplicationScreeningCreationTest() throws InterruptedException {
        ArrayList<Date> dates = new ArrayList<>();
        homePage.clickModules();
        homePage.openBookingAppModule();
        bookingAppPage.openScreening();
        bookingAppScreeningPage.changeFilterTo("Date range");
        String bookingId = bookingAppScreeningPage.bookScreeningVisit().replace(". ", "");
        assertTrue(bookingAppScreeningPage.bookingIdExists(bookingId));
        bookingAppScreeningPage.changeFilterTo("Today");
        dates.add(new Date());
        assertTrue(bookingAppScreeningPage.isFirstBookingOK(dates));
        bookingAppScreeningPage.changeFilterTo("Tomorrow");
        dates.remove(0);
        dates.add(new Date(new Date().getDate() + 1));
        assertTrue(bookingAppScreeningPage.isFirstBookingOK(dates));
        bookingAppScreeningPage.changeFilterTo("Day after tomorrow");
        dates.remove(0);
        dates.add(new Date(new Date().getDate() + 2));
        assertTrue(bookingAppScreeningPage.isFirstBookingOK(dates));
        bookingAppScreeningPage.changeFilterTo("Next 3 days");
        dates.remove(0);
        dates.add(new Date(new Date().getDate()));
        dates.add(new Date(new Date().getDate() + 1));
        dates.add(new Date(new Date().getDate() + 2));
        assertTrue(bookingAppScreeningPage.isFirstBookingOK(dates));
        bookingAppScreeningPage.changeFilterTo("Next 7 days");
        dates.add(new Date(new Date().getDate() + 3));
        dates.add(new Date(new Date().getDate() + 4));
        dates.add(new Date(new Date().getDate() + 5));
        dates.add(new Date(new Date().getDate() + 6));
        assertTrue(bookingAppScreeningPage.isFirstBookingOK(dates));
        bookingAppScreeningPage.changeFilterTo("Date range");
        bookingAppScreeningPage.setDate();
        bookingAppScreeningPage.exportToPDF();
        bookingAppScreeningPage.exportToXLS();
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(10000);
        loginPage.logOut();
    }
}