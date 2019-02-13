package com.example.base64comparator.base64comparator.Util;

import com.example.base64comparator.base64comparator.model.Difference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class for calculating differences.
 */
@Component
@RequiredArgsConstructor
public class DiffCalculatorUtil {

    /**
     * Calculates differences of left and right data.
     *
     * @param leftData  left input
     * @param rightData right input
     * @return list of Differences.
     */
    public List<Difference> calculateJsonDifference(String leftData, String rightData) {
        List<Difference> differences = new LinkedList<>();
        int diffOffset = -1;
        int diffLength = 0;
        for (int i = 0; i <= leftData.length(); i++) {
            if (i < leftData.length()
                    && leftData.charAt(i) != rightData.charAt(i)) {
                diffLength++;
                if (diffOffset < 0) {
                    diffOffset = i;
                }
            } else if (diffOffset != -1) {
                differences.add(new Difference(diffOffset, diffLength));
                diffLength = 0;
                diffOffset = -1;
            }
        }

        return differences;
    }
}
