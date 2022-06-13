package com.j32bit.backend.api;

import com.j32bit.backend.dto.*;
import com.j32bit.backend.service.AuthenticationService;
import com.j32bit.backend.service.HomePageService;
import com.j32bit.backend.service.UserManagementService;
import com.j32bit.backend.service.UserService;
import com.j32bit.backend.shared.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Log4j2
@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@CrossOrigin
public class UserAPI {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    private final HomePageService homePageService;
    private final UserManagementService userManagementService;

    @GetMapping("user-list")
    public ResponseEntity<GenericResponse<UserDTO>> userList(){
        ArrayList<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(GenericResponse.createSuccessResponse(users));
    }

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserLoginRequestDTO userLoginRequestDTO) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(userLoginRequestDTO));
    }

    @GetMapping("homepage")
    public ResponseEntity<GenericResponse<HomePageDTO>> homepage(){
        ArrayList<HomePageDTO> homepage = homePageService.findAll();
        return ResponseEntity.ok(GenericResponse.createSuccessResponse(homepage));
    }
    @PostMapping("createuser")
    public ResponseEntity<?> createUser( @RequestBody UserCreateDTO userCreateDTO){
        userManagementService.createUser(userCreateDTO);
        log.info("User Created");
        UserRoleCreateDTO userRoleCreateDTO = new UserRoleCreateDTO();
        userRoleCreateDTO.setRole_id(2);
        userRoleCreateDTO.setUser_id(24);
        userManagementService.userAuthorization(userRoleCreateDTO);
        return ResponseEntity.ok(new GenericResponse("User Created."));
    }

}
