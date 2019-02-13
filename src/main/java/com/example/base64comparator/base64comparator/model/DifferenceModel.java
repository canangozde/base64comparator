package com.example.base64comparator.base64comparator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DifferenceModel is return type for difference calculting endpoint.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DifferenceModel {

    private String id;
    private String leftData;
    private String rightData;
    private String diffMessage;
    private List<Difference> differences;
}
