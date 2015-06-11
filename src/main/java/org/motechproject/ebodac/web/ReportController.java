package org.motechproject.ebodac.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.motechproject.ebodac.service.EbodacService;
import org.motechproject.mds.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private EbodacService ebodacService;

    @RequestMapping(value = "/generateReports", method = RequestMethod.POST)
    @PreAuthorize(Constants.Roles.HAS_DATA_ACCESS)
    @ResponseBody
    public ResponseEntity<String> generateReports(@RequestBody String startDate) {

        try {
            DateTime date = DateTime.parse(startDate, DateTimeFormat.mediumDate());
            ebodacService.generateDailyReport(date);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid date format", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error("Fatal error raised during creating reports", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
