package org.aist.formexpert.services;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore
// This has a fairly limited number of free uses so they shouldn't just be run all the time.
public class AddressValidatorServiceTest {
    private AddressValidatorService addressValidatorService = new AddressValidatorService();

    @Test
    public void testInvalidAddressReturnsFalse() {
        Assert.assertFalse(addressValidatorService.validateAddress("15 Knole Ln, Dennis MA"));
    }

    @Test
    public void testValidAddressReturnsTrue() {
        Assert.assertTrue(addressValidatorService.validateAddress("1020 Old Bass River Rd, Dennis, MA"));
    }
}
