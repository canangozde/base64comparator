package com.example.base64comparator.base64comparator.Util;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * Utility class for decoding Base64 input.
 */
@Component
@RequiredArgsConstructor
public class Base64Util {

    /**
     * Decodes base64 encoded input to a valid json data.
     *
     * @param encodedData base 64 encoded data
     * @return decoded string of given base64
     */
    public String base64decode(String encodedData) {
        // Decode data on other side, by processing encoded data
        byte[] valueDecoded = Base64.decodeBase64(encodedData);
        System.out.println("Decoded value is " + new String(valueDecoded, Charset.forName("UTF-8")));
        return new String(valueDecoded, Charset.forName("UTF-8"));
    }
}
