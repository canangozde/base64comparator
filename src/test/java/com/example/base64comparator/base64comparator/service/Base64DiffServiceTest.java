package com.example.base64comparator.base64comparator.service;

import com.example.base64comparator.base64comparator.Util.DiffCalculatorUtil;
import com.example.base64comparator.base64comparator.constants.TestConstants;
import com.example.base64comparator.base64comparator.data.jpa.Base64Repository;
import com.example.base64comparator.base64comparator.enums.DiffEnum;
import com.example.base64comparator.base64comparator.model.Base64Model;
import com.example.base64comparator.base64comparator.model.DifferenceModel;
import com.example.base64comparator.base64comparator.utils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class Base64DiffServiceTest {

    @Mock
    Base64Repository base64RepositoryMock;
    @Mock
    DiffCalculatorUtil diffCalculatorUtilMock;
    @InjectMocks
    Base64DiffService underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveDecodedBase64InputData() {
        //GIVEN
        Base64Model leftData = TestUtils.createDataModelWithLeftData();
        Base64Model wholeData = TestUtils.createDataModel();
        Mockito.doReturn(TestUtils.createDataModelWithRightData()).when(base64RepositoryMock)
                .findOneById(TestConstants.anyId);
        Mockito.doReturn(wholeData).when(base64RepositoryMock).save(any(Base64Model.class));
        //WHEN
        Base64Model base64ModelResponse = underTest.save(leftData);
        //THEN
        Mockito.verify(base64RepositoryMock, Mockito.times(1)).findOneById(Mockito.anyString());
        Mockito.verify(base64RepositoryMock, Mockito.times(1)).save(any(Base64Model.class));
        Assert.assertEquals(wholeData.getLeftData(), base64ModelResponse.getLeftData());
    }

    @Test
    public void shouldReturnDifferencesWithGivenSameSizedDifferentContextLeftAndRightData() {
        //GIVEN
        Base64Model base64Model = TestUtils.createDataModel();
        Mockito.doReturn(base64Model).when(base64RepositoryMock).findOneById(any(String.class));
        Mockito.doReturn(TestUtils.createDifferences()).when(diffCalculatorUtilMock)
                .calculateJsonDifference(base64Model.getLeftData(), base64Model.getRightData());
        //WHEN
        DifferenceModel differenceModelResponse = underTest.findDiff(TestConstants.anyId);
        //THEN
        Mockito.verify(base64RepositoryMock, Mockito.times(1)).findOneById(Mockito.anyString());
        Assert.assertEquals(base64Model.getId(), differenceModelResponse.getId());
        Assert.assertEquals(base64Model.getRightData(), differenceModelResponse.getRightData());
        Assert.assertEquals(base64Model.getLeftData(), differenceModelResponse.getLeftData());
        Assert.assertEquals(DiffEnum.DIFFERENT.getVal(), differenceModelResponse.getDiffMessage());
        Assert.assertEquals(4, differenceModelResponse.getDifferences().get(0).getLength());
        Assert.assertEquals(5, differenceModelResponse.getDifferences().get(0).getOffset());
    }
}
