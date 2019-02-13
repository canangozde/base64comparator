package com.example.base64comparator.base64comparator.controller;

import com.example.base64comparator.base64comparator.Util.Base64Util;
import com.example.base64comparator.base64comparator.constants.TestConstants;
import com.example.base64comparator.base64comparator.enums.DiffEnum;
import com.example.base64comparator.base64comparator.model.Base64Model;
import com.example.base64comparator.base64comparator.model.DifferenceModel;
import com.example.base64comparator.base64comparator.service.Base64DiffService;
import com.example.base64comparator.base64comparator.utils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Base64DiffControllerTest {

    @Mock
    private Base64DiffService base64DiffServiceMock;

    @Mock
    private Base64Util base64UtilMock;

    @InjectMocks
    private Base64DiffController underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUploadLeftDataWithGivenBase64EncodedInput() {
        //GIVEN
        Mockito.doReturn(TestUtils.createDataModel()).when(base64DiffServiceMock)
                .save(Mockito.any(Base64Model.class));
        Mockito.doReturn(TestConstants.leftDataDecoded).when(base64UtilMock).base64decode(TestConstants.leftDataBase64);
        //WHEN
        ResponseEntity<Base64Model> base64ModelResponse = underTest
                .uploadLeft(TestConstants.anyId, TestUtils.createLeftData());
        //THEN
        Mockito.verify(base64DiffServiceMock, Mockito.times(1)).save(Mockito.any(Base64Model.class));
        Assert.assertEquals(HttpStatus.CREATED, base64ModelResponse.getStatusCode());
        Assert.assertEquals(TestConstants.leftDataDecoded,base64ModelResponse.getBody().getLeftData());
    }

    @Test
    public void shouldUploadRightDataWithGivenBase64EncodedInput() {
        //GIVEN
        Mockito.doReturn(TestUtils.createDataModel()).when(base64DiffServiceMock)
                .save(Mockito.any(Base64Model.class));
        Mockito.doReturn(TestConstants.rightDataDecoded).when(base64UtilMock)
                .base64decode(TestConstants.rightDataBase64);
        //WHEN
        ResponseEntity<Base64Model> base64ModelResponse = underTest
                .uploadRight(TestConstants.anyId, TestUtils.createRightBase64Dto());
        //THEN
        Mockito.verify(base64DiffServiceMock, Mockito.times(1)).save(Mockito.any(Base64Model.class));
        Assert.assertEquals(HttpStatus.CREATED, base64ModelResponse.getStatusCode());
        Assert.assertEquals(TestConstants.rightDataDecoded,base64ModelResponse.getBody().getRightData());
    }

    @Test
    public void shouldFindDiffWithGivenUploadedLeftAndRightInput() {
        //GIVEN
        Mockito.doReturn(TestUtils.createDiffModel(TestConstants.leftDataBase64, TestConstants.rightDataBase64,
                DiffEnum.DIFFERENT)).when(base64DiffServiceMock).findDiff(TestConstants.anyId);

        //WHEN
        ResponseEntity<DifferenceModel> differenceModelResponse = underTest.findDiff(TestConstants.anyId);
        //THEN
        Mockito.verify(base64DiffServiceMock, Mockito.times(1)).findDiff(TestConstants.anyId);
        Assert.assertEquals(HttpStatus.OK, differenceModelResponse.getStatusCode());
        Assert.assertEquals(TestConstants.anyId, differenceModelResponse.getBody().getId());
        Assert.assertEquals(TestConstants.leftDataBase64, differenceModelResponse.getBody().getLeftData());
        Assert.assertEquals(TestConstants.rightDataBase64, differenceModelResponse.getBody().getRightData());
        Assert.assertEquals(DiffEnum.DIFFERENT.getVal(), differenceModelResponse.getBody().getDiffMessage());
    }
}
