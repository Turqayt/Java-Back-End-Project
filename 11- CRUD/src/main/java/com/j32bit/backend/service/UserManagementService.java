package com.j32bit.backend.service;

import com.j32bit.backend.dto.*;
import com.j32bit.backend.dto.user.UserCreateDTO;
import com.j32bit.backend.dto.user.UserDTO;
import com.j32bit.backend.dto.user.UserRoleAddedDTO;
import com.j32bit.backend.dto.user.UserUpdateDTO;

public interface UserManagementService {
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Integer id);
    UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO);
    void deleteRole(Integer id);
}
