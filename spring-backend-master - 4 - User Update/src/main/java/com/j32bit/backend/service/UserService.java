package com.j32bit.backend.service;

import com.j32bit.backend.dto.UserDTO;
import com.j32bit.backend.entity.User;

import java.util.ArrayList;

public interface UserService {
    /**
     *
     * @param username
     * @return the requested user if it is exists.
     */
    public User findByUsername(String username);

    /**
     *
     * @return all Users
     */
    ArrayList<UserDTO> findAll();
}
