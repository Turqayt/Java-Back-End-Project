package com.j32bit.backend.service;

import com.j32bit.backend.dto.*;

public interface UserManagementService {
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO);

    UserDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO);
}
