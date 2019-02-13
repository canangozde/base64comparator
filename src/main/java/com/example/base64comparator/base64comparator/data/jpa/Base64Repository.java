package com.example.base64comparator.base64comparator.data.jpa;


import com.example.base64comparator.base64comparator.model.Base64Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Persistence class.
 */
public interface Base64Repository extends JpaRepository<Base64Model, String> {

    /**
     * Query for searching existing Base64Model by id.
     *
     * @param id id parameter.
     * @return found Base64Model.
     */
    Base64Model findOneById(String id);
}
