package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.*;
import com.j32bit.backend.entity.User;
import com.j32bit.backend.entity.UserRole;
import com.j32bit.backend.repository.UserRepository;
import com.j32bit.backend.repository.UserRoleRepository;
import com.j32bit.backend.service.UserManagementService;
import com.j32bit.backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

/*
* Kullanıcı ekleme, rol düzenleme, güncelleme ve "soft delete" silme işlemi bu serviste yapılıyor
* */

@Service
@RequiredArgsConstructor
@Log4j2
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;

    //Burada kullanıcı ve rol bilgileri ekleniyor
    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        final User user = userRepository.save(new User(userCreateDTO.getAddress(), userCreateDTO.getCompanyname(), userCreateDTO.getEmail(),
                userCreateDTO.getName(), userCreateDTO.getOccupation(), userCreateDTO.getPhonenumber(), userCreateDTO.getSurname(),
                userCreateDTO.getTcnumber(), userCreateDTO.getUsername(), null,null));
        log.info("User Created");
        UserRoleAddedDTO userRoleAddedDTO = new UserRoleAddedDTO();
        userRoleAddedDTO.setRole_id(2);
        userRoleAddedDTO.setUser_id(user.getId());
        userAuthorization(userRoleAddedDTO);
        log.info("Role added");
        return UserDTO.of(user);
    }

    //Kullanıcı bilgileri günceleniyor
    @Override
    @Transactional
    public UserDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));
        log.info("User Found");
        user.setAddress(userUpdateDTO.getAddress());
        user.setCompanyName(userUpdateDTO.getCompanyname());
        user.setEmail(userUpdateDTO.getEmail());
        user.setName(userUpdateDTO.getName());
        user.setOccupation(userUpdateDTO.getOccupation());
        user.setPhoneNumber(userUpdateDTO.getPhonenumber());
        user.setSurname(userUpdateDTO.getSurname());
        user.setTcNumber(userUpdateDTO.getTcnumber());
        user.setUserName(userUpdateDTO.getUsername());
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        String updateBy = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setUpdatedBy(updateBy);
        log.info("User Values Assigned");
        final User updateUser = userRepository.save(user);
        log.info("User Data Saved in Database");
        return UserDTO.of(updateUser);
    }

    //Kullanici siliyor
    @Override
    @Transactional
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
        log.info("User Deleted");
    }

    //Rol bilgileri günceleniyor
    @Override
    @Transactional
    public UserRoleDTO userAuthorization(UserRoleAddedDTO userRoleAddedDTO) {
        final UserRole userRole = userRoleRepository.save(new UserRole(userRoleAddedDTO.getRole_id(), userRoleAddedDTO.getUser_id()));
       log.info("Rol Updated");
        return UserRoleDTO.of(userRole);
    }

    //rol siliyor
    @Override
    @Transactional
    public void deleteRole(Integer id) {
        userRoleRepository.deleteById(id);
        log.info("Role Deleted");
    }


}
