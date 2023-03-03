package com.saraya.productmanagementservice.restImpl;

import com.saraya.productmanagementservice.constants.ProductConstants;
import com.saraya.productmanagementservice.rest.UserRest;
import com.saraya.productmanagementservice.service.UserService;
import com.saraya.productmanagementservice.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex){
           ex.printStackTrace();
        }
        return ProductUtils.getResponseEntity(ProductConstants.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
