# Base64Comparator
A Spring Boot application for calculating difference of given base 64 encoded two JSON input.

It provides two post endpoint that  accepts JSON base64 encoded binary data on both:

    <host>/v1/diff/<ID>/left
    <host>/v1/diff/<ID>/right
   
The provided data diff-ed and the results are be available on a third end point

     <host>/v1/diff/<ID>

The results shall provide the following info in JSON format
      
      o If equal return that
      o If not of equal size just return that
      o If of same size provide insight in where the diffs are, actual diffs are not needed.
      § So mainly offsets + length in the data
      
## Technologies
Spring Boot with H2 database, Maven, Tomcat

## Usage

<host>/v1/diff/<ID>/left
<host>/v1/diff/<ID>/right

These two endpoints for uploading left an right base64 encoded json expects the following format:

    {
        "binaryData":"<BASE64_ENCODED_JSON_STRING>"
    }

After uploading both left and right input, difference will be calculated with <host>/v1/diff/<ID> get endpoint
It accepts String value as id and produces results in the following format:
    
    {
        "id": "<ID>",
        "leftData":"<ENCODED_LEFT_INPUT>",
        "rightData":"<ENCODED_RIGHT_INPUT>",
        "diffMessage":"<EQUAL|DIFFERENT_SIZES|DIFFERENT_CONTENTS>",
        "differences":[ {"offset":"int", "length":"int"}]
    }
