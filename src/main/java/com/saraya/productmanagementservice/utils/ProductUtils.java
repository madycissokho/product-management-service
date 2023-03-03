package com.saraya.productmanagementservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductUtils {

    private ProductUtils(){

    }
    public static ResponseEntity<String>
    getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>
                ("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
