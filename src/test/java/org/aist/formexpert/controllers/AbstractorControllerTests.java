package org.aist.formexpert.controllers;

import static org.aist.formexpert.models.SupportedAbstractions.getSupported;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.aist.formexpert.models.AbstractionRequest;
import org.aist.formexpert.services.AbstractorService;
import org.aist.formexpert.services.AddressValidatorService;
import org.aist.formexpert.services.DateClassifierService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;



@RunWith(MockitoJUnitRunner.class)
public class AbstractorControllerTests {
    @InjectMocks
    private AbstractorController abstractorController = new AbstractorController();

    @Mock
    private AbstractorService abstractorService;

    @Mock
    private DateClassifierService dateClassifierService;

    @Mock
    private AddressValidatorService addressValidatorService;

    @Test
    public void onGetAbstractionRequest_AbstractorServiceIsCalled() {
        //arrange
        final AbstractionRequest request = new AbstractionRequest("label", "value");

        //act
        abstractorController.getAbstraction(request);

        //assert
        verify(abstractorService, times(1)).getAbstraction(request);
    }

    @Test
    public void onGetSupportedAbstractions_TheSupportedAbstractionsListIsReturned() {
        //arrange

        //act
        ResponseEntity<List<String>> supportedList = abstractorController.getSupportedAbstractions();

        //verify
        Assert.assertEquals(supportedList.getBody(), getSupported());
    }

    @Test
    public void onGetAbstractionForDate_IsValidIsCalledForTheValue() {
        //arrange
        final AbstractionRequest dateRequest = new AbstractionRequest("date", "10/11/23");

        //act
        abstractorController.getAbstraction(dateRequest);

        //verify
        verify(dateClassifierService, times(1)).isValidDate(dateRequest.getValue());
    }

    @Test
    public void onGetAbstractionForBirthday_IsValidIsCalledForTheValue() {
        //arrange
        final AbstractionRequest dateRequest = new AbstractionRequest("birthday", "10/11/23");

        //act
        abstractorController.getAbstraction(dateRequest);

        //verify
        verify(dateClassifierService, times(1)).isValidDate(dateRequest.getValue());
    }

    @Test
    public void onGetAbstractionForAddress_IsValidIsCalledForTheValue() {
        //arrange
        final AbstractionRequest addressRequest = new AbstractionRequest("address", "value");

        //act
        abstractorController.getAbstraction(addressRequest);

        //verify
        verify(addressValidatorService, times(1)).isValid(addressRequest.getValue());
    }
}
