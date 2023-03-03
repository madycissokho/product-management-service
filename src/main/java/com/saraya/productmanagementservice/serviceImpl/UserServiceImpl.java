package com.saraya.productmanagementservice.serviceImpl;

import com.saraya.productmanagementservice.POJO.User;
import com.saraya.productmanagementservice.constants.ProductConstants;
import com.saraya.productmanagementservice.dao.UserDao;
import com.saraya.productmanagementservice.service.UserService;
import com.saraya.productmanagementservice.utils.ProductUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signUp {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return ProductUtils.getResponseEntity(
                            "Successfully Registered.", HttpStatus.OK);

                } else {
                    return ProductUtils.getResponseEntity
                            ("Email already exists.", HttpStatus.BAD_REQUEST);
                }

            } else {
                return ProductUtils.getResponseEntity(ProductConstants.INVALID_DATA,
                        HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ProductUtils.getResponseEntity(
                ProductConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String,String> requestMap){
      if ( requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber")
        && requestMap.containsKey("email")
                && requestMap.containsKey("password")){
          return true;
      }
      return false;
    }
    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
