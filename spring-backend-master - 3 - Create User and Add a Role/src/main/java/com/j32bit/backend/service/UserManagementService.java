package com.j32bit.backend.service;

import com.j32bit.backend.dto.UserCreateDTO;
import com.j32bit.backend.dto.UserDTO;
import com.j32bit.backend.dto.UserRoleAddedDTO;
import com.j32bit.backend.dto.UserRoleDTO;

public interface UserManagementService {
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO);
}
