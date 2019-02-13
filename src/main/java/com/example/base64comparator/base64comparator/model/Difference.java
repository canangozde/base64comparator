package com.example.base64comparator.base64comparator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Difference object.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Difference {

    private int offset;
    private int length;
}
