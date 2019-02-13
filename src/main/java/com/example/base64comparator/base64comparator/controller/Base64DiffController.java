package com.example.base64comparator.base64comparator.controller;

import com.example.base64comparator.base64comparator.Util.Base64Util;
import com.example.base64comparator.base64comparator.controller.dto.Base64Dto;
import com.example.base64comparator.base64comparator.model.Base64Model;
import com.example.base64comparator.base64comparator.model.DifferenceModel;
import com.example.base64comparator.base64comparator.service.Base64DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * This class provides all endpoints of the comparator.
 */
@RestController
@RequestMapping(value = "/v1/diff", produces = MediaType.APPLICATION_JSON_VALUE)
public class Base64DiffController {

    @Autowired
    Base64DiffService base64DiffService;
    @Autowired
    Base64Util base64Util;

    /**
     * Endpoint for uploading left data.
     *
     * @param id         id of the data.
     * @param binaryData base64 encoded input of a valid json.
     * @return 201 Created.
     */
    @PostMapping(value = "/{id}/left", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Base64Model> uploadLeft(@PathVariable String id,
            @RequestBody @NotEmpty final Base64Dto binaryData) {
        Base64Model base64Model = Base64Model.builder()
                .id(id)
                .leftData(base64Util.base64decode(binaryData.getValue()))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(base64DiffService.save(base64Model));
    }

    /**
     * Endpoint for uploading right data.
     *
     * @param id         id of the data.
     * @param binaryData base64 encoded input of a valid json.
     * @return 201 Created.
     */
    @PostMapping(value = "/{id}/right", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Base64Model> uploadRight(@PathVariable String id,
            @RequestBody @NotEmpty final Base64Dto binaryData) {
        Base64Model base64Model = Base64Model.builder()
                .id(id)
                .rightData(base64Util.base64decode(binaryData.getValue()))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(base64DiffService.save(base64Model));
    }

    /**
     * Endpoint for comparing left and right data.
     *
     * @param id id of the data.
     * @return DifferenceModel object with 200 OK
     */
    @GetMapping(value = "/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DifferenceModel> findDiff(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(base64DiffService.findDiff(id));
    }
}
