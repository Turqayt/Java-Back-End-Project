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

import java.sql.*;
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
    public ResponseEntity<?> createUser( @RequestBody UserCreateDTO userCreateDTO) throws SQLException {
        userManagementService.createUser(userCreateDTO);
        log.info("User Created, Role Added ");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");
        log.info("User Created, Role Addded ");

        HomePageDTO homePageDTO = new HomePageDTO();

        Statement sta = con.createStatement();
        ResultSet res = sta.executeQuery("Select COUNT(*) From mobile.user");
        while (res.next()) {
            homePageDTO.setNumberOfUsers(Integer.parseInt(res.getString("count")));
        }
        homePageService.updateHomePage(homePageDTO);

        return ResponseEntity.ok(new GenericResponse("User Created."));
    }

    @PutMapping("updateuser/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id,@RequestBody UserUpdateDTO userUpdateDTO){
        final UserDTO user = userManagementService.updateUser(id,userUpdateDTO);
        log.info("User Updated");
        return  ResponseEntity.ok(user);
    }

    @PostMapping("userauthorization")
    public ResponseEntity<?> userAuthorization( @RequestBody UserRoleAddedDTO userRoleCreateDTO){
        userManagementService.userAuthorization(userRoleCreateDTO);
        return ResponseEntity.ok(new GenericResponse("User authorization."));
    }


}
