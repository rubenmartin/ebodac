package org.motechproject.ebodac.uitest.page;

import org.motechproject.uitest.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrimeFollowAndBoostReportPage extends AbstractBasePage {

    private static final String INNER_HTML = "innerHTML";
    private static final String REPORT_BOTTOM_DIV = "//*[@id='pageReportTable_left']/div";
    private static final String NO_RECORDS_TO_VIEW = "No records to view";
    public static final String URL_PATH = "/home#/mds/dataBrowser";
    static final By TABLE = By.xpath("//table[@class='ui-jqgrid-htable']");
    static final By LOOKUP = By.id("lookupDialogButton");
    static final By DROPDOWN = By.xpath("//button[@data-toggle='dropdown']");
    static final By VISIT_TYPE_AND_ACTUAL_VISIT_DATE_LOOKUP = By.linkText("Find By Visit Type And Actual Visit Date");
    static final By VISIT_TYPE_AND_SITENAME_LOOKUP = By.linkText("Find By Visit Type And Site Name");
    static final By VISIT_TYPE_PLANNED_VISIT_DATE_AND_SITENAME_LOOKUP = By
            .linkText("Find By Visit Type Planned Visit Date And Site Name");
    static final By VISIT_TYPE_ACTUAL_VISIT_DATE_AND_STAGE_ID = By
            .linkText("Find By Visit Type Actual Visit Date And Stage Id");
    static final By VISIT_TYPE_AND_STAGE_ID = By.linkText("Find By Visit Type And Stage Id");
    static final By VISIT_TYPE_PLANNED_VISIT_DATE_AND_STAGE_ID = By
            .linkText("Find By Visit Type Planned Visit Date And Stage Id");
    static final By VISIT_TYPE_ACTUAL_VISIT_DATE_AND_SITENAME_LOOKUP = By
            .linkText("Find By Visit Type Actual Visit Date And Site Name");
    static final By VISIT_TYPE_FIELD = By.xpath("//select[@ng-model='lookupBy[buildLookupFieldName(field)]']");
    static final By PRIME_VACCINATION_FIRST_FOLLOW_UP_VISIT_OPTION = By
            .xpath("//option[text()='Prime Vaccination First Follow-up visit']");
    static final By BOOST_VACCINATION_VISIT_OPTION = By.xpath("//option[text()='Boost Vaccination Day']");
    static final int TIMEOUT = 2000;

    public PrimeFollowAndBoostReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLookupVisible() {
        try {
            if (findElement(LOOKUP) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void openLookup() throws InterruptedException {
        clickWhenVisible(LOOKUP);
    }

    public boolean areLookupsPresent() throws InterruptedException {
        Thread.sleep(TIMEOUT);
        try {
            if (findElement(VISIT_TYPE_AND_ACTUAL_VISIT_DATE_LOOKUP) == null) {
                return false;
            }
            if (findElement(VISIT_TYPE_AND_SITENAME_LOOKUP) == null) {
                return false;
            }
            if (findElement(VISIT_TYPE_PLANNED_VISIT_DATE_AND_SITENAME_LOOKUP) == null) {
                return false;
            }
            if (findElement(VISIT_TYPE_ACTUAL_VISIT_DATE_AND_STAGE_ID) == null) {
                return false;
            }
            if (findElement(VISIT_TYPE_AND_STAGE_ID) == null) {
                return false;
            }
            if (findElement(VISIT_TYPE_PLANNED_VISIT_DATE_AND_STAGE_ID) == null) {
                return false;
            }
            if (findElement(VISIT_TYPE_ACTUAL_VISIT_DATE_AND_SITENAME_LOOKUP) == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void openByVisittypeAndActualVisitDateLookup() throws InterruptedException {
        clickWhenVisible(VISIT_TYPE_AND_ACTUAL_VISIT_DATE_LOOKUP);
    }

    public boolean islookupOpen() {
        try {
            if (findElement(VISIT_TYPE_FIELD) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllVisitsAvailable() {
        try {
            if (findElement(PRIME_VACCINATION_FIRST_FOLLOW_UP_VISIT_OPTION) == null) {
                return false;
            }
            if (findElement(BOOST_VACCINATION_VISIT_OPTION) == null) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void openVisitType() throws InterruptedException {
        clickWhenVisible(VISIT_TYPE_FIELD);
    }

    @Override
    public String expectedUrlPath() {
        return getServerURL() + URL_PATH;
    }

    @Override
    public void goToPage() {

    }

    public void openDropdown() throws InterruptedException {
        clickWhenVisible(DROPDOWN);
    }

    public boolean existTable() {
        boolean status = false;
        try {
            if (!findElement(TABLE).getAttribute(INNER_HTML).isEmpty()) {
                status = true;
            }
        } catch (Exception e) {
            getLogger().error("existTable - Exception - Reason : " + e.getLocalizedMessage(), e);
        }
        return status;
    }

    public boolean isReportEmpty() {
        boolean status = false;
        try {
            if (findElement(By.xpath(REPORT_BOTTOM_DIV)) != null
                    && findElement(By.xpath(REPORT_BOTTOM_DIV)).getAttribute(INNER_HTML).contains(NO_RECORDS_TO_VIEW)) {
                status = true;
            }
        } catch (Exception e) {
            getLogger().error("isReportEmpty -  Exception . Reason : " + e.getLocalizedMessage(), e);
            status = false;
        }
        return status;
    }

    public void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            getLogger().error("sleep -  Exception . Reason : " + e.getLocalizedMessage(), e);

        }

    }
}
