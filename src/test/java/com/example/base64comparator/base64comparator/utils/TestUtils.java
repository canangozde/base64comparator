package com.example.base64comparator.base64comparator.utils;

import com.example.base64comparator.base64comparator.constants.TestConstants;
import com.example.base64comparator.base64comparator.controller.dto.Base64Dto;
import com.example.base64comparator.base64comparator.enums.DiffEnum;
import com.example.base64comparator.base64comparator.model.Base64Model;
import com.example.base64comparator.base64comparator.model.Difference;
import com.example.base64comparator.base64comparator.model.DifferenceModel;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static Base64Dto createLeftData() {
        return new Base64Dto(TestConstants.leftDataBase64);
    }

    public static Base64Dto createRightBase64Dto() {
        return new Base64Dto(TestConstants.rightDataBase64);
    }

    public static Base64Model createDataModel() {
        return Base64Model.builder()
                .id(TestConstants.anyId)
                .leftData(TestConstants.leftDataDecoded)
                .rightData(TestConstants.rightDataDecoded)
                .build();
    }

    public static Base64Model createDataModelWithLeftData() {
        return Base64Model.builder()
                .id(TestConstants.anyId)
                .leftData(TestConstants.leftDataDecoded)
                .build();
    }

    public static Base64Model createDataModelWithRightData() {
        return Base64Model.builder()
                .id(TestConstants.anyId)
                .rightData(TestConstants.rightDataDecoded)
                .build();
    }

    public static DifferenceModel createDiffModel(String leftData, String rightData, DiffEnum difEnum) {
        return DifferenceModel.builder()
                .id(TestConstants.anyId)
                .leftData(leftData)
                .rightData(rightData)
                .diffMessage(difEnum.getVal())
                .differences(createDifferences())
                .build();
    }

    public static List<Difference> createDifferences() {
        return Arrays.asList(Difference.builder().offset(5).length(4).build());
    }

}
