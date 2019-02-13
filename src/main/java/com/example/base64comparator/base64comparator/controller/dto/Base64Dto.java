package com.example.base64comparator.base64comparator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Base64Dto object for Base64DiffController left and right endpoint inputs.
 */
@Data
@AllArgsConstructor
public class Base64Dto {

    public String value;
}
