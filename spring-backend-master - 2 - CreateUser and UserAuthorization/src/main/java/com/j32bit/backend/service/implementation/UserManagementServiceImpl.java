package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.UserCreateDTO;
import com.j32bit.backend.dto.UserDTO;
import com.j32bit.backend.dto.UserRoleCreateDTO;
import com.j32bit.backend.dto.UserRoleDTO;
import com.j32bit.backend.entity.User;
import com.j32bit.backend.entity.UserRole;
import com.j32bit.backend.repository.UserRepository;
import com.j32bit.backend.repository.UserRoleRepository;
import com.j32bit.backend.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        final User user = userRepository.save(new User(userCreateDTO.getAddress(), userCreateDTO.getCompanyname(), userCreateDTO.getEmail(),
                userCreateDTO.getName(), userCreateDTO.getOccupation(), userCreateDTO.getPhonenumber(), userCreateDTO.getSurname(),
                userCreateDTO.getTcnumber(), userCreateDTO.getUsername()));
        return UserDTO.of(user);
    }

    @Override

    public UserRoleDTO userAuthorization(UserRoleCreateDTO userRoleCreateDTO) {
        log.info("Bakiyoruz m:{},{}",userRoleCreateDTO.getRole_id(),userRoleCreateDTO.getUser_id());
        final UserRole userRole = userRoleRepository.save(new UserRole(userRoleCreateDTO.getRole_id(),userRoleCreateDTO.getUser_id()));
        log.info("Baxtiq m:{},{}",userRoleCreateDTO.getRole_id(),userRoleCreateDTO.getUser_id());
        return UserRoleDTO.of(userRole);
    }
}
