package com.j32bit.backend.service;

import com.j32bit.backend.dto.*;

public interface UserManagementService {
    UserDTO createUser(UserCreateDTO userCreateDTO);
    UserDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Integer id);
    UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO);
    void deleteRole(Integer id);
}
