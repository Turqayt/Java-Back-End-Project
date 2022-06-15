package com.j32bit.backend.service;

import com.j32bit.backend.dto.*;

public interface UserManagementService {
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO);
    UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO);
}
