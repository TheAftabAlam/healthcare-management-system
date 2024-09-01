package com.hms.user.controller;

import com.hms.common.dto.ApiResponse;
import com.hms.common.dto.CommonListTO;
import com.hms.common.dto.FilterObject;
import com.hms.user.dto.UserTO;
import com.hms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService sUser;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveOrUpdateHandler(@RequestBody UserTO user){
        user = sUser.saveOrUpdate(user);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM01","/user","user",user);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/byId/{id}",method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> byIdHandler(@PathVariable Integer id){
        UserTO user = sUser.byId(id);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM03","/byId","user",user);
        return ResponseEntity.ok(response);
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> searchHandler(@RequestBody FilterObject filterObject){
        CommonListTO<UserTO> user = sUser.search(filterObject);
        ApiResponse response = ApiResponse.responseBuilder(HttpStatus.OK.value(), "COM04","/search","user",user);
        return ResponseEntity.ok(response);
    }
}
