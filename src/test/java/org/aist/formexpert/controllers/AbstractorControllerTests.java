package org.aist.formexpert.controllers;

import static org.aist.formexpert.models.SupportedAbstractions.getSupported;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.aist.formexpert.models.AbstractionRequest;
import org.aist.formexpert.services.AbstractorService;
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

    private final AbstractionRequest request = new AbstractionRequest("label", "value");

    @Test
    public void onGetAbstractionRequest_AbstractorServiceIsCalled() {
        //arrange

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
}
