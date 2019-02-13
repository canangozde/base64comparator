package com.example.base64comparator.base64comparator.service;

import com.example.base64comparator.base64comparator.Util.DiffCalculatorUtil;
import com.example.base64comparator.base64comparator.data.jpa.Base64Repository;
import com.example.base64comparator.base64comparator.enums.DiffEnum;
import com.example.base64comparator.base64comparator.exception.Base64NotFoundException;
import com.example.base64comparator.base64comparator.exception.LeftOrRightDataNotFoundException;
import com.example.base64comparator.base64comparator.model.Base64Model;
import com.example.base64comparator.base64comparator.model.DifferenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Service class of Base65 comparator.
 */
@Service
public class Base64DiffService {

    @Autowired
    Base64Repository base64Repository;
    @Autowired
    DiffCalculatorUtil diffCalculatorUtil;

    /**
     * This method used for saving left and right input data.
     *
     * @param inputData left or right input data.
     * @return Base64Model object.
     */
    @Transactional
    public Base64Model save(Base64Model inputData) {
        Base64Model existingBase64Model = base64Repository.findOneById(inputData.getId());
        if (Objects.nonNull(existingBase64Model)) {
            if (Objects.nonNull(inputData.getLeftData())) {
                existingBase64Model.setLeftData(inputData.getLeftData());
            }
            if (Objects.nonNull(inputData.getRightData())) {
                existingBase64Model.setRightData(inputData.getRightData());
            }
            return base64Repository.save(existingBase64Model);
        } else {
            return base64Repository.save(inputData);
        }
    }

    /**
     * This method calculates differences and construct DifferenceModel object.
     *
     * @param id parameter id
     * @return Difference Model
     */
    public DifferenceModel findDiff(String id) {
        Base64Model base64Model = base64Repository.findOneById(id);
        DifferenceModel differenceModel = buildDifferenceModel(base64Model);
        if (base64Model.getLeftData().equals(base64Model.getRightData())) {
            differenceModel.setDiffMessage(DiffEnum.EQUAL.getVal());
        } else if (base64Model.getLeftData().length() != base64Model.getRightData()
                .length()) {
            differenceModel.setDiffMessage(DiffEnum.NOT_EQUAL_SIZE.getVal());
        } else if (base64Model.getLeftData().length() == base64Model.getRightData()
                .length()) {
            differenceModel.setDiffMessage(DiffEnum.DIFFERENT.getVal());
            differenceModel.setDifferences(diffCalculatorUtil
                    .calculateJsonDifference(base64Model.getLeftData(), base64Model.getRightData()));
        }
        return differenceModel;
    }

    /**
     * This methods builds DifferenceModel.
     *
     * @param base64Model Base64Model object
     * @return DifferenceModel
     */
    private DifferenceModel buildDifferenceModel(Base64Model base64Model) {
        if (Objects.isNull(base64Model)) {
            throw new Base64NotFoundException();
        }
        if (Objects.isNull(base64Model.getLeftData()) && Objects.isNull(base64Model.getRightData())) {
            throw new LeftOrRightDataNotFoundException();
        } else {
            return DifferenceModel.builder()
                    .id(base64Model.getId())
                    .leftData(base64Model.getLeftData())
                    .rightData(base64Model.getRightData())
                    .build();
        }
    }
}
