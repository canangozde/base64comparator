package com.example.base64comparator.base64comparator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Base64Model that is used for saving left and right data.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Base64Model {

    @Id
    private String id;
    private String leftData;
    private String rightData;

}
