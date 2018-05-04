package org.aist.formexpert.services;

import java.util.Date;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class DateClassifierServiceTests {
    private DateClassifierService dateClassifierService =  new DateClassifierService();

    @Test
    @Parameters({
            "13/12/2000, false",
            "14/12/2000, false",
            "00/1/2000, false",
            "10/0/1919, false",
            "12/12/, false",
            "12/00/2, false",
            "/12/19i9, false",
            "/12/1919, false",
            "12/1/2000, true",
            "12/31/2000, true",
            "11/30/1231, true",
            "10/31/20013, true",
            "9/30/2123, true",
            "09/30/2100, true",
            "08/31/203, true",
            "07/31/20, true",
            "06/30/2, true",
            "05/31/2005, true",
            "04/30/2020, true",
            "03/31/2032, true",
            "02/28/2010, true",
            "01/31/2000, true",
            "12/32/2002, false",
            "11/31/2030, false",
            "10/32/2000, false",
            "09/31/2000, false",
            "08/32/2000, false",
            "07/32/2000, false",
            "06/31/2000, false",
            "05/32/2000, false",
            "04/31/2000, false",
            "03/32/2000, false",
            "02/30/2000, false",
            "01/32/2000, false",
            "02/29/2004, true",
            "02/29/2008, true",
            "02/29/2000, true",
            "02/29/2003, false",
            "02/29/1205, false",
            "02/29/1900, false",
    })
    public void testValidDate(String date, boolean expected) {
        Assert.assertEquals(dateClassifierService.isValidDate(date), expected);
    }

    @Test
    @Parameters({
            "12/12/1990, 12/12/2008, 18",
            "12/12/1990, 12/12/2009, 19",
            "12/12/1990, 12/11/2008, 17",
            "12/12/1990, 11/20/2008, 17",
            "12/12/1980, 12/12/2008, 28",
            "1/1/1990, 1/1/2019, 29",
            "1/1/2019, 1/1/1990, -29",
    })
    public void testDiffCalculator(String from, String to, int expected) {
        Date first = dateClassifierService.getDate(from);
        Date last = dateClassifierService.getDate(to);
        Assert.assertEquals(dateClassifierService.getDiffYears(first, last), expected);
    }

    @Test
    public void testIsOver18() {
        Assert.assertEquals(dateClassifierService.isOver18("1/1/1990"), true);
    }

    @Test
    public void testIsOver21() {
        Assert.assertEquals(dateClassifierService.isOver21("1/1/1990"), true);
    }
}
